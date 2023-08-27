package io.example.kickstart.controller;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import io.example.kickstart.domain.*;
import io.example.kickstart.service.BookService;
import io.example.kickstart.service.UserService;
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
    private final UserService userService;

    public CompletableFuture<Book> bookById(Integer id) {
        return CompletableFuture.supplyAsync(() -> bookService.queryBook(id));
    }

    public CompletableFuture<List<Book>> bookList() {
        return CompletableFuture.supplyAsync(bookService::queryBooks);
    }

    public CompletableFuture<Connection<Author>> authorPage(
            // 指定取游标后的多少个数据，与after搭配使用
            Integer first,
            // 开始游标，与first搭配使用
            String after,
            // 指定取游标前的多少个数据，与before搭配使用
            Integer last,
            // 结束游标，与last搭配使用
            String before,
            DataFetchingEnvironment env) {
        return CompletableFuture.supplyAsync(() -> new SimpleListConnection<>(bookService.queryAuthorList()).get(env));
    }

    public CompletableFuture<List<User>> userList() {
        return CompletableFuture.supplyAsync(userService::queryUsers);
    }

    public CompletableFuture<LoginOutput> login(LoginInput loginInput) {
        return CompletableFuture.supplyAsync(() -> userService.userLogin(loginInput));
    }

}
