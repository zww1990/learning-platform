package io.example.demo;

import io.example.demo.config.ApplicationProperties;
import io.example.demo.excel.ExcelUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * JavaSpringApplication
 *
 * @author zhang weiwei
 * @since 2022年8月12日, 下午8:45:37
 */
@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class JavaSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JavaSpringApplication.class, args);
        log.debug("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
    }

    private final ApplicationProperties properties;

    @Override
    public void run(String... args) throws Exception {
        log.info("{}", this.properties);
        this.doMergeExcel();
    }

    private void doMergeExcel() throws Exception {
        Path path = Paths.get(this.properties.getReadFolder());
        if (Files.notExists(path)) {
            log.info("正在创建文件夹[ {} ]", this.properties.getReadFolder());
            Files.createDirectories(path);
        }
        List<String> fileList = Files.walk(path).filter(p -> {
            String tmp = p.toString().toLowerCase();
            return tmp.endsWith(".xls") || tmp.endsWith(".xlsx");
        }).map(Path::toString).sorted().collect(Collectors.toList());
        if (fileList.isEmpty()) {
            log.info("文件夹[ {} ]没有待合并的工作簿", this.properties.getReadFolder());
            return;
        }
        log.info("文件夹[ {} ]待合并的工作簿总个数[ {} ]", this.properties.getReadFolder(), fileList.size());
        ExcelUtils.mergeExcel(fileList, this.properties.getWriteFolder(), this.properties.getDateTimePattern());
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("是否继续合并?(Y/N):");
            if ("Y".equalsIgnoreCase(sc.next())) {
                this.doMergeExcel();
            } else {
                System.out.println("程序运行结束。");
            }
        }
    }
}
