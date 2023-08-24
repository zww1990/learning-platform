package io.example.dgs.controller;

import com.netflix.graphql.dgs.*;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import io.example.dgs.domain.Author;
import io.example.dgs.domain.Book;
import io.example.dgs.security.Authentication;
import io.example.dgs.service.BookService;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
    @Authentication
    public Book createBook(@InputArgument Book book) {
        return bookService.createBook(book);
    }

    @DgsMutation(field = "updateBook")
    @Authentication
    public Book updateBook(@InputArgument Integer id, @InputArgument Book book) {
        return bookService.updateBook(
                Optional.ofNullable(book)
                        .map(bk -> bk.setId(id))
                        .orElseGet(Book::new));
    }

    @DgsMutation(field = "deleteById")
    @Authentication
    public Boolean deleteById(@InputArgument Integer id) {
        return bookService.deleteById(id);
    }

    @DgsQuery(field = "authorPage")
    public Connection<Author> authorPage(DataFetchingEnvironment env) {
        return new SimpleListConnection<>(bookService.queryAuthorList()).get(env);
    }
}
