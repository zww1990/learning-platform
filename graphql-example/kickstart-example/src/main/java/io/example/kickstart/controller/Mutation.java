package io.example.kickstart.controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.example.kickstart.domain.Book;
import io.example.kickstart.domain.FileInfo;
import io.example.kickstart.service.BookService;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Mutation
 *
 * @author zww
 * @since 2023-08-24 21:12:59
 */
@Component
@AllArgsConstructor
@Slf4j
public class Mutation implements GraphQLMutationResolver {
    private final BookService bookService;

    public CompletableFuture<Book> createBook(Book book) {
        return CompletableFuture.supplyAsync(() -> bookService.createBook(book));
    }

    public CompletableFuture<Book> updateBook(Book book) {
        return CompletableFuture.supplyAsync(() -> bookService.updateBook(book));
    }

    public CompletableFuture<Boolean> deleteById(Integer id) {
        return CompletableFuture.supplyAsync(() -> bookService.deleteById(id));
    }

    public FileInfo fileUpload(Part file) {
        if (file == null) {
            return new FileInfo();
        }
        String contentType = file.getContentType();
        String name = file.getName();
        String originalFilename = file.getSubmittedFileName();
        long size = file.getSize();
        log.info("contentType = {}, name = {}, originalFilename = {}, size = {}", contentType, name, originalFilename, size);
        return new FileInfo()
                .setId(UUID.randomUUID().toString())
                .setName(name)
                .setSize(size)
                .setContentType(contentType)
                .setOriginalFilename(originalFilename);
    }

    public List<FileInfo> multiFileUpload(List<Part> files) {
        if (CollectionUtils.isEmpty(files)) {
            return Collections.emptyList();
        }
        return files.stream().map(file -> {
            String contentType = file.getContentType();
            String name = file.getName();
            String originalFilename = file.getSubmittedFileName();
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
