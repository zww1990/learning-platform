package io.example.kickstart.controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.example.kickstart.domain.Book;
import io.example.kickstart.security.Authentication;
import io.example.kickstart.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Mutation
 *
 * @author zww
 * @since 2023-08-24 21:12:59
 */
@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {
    private final BookService bookService;

    @Authentication
    public CompletableFuture<Book> createBook(Book book) {
        return CompletableFuture.supplyAsync(() -> bookService.createBook(book));
    }

    @Authentication
    public CompletableFuture<Book> updateBook(Book book) {
        return CompletableFuture.supplyAsync(() -> bookService.updateBook(book));
    }

    @Authentication
    public CompletableFuture<Boolean> deleteById(Integer id) {
        return CompletableFuture.supplyAsync(() -> bookService.deleteById(id));
    }

}
