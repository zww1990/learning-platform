package io.example.tinyvue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.accept.ContentNegotiationManager;

@SpringBootTest
public class TinyvueExampleApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        System.err.println(this.applicationContext.getBean(WebMvcProperties.class)
                .getContentnegotiation().getMediaTypes());
        ContentNegotiationManager manager = this.applicationContext.getBean(ContentNegotiationManager.class);
        System.err.println(manager.getAllFileExtensions());
        System.err.println(manager.getMediaTypeMappings());
        System.err.println(manager.getStrategies());
    }

}
