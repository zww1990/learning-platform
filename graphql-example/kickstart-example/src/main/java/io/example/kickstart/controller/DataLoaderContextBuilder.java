package io.example.kickstart.controller;

import graphql.kickstart.execution.context.GraphQLKickstartContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
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

/**
 * Data Loader Context Builder
 *
 * @author zww
 * @since 2023-08-26 16:47:02
 */
@Component
@AllArgsConstructor
public class DataLoaderContextBuilder implements GraphQLServletContextBuilder {
    private final AuthorsDataLoader authorsDataLoader;

    @Override
    public GraphQLKickstartContext build(HttpServletRequest request, HttpServletResponse response) {
        Map<Object, Object> map = new HashMap<>();
        map.put(HttpServletRequest.class, request);
        map.put(HttpServletResponse.class, response);
        return GraphQLKickstartContext.of(this.buildDataLoaderRegistry(), map);
    }

    @Override
    public GraphQLKickstartContext build(Session session, HandshakeRequest handshakeRequest) {
        throw new IllegalStateException("不支持此方法");
    }

    @Override
    public GraphQLKickstartContext build() {
        throw new IllegalStateException("不支持此方法");
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        return DataLoaderRegistry.newRegistry()
                .register(
                        AuthorsDataLoader.AUTHORS_DATALOADER_KEY,
                        DataLoaderFactory.newMappedDataLoader(this.authorsDataLoader))
                .build();
    }
}
