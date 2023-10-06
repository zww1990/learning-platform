package io.online.videosite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.stream.Stream;

@SpringBootTest
public class VideoSiteApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        System.err.println(this.applicationContext.getBeanDefinitionCount());
        Stream.of(this.applicationContext.getBeanDefinitionNames())
                .forEach(System.err::println);
        System.err.println(this.applicationContext.getBean(PathMatcher.class));
        System.err.println(this.applicationContext.getBean(PathPatternParser.class));
    }

}
