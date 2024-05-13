package io.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleTests {
    public static void main(String[] args) {
        String pattern = "yyyy——MM——dd HH：mm：ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String datetime = formatter.format(LocalDateTime.now());
        System.err.println(datetime);
        System.err.println(formatter.parse(datetime));
    }
}
