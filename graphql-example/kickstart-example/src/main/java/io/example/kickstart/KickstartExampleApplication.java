package io.example.kickstart;

import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.schema.GraphQLScalarType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Kickstart Example Application
 *
 * @author zww
 * @since 2023-08-14 16:53:58
 */
@SpringBootApplication
@Slf4j
public class KickstartExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KickstartExampleApplication.class, args);
        log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//        java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
    }

    @Bean
    GraphQLScalarType uploadScalarType() {
        return ApolloScalars.Upload;
    }
}
