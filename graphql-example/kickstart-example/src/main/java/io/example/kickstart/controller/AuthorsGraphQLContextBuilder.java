package io.example.kickstart.controller;

import graphql.kickstart.execution.context.GraphQLKickstartContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import io.example.kickstart.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import jakarta.websocket.server.HandshakeRequest;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Authors Graph QL Context Builder
 *
 * @author zww
 * @since 2023-08-20 16:28:56
 */
@Component
@AllArgsConstructor
public class AuthorsGraphQLContextBuilder implements GraphQLServletContextBuilder {
    private final BookService bookService;

    @Override
    public GraphQLKickstartContext build() {
        return GraphQLKickstartContext.of(this.buildDataLoaderRegistry());
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        return DataLoaderRegistry.newRegistry()
                .register("authorsDataLoader",
                        DataLoaderFactory.newMappedDataLoader((Set<Integer> keys) -> {
                            System.err.println("keys = " + keys);
                            return CompletableFuture.supplyAsync(() -> bookService.queryAuthorMapList(keys));
                        }))
                .build();
    }

    @Override
    public GraphQLKickstartContext build(HttpServletRequest request, HttpServletResponse response) {
        Map<Object, Object> map = new HashMap<>();
        map.put(HttpServletRequest.class, request);
        map.put(HttpServletResponse.class, response);
        return GraphQLKickstartContext.of(this.buildDataLoaderRegistry(), map);
    }

    @Override
    public GraphQLKickstartContext build(Session session, HandshakeRequest handshakeRequest) {
        Map<Object, Object> map = new HashMap<>();
        map.put(Session.class, session);
        map.put(HandshakeRequest.class, handshakeRequest);
        return GraphQLKickstartContext.of(this.buildDataLoaderRegistry(), map);
    }
}
