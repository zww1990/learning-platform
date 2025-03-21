package io.example.graphql.controller;

import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import io.example.graphql.domain.Author;
import io.example.graphql.domain.Book;
import io.example.graphql.security.Authentication;
import io.example.graphql.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

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

    @QueryMapping("bookById")
    public Book bookById(@Argument Long id) {
        return bookService.queryBook(id);
    }

    @QueryMapping("bookList")
    public List<Book> bookList() {
        return bookService.queryBooks();
    }

    @MutationMapping("createBook")
    @Authentication
    public Book createBook(@Argument Book book) {
        return bookService.createBook(book);
    }

    @MutationMapping("updateBook")
    @Authentication
    public Book updateBook(@Argument Book book) {
        return bookService.updateBook(book);
    }

    @MutationMapping("deleteById")
    @Authentication
    public Boolean deleteById(@Argument Long id) {
        return bookService.deleteById(id);
    }

    @QueryMapping("authorPage")
    public Connection<Author> authorPage(DataFetchingEnvironment env) {
        return new SimpleListConnection<>(bookService.queryAuthorList()).get(env);
    }

    @BatchMapping(typeName = "Book", field = "authors")
    public Mono<Map<Book, List<Author>>> authorMap(List<Book> books) {
        return Mono.just(bookService.queryAuthorMap(books));
    }
}
