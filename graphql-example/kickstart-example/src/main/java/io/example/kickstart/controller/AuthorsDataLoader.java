package io.example.kickstart.controller;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import io.example.kickstart.domain.Author;
import io.example.kickstart.domain.Book;
import io.example.kickstart.service.BookService;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.MappedBatchLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Authors Data Loader
 *
 * @author zww
 * @since 2023-08-14 18:05:09
 */
@Component
@AllArgsConstructor
public class AuthorsDataLoader implements GraphQLResolver<Book>, MappedBatchLoader<Integer, List<Author>> {
    public static final String AUTHORS_DATALOADER_KEY = "authorsDataLoader";
    private final BookService bookService;

    public CompletableFuture<List<Author>> authors(Book book, DataFetchingEnvironment env) {
        DataLoader<Integer, List<Author>> dataLoader = env.getDataLoader(AUTHORS_DATALOADER_KEY);
        return dataLoader.load(book.getId());
    }

    @Override
    public CompletionStage<Map<Integer, List<Author>>> load(Set<Integer> keys) {
        return CompletableFuture.supplyAsync(() -> bookService.queryAuthorMapList(keys));
    }
}
