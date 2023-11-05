package io.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 应用程序配置
 *
 * @author zhang weiwei
 * @since 2022年8月13日, 下午8:58:14
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@ToString
public class ApplicationProperties {
    /**
     * 指定读取Excel文件目录
     */
    private String readFolder = "/合并/待合并的工作簿";
    /**
     * 指定写入Excel文件目录
     */
    private String writeFolder = "/合并/已合并的工作簿";
    /**
     * 指定日期时间格式
     */
    private String dateTimePattern = "yyyy-MM-dd_HH-mm-ss";
}
