package io.online.videosite.config;

import io.online.videosite.properties.VideoSiteAppProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.util.UrlPathHelper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义Spring MVC配置类
 *
 * @author 张维维
 * @since 2023-10-05 19:31:12
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class VideoSiteAppConfig implements WebMvcConfigurer, CommandLineRunner {
    private final VideoSiteAppProperties appProps;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("{}", this.appProps);
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns(this.appProps.getIncludePathPatterns())
                .excludePathPatterns(this.appProps.getExcludePathPatterns());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/success").setStatusCode(HttpStatus.OK).setViewName("user/success");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowedOriginPatterns(CorsConfiguration.ALL)
                .exposedHeaders(CorsConfiguration.ALL);
    }

    /**
     * 注册身份验证拦截器
     */
    @Bean
    AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    /**
     * 注册密码编码器
     */
    @Bean
    static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        Path image = Paths.get(this.appProps.getImageUploadFolder());
        if (Files.notExists(image)) {
            // 创建上传图片目录
            Files.createDirectories(image);
        }
        Path video = Paths.get(this.appProps.getVideoUploadFolder());
        if (Files.notExists(video)) {
            // 创建上传视频目录
            Files.createDirectories(video);
        }
    }
}
