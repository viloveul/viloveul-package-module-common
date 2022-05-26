package com.viloveul.module.common.service;

import com.viloveul.context.type.VisibilityType;
import com.viloveul.module.common.data.entity.Media;
import com.viloveul.module.common.pojo.MediaForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public interface MediaService {

    Media upload(MediaForm form) throws IOException;

    Media upload(String name, VisibilityType visibility, MultipartFile file) throws IOException;

    Media upload(String name, MultipartFile file) throws IOException;

    Media upload(MultipartFile file) throws IOException;

    Media detail(String id);

    File download(Media media);

    File download(String id);

    Page<Media> search(Specification<Media> filter, Pageable pageable);

}
