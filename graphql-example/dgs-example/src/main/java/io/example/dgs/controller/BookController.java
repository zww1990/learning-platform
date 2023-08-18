package io.example.dgs.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import io.example.dgs.domain.Author;
import io.example.dgs.domain.Book;
import io.example.dgs.service.BookService;
import lombok.AllArgsConstructor;

import java.util.List;

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

    @DgsData(parentType = "BookQuery", field = "bookById")
    public Book bookById(@InputArgument Long id) {
        return bookService.queryBook(id);
    }

    @DgsData(parentType = "Book", field = "authors")
    public List<Author> authorsByBookId(DgsDataFetchingEnvironment env) {
        Book book = env.getSource();
        return bookService.queryAuthorsByBookId(book.getId());
    }

    @DgsData(parentType = "BookQuery", field = "bookList")
    public List<Book> bookList() {
        return bookService.queryBooks();
    }

    @DgsData(parentType = "BookMutation", field = "createBook")
    public Book createBook(@InputArgument Book book) {
        return bookService.createBook(book);
    }

    @DgsData(parentType = "BookMutation", field = "updateBook")
    public Book updateBook(@InputArgument Book book) {
        return bookService.updateBook(book);
    }

    @DgsData(parentType = "BookMutation", field = "deleteById")
    public Boolean deleteById(@InputArgument Long id) {
        return bookService.deleteById(id);
    }

//    @SchemaMapping(typeName = "BookQuery", field = "authorPage")
//    public Connection<Author> authorPage(@Argument Integer first, @Argument String after) {
//        return bookService.queryAuthorPage(first, after);
//    }
}
