package io.example.kickstart.controller;

import graphql.kickstart.tools.GraphQLResolver;
import io.example.kickstart.domain.Author;
import io.example.kickstart.domain.Book;
import io.example.kickstart.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Book Controller
 *
 * @author zww
 * @since 2023-08-14 18:05:09
 */
@Component
@AllArgsConstructor
public class BookController implements GraphQLResolver<Book> {
    private final BookService bookService;

    public CompletableFuture<List<Author>> authors(Book book) {
        return CompletableFuture.supplyAsync(() -> bookService.queryAuthorByBookId(book.getId()));
    }

}
