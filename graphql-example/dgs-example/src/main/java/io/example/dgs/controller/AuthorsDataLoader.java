package io.example.dgs.controller;

import com.netflix.graphql.dgs.DgsDataLoader;
import io.example.dgs.domain.Author;
import io.example.dgs.service.BookService;
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
@DgsDataLoader(name = "authors")
@AllArgsConstructor
public class AuthorsDataLoader implements MappedBatchLoader<Integer, List<Author>> {
    private BookService bookService;

    @Override
    public CompletionStage<Map<Integer, List<Author>>> load(Set<Integer> keys) {
        return CompletableFuture.supplyAsync(() -> bookService.queryAuthorMapList(keys));
    }
}
