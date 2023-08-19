package io.example.dgs.controller;

import com.netflix.graphql.dgs.*;
import graphql.relay.Connection;
import io.example.dgs.domain.Author;
import io.example.dgs.domain.Book;
import io.example.dgs.service.BookService;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Book Controller
 *
 * @author zww
 * @since 2023-08-14 18:05:09
 */
@DgsComponent
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @DgsQuery(field = "bookById")
    public Book bookById(@InputArgument Integer id) {
        return bookService.queryBook(id);
    }

    @DgsData(parentType = "Book", field = "authors")
    public CompletableFuture<Author> authors(DgsDataFetchingEnvironment env) {
        Book book = env.getSource();
        DataLoader<Integer, Author> dataLoader = env.getDataLoader(AuthorsDataLoader.class);
        return dataLoader.load(book.getId());
    }

    @DgsQuery(field = "bookList")
    public List<Book> bookList() {
        return bookService.queryBooks();
    }

    @DgsMutation(field = "createBook")
    public Book createBook(@InputArgument Book book) {
        return bookService.createBook(book);
    }

    @DgsMutation(field = "updateBook")
    public Book updateBook(@InputArgument Map<String, Object> book) {
        Book tmp = new Book()
                .setId(Integer.parseInt((String) book.get("id")))
                .setName((String) book.get("name"))
                .setPageCount((int) book.get("pageCount"))
                .setAuthors(((List<Map<String, String>>) book.get("authors"))
                        .stream()
                        .map(m -> new Author()
                                .setId(m.get("id"))
                                .setFirstName(m.get("firstName"))
                                .setLastName(m.get("lastName")))
                        .collect(Collectors.toList()));
        return bookService.updateBook(tmp);
    }

    @DgsMutation(field = "deleteById")
    public Boolean deleteById(@InputArgument Integer id) {
        return bookService.deleteById(id);
    }

    @DgsQuery(field = "authorPage")
    public Connection<Author> authorPage(@InputArgument Integer first, @InputArgument String after) {
        return bookService.queryAuthorPage(first, after);
    }
}
