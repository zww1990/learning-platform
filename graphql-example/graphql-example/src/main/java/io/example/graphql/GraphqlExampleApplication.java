package io.example.graphql;

import graphql.language.ScalarTypeDefinition;
import graphql.scalars.ExtendedScalars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Graphql Example Application
 *
 * @author zww
 * @since 2023-08-14 16:53:58
 */
@SpringBootApplication
@Slf4j
public class GraphqlExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GraphqlExampleApplication.class, args);
        log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//        java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
    }

    @Bean
    public GraphQlSourceBuilderCustomizer graphQlSourceBuilderCustomizer() {
        return builder ->
                builder.configureRuntimeWiring(config ->
                        config.scalar(ExtendedScalars.DateTime)
                                .scalar(ExtendedScalars.Date)
                                .scalar(ExtendedScalars.Time)
                                .scalar(ExtendedScalars.LocalTime)
                ).configureTypeDefinitions(config -> {
                    config.add(ScalarTypeDefinition.newScalarTypeDefinition().name("DateTime").build());
                    config.add(ScalarTypeDefinition.newScalarTypeDefinition().name("Date").build());
                    config.add(ScalarTypeDefinition.newScalarTypeDefinition().name("Time").build());
                    config.add(ScalarTypeDefinition.newScalarTypeDefinition().name("LocalTime").build());
                });
    }
}
