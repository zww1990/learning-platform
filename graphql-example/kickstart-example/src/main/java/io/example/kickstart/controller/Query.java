package io.example.kickstart.controller;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import io.example.kickstart.domain.Author;
import io.example.kickstart.domain.Book;
import io.example.kickstart.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Query
 *
 * @author zww
 * @since 2023-08-24 21:13:15
 */
@Component
@AllArgsConstructor
public class Query implements GraphQLQueryResolver {
    private final BookService bookService;

    public CompletableFuture<Book> bookById(Integer id) {
        return CompletableFuture.supplyAsync(() -> bookService.queryBook(id));
    }

    public CompletableFuture<List<Book>> bookList() {
        return CompletableFuture.supplyAsync(bookService::queryBooks);
    }

    public CompletableFuture<Connection<Author>> authorPage(int first, String after, DataFetchingEnvironment env) {
        return CompletableFuture.supplyAsync(() -> new SimpleListConnection<>(bookService.queryAuthorList()).get(env));
    }
}
