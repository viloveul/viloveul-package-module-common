package com.viloveul.module.common.service.impl;

import com.viloveul.context.util.misc.MediaStorage;
import com.viloveul.context.base.AbstractComponent;
import com.viloveul.context.type.VisibilityType;
import com.viloveul.module.common.data.entity.Media;
import com.viloveul.module.common.pojo.MediaForm;
import com.viloveul.module.common.data.repository.MediaRepository;
import com.viloveul.module.common.service.MediaService;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.context.exception.GeneralFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

@Service
public class MediaServiceImpl extends AbstractComponent implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaStorage mediaStorage;

    @Override
    public Media upload(@Valid MediaForm form) throws IOException {
        URL url = new URL(form.getSource());
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        final int bufLen = 4 * 0x400; // 4KB
        byte[] buf = new byte[bufLen];
        int readLen;
        Media media = new Media();
        media.setName(form.getName());
        media.setVisibility(form.getVisibility());
        media.setMime(connection.getContentType());
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            while ((readLen = inputStream.read(buf, 0, bufLen)) != -1) {
                outputStream.write(buf, 0, readLen);
            }
            media.setSize((long) outputStream.size());
            media.setSource(
                this.mediaStorage.write(form.getName(), new ByteArrayInputStream(outputStream.toByteArray()))
            );
            outputStream.flush();
        }
        return this.mediaRepository.save(media);
    }

    @Override
    public Media upload(String name, VisibilityType visibility, MultipartFile file) throws IOException {
        Media media = new Media();
        media.setName(name);
        media.setVisibility(visibility);
        media.setSize(file.getSize());
        media.setSource(this.mediaStorage.write(file.getOriginalFilename(), file.getInputStream()));
        media.setMime(file.getContentType());
        return this.mediaRepository.save(media);
    }

    @Override
    public Media upload(String name, MultipartFile file) throws IOException {
        return this.upload(name, VisibilityType.PUBLIC, file);
    }

    @Override
    public Media upload(MultipartFile file) throws IOException {
        return this.upload(file.getName(), VisibilityType.PUBLIC, file);
    }

    @Override
    public Media detail(String id) {
        Optional<Media> res = this.mediaRepository.findById(id);
        if (!res.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return res.get();
    }

    @Override
    public File download(Media media) {
        return this.mediaStorage.load(media.getSource());
    }

    @Override
    public File download(String id) {
        Optional<Media> res = this.mediaRepository.findById(id);
        if (!res.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        Media media = res.get();
        return this.mediaStorage.load(media.getSource());
    }

    @Override
    public Page<Media> search(Specification<Media> filter, Pageable pageable) {
        return this.mediaRepository.findAll(filter, pageable);
    }
}
