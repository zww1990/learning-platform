package io.example.graphql.controller;

import io.example.graphql.domain.Book;
import io.example.graphql.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Book Controller
 *
 * @author zww
 * @since 2023-08-14 18:05:09
 */
@Controller
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @SchemaMapping(typeName = "BookQuery", field = "bookById")
    public Book bookById(@Argument Long id) {
        return bookService.queryBook(id);
    }

    @SchemaMapping(typeName = "BookQuery", field = "bookList")
    public List<Book> bookList() {
        return bookService.queryBooks();
    }

    @SchemaMapping(typeName = "BookMutation", field = "createBook")
    public Book createBook(@Argument Book book) {
        return bookService.createBook(book);
    }

    @SchemaMapping(typeName = "BookMutation", field = "updateBook")
    public Book updateBook(@Argument Book book) {
        return bookService.updateBook(book);
    }
}
