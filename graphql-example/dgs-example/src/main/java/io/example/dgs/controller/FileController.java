package io.example.dgs.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import io.example.dgs.domain.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * File Controller
 *
 * @author zww
 * @since 2023-08-19 11:30:01
 */
@DgsComponent
@Slf4j
public class FileController {
    @DgsMutation(field = "fileUpload")
    public FileInfo fileUpload(@InputArgument MultipartFile file) {
        if (file == null) {
            return new FileInfo();
        }
        String contentType = file.getContentType();
        String name = file.getName();
        String originalFilename = file.getOriginalFilename();
        long size = file.getSize();
        log.info("contentType = {}, name = {}, originalFilename = {}, size = {}", contentType, name, originalFilename, size);
        return new FileInfo()
                .setId(UUID.randomUUID().toString())
                .setName(name)
                .setSize(size)
                .setContentType(contentType)
                .setOriginalFilename(originalFilename);
    }

    @DgsMutation(field = "multiFileUpload")
    public List<FileInfo> multiFileUpload(@InputArgument List<MultipartFile> files) {
        if (CollectionUtils.isEmpty(files)) {
            return Collections.emptyList();
        }
        return files.stream().map(file -> {
            String contentType = file.getContentType();
            String name = file.getName();
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            log.info("contentType = {}, name = {}, originalFilename = {}, size = {}", contentType, name, originalFilename, size);
            return new FileInfo()
                    .setId(UUID.randomUUID().toString())
                    .setName(name)
                    .setSize(size)
                    .setContentType(contentType)
                    .setOriginalFilename(originalFilename);
        }).toList();
    }
}
