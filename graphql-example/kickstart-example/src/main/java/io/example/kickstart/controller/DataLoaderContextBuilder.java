package io.example.kickstart.controller;

import graphql.kickstart.execution.context.GraphQLKickstartContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContextBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
public class DataLoaderContextBuilder extends DefaultGraphQLServletContextBuilder {
    private final AuthorsDataLoader authorsDataLoader;

    @Override
    public GraphQLKickstartContext build(HttpServletRequest request, HttpServletResponse response) {
        Map<Object, Object> map = new HashMap<>();
        map.put(HttpServletRequest.class, request);
        map.put(HttpServletResponse.class, response);
        return GraphQLKickstartContext.of(this.buildDataLoaderRegistry(), map);
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        return DataLoaderRegistry.newRegistry()
                .register(
                        AuthorsDataLoader.AUTHORS_DATALOADER_KEY,
                        DataLoaderFactory.newMappedDataLoader(this.authorsDataLoader))
                .build();
    }
}
