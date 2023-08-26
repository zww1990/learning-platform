package io.example.kickstart.controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.example.kickstart.domain.FileInfo;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * File Controller
 *
 * @author zww
 * @since 2023-08-19 11:30:01
 */
@Component
@Slf4j
public class FileController implements GraphQLMutationResolver {
    public FileInfo fileUpload(Part file) {
        if (file == null) {
            return new FileInfo();
        }
        return this.buildFileInfo(file);
    }

    public List<FileInfo> multiFileUpload(List<Part> files) {
        if (CollectionUtils.isEmpty(files)) {
            return Collections.emptyList();
        }
        return files.stream().map(this::buildFileInfo).toList();
    }

    private FileInfo buildFileInfo(Part file) {
        String contentType = file.getContentType();
        String name = file.getName();
        String submittedFileName = file.getSubmittedFileName();
        long size = file.getSize();
        log.info("contentType = {}, name = {}, submittedFileName = {}, size = {}", contentType, name, submittedFileName, size);
        return new FileInfo()
                .setId(UUID.randomUUID().toString())
                .setName(name)
                .setSize(size)
                .setContentType(contentType)
                .setSubmittedFileName(submittedFileName);
    }
}
