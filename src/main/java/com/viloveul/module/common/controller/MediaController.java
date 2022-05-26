package com.viloveul.module.common.controller;

import com.viloveul.context.base.AbstractController;
import com.viloveul.context.type.VisibilityType;
import com.viloveul.module.common.data.entity.Media;
import com.viloveul.module.common.pojo.MediaForm;
import com.viloveul.module.common.search.MediaSpecification;
import com.viloveul.module.common.service.MediaService;
import com.viloveul.context.util.misc.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping(path = "${viloveul.controller.media:/media}")
public class MediaController extends AbstractController {

    @Autowired
    private MediaService mediaService;

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageableResult<Media> search(Pageable pageable, MediaSpecification filter) {
        return new PageableResult<>(this.mediaService.search(filter, pageable));
    }

    @Transactional
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Media> upload(
        @RequestParam("file") MultipartFile file,
        @RequestParam(name = "name") String name,
        @RequestParam(name = "visibility", defaultValue = "PUBLIC", required = false) VisibilityType visibility
    ) throws IOException {
        return new ResponseEntity<>(this.mediaService.upload(name, visibility, file), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Media> upload(@RequestBody @Valid MediaForm form) throws IOException {
        return new ResponseEntity<>(this.mediaService.upload(form), HttpStatus.CREATED);
    }

    @ResponseBody
    @GetMapping(path = "/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Media> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.mediaService.detail(id), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/{id}/download")
    @Transactional(readOnly = true)
    public ResponseEntity<InputStreamSource> download(@PathVariable("id") String id) throws MalformedURLException {
        Media media = this.mediaService.detail(id);
        File file = this.mediaService.download(media);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, media.getMime());
        return ResponseEntity.ok().headers(headers).body(new UrlResource(file.toURI()));
    }

}
