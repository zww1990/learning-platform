package io.online.videosite.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置属性类
 *
 * @author 张维维
 * @since 2023-10-06 09:36:39
 */
@Configuration
@ConfigurationProperties(ignoreInvalidFields = true, prefix = "video-site-app")
@Getter
@Setter
@ToString
public class VideoSiteAppProperties {
    /**
     * 拦截器包括的路径模式
     */
    private String[] includePathPatterns = {"/category/**", "/comment/**", "/videohub/**"};
    /**
     * 拦截器排除的路径模式
     */
    private String[] excludePathPatterns = {"/videohub/show/**"};
    /**
     * 需要管理员身份的路径模式
     */
    private String[] adminPathPatterns = {"/category/**", "/videohub/audit/**"};
    /**
     * 图片格式模式
     */
    private String[] imageMimePatterns = {"image/*"};
    /**
     * 视频格式模式
     */
    private String[] videoMimePatterns = {"video/*"};
    /**
     * 图片上传目录
     */
    private String imageUploadFolder = "/upload/images";
    /**
     * 视频上传目录
     */
    private String videoUploadFolder = "/upload/videos";
}
