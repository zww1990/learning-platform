package io.example.kickstart.controller;

import io.example.kickstart.domain.Author;
import io.example.kickstart.service.BookService;
import lombok.AllArgsConstructor;
import org.dataloader.MappedBatchLoader;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Authors Data Loader
 *
 * @author zww
 * @since 2023-08-19 15:33:40
 */
@AllArgsConstructor
public class AuthorsDataLoader implements MappedBatchLoader<Integer, List<Author>> {
    private BookService bookService;

    @Override
    public CompletionStage<Map<Integer, List<Author>>> load(Set<Integer> keys) {
        System.err.println("keys = " + keys);
        return CompletableFuture.supplyAsync(() -> bookService.queryAuthorMapList(keys));
    }
}
