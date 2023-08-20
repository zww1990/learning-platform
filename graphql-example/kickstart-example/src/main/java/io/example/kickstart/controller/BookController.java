package io.example.kickstart.controller;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import io.example.kickstart.domain.Author;
import io.example.kickstart.domain.Book;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

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

    public CompletableFuture<Author> authors(DataFetchingEnvironment env) {
        System.err.println("env = " + env);
        Book book = env.getSource();
        DataLoader<Integer, Author> dataLoader = env.getDataLoader("AuthorsDataLoader");
        return dataLoader.load(book.getId());
    }

}
