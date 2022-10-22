package io.example.demo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import io.example.demo.config.ApplicationProperties;
import io.example.demo.excel.ExcelUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * LoveWqxApplication
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午8:45:37
 */
@SpringBootApplication
@Slf4j
public class LoveWqxApplication implements CommandLineRunner {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(LoveWqxApplication.class, args);
		log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@Resource
	private ApplicationProperties properties;

	@Override
	public void run(String... args) throws Exception {
		log.info("{}", this.properties);
		Path path = Paths.get(this.properties.getReadFolder());
		if (Files.notExists(path)) {
			log.info("正在创建此文件夹[ {} ]", this.properties.getReadFolder());
			Files.createDirectory(path);
		}
		List<String> fileList = Files.walk(path).filter(p -> {
			String tmp = p.toString().toLowerCase();
			return tmp.endsWith(".xls") || tmp.endsWith(".xlsx");
		}).map(Path::toString).sorted().collect(Collectors.toList());
		if (fileList.isEmpty()) {
			log.info("此文件夹[ {} ]没有待合并的工作簿", this.properties.getReadFolder());
			return;
		}
		ExcelUtils.mergeExcel(fileList, this.properties.getWriteFolder(), this.properties.getDateTimePattern());
	}
}
