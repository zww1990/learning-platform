package io.example.graphql.controller;

import io.example.graphql.domain.Book;
import io.example.graphql.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

/**
 * Book Controller
 *
 * @author zww
 * @since 2023-08-14 18:05:09
 */
@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @SchemaMapping(typeName = "BookQuery", field = "bookById")
    public Book bookById(@Argument Long id) {
        return bookService.queryBook(id);
    }
}
