package io.example.graphql.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * File Controller
 *
 * @author zww
 * @since 2023-08-19 11:30:01
 */
@Controller
@Slf4j
public class FileController {
    @MutationMapping("fileUpload")
    public Boolean fileUpload(@Argument MultipartFile file) {
        if (file == null) {
            return false;
        }
        String contentType = file.getContentType();
        String name = file.getName();
        String originalFilename = file.getOriginalFilename();
        long size = file.getSize();
        log.info("contentType = {}, name = {}, originalFilename = {}, size = {}", contentType, name, originalFilename, size);
        return true;
    }

    @MutationMapping("multiFileUpload")
    public Boolean multiFileUpload(@Argument List<MultipartFile> files) {
        if (CollectionUtils.isEmpty(files)) {
            return false;
        }
        files.forEach(file -> {
            String contentType = file.getContentType();
            String name = file.getName();
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            log.info("contentType = {}, name = {}, originalFilename = {}, size = {}", contentType, name, originalFilename, size);
        });
        return true;
    }
}
