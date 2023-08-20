package io.example.kickstart.service;

import io.example.kickstart.domain.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BookServiceTests {
    private final List<Author> authorList = new ArrayList<>();

    @BeforeEach
    public void testInit() {
        Author a1 = new Author().setId("a").setFirstName("张").setLastName("大");
        Author a2 = new Author().setId("b").setFirstName("张").setLastName("二");
        Author a3 = new Author().setId("c").setFirstName("张").setLastName("三");
        Author a4 = new Author().setId("d").setFirstName("李").setLastName("大");
        Author a5 = new Author().setId("e").setFirstName("李").setLastName("二");
        Author a6 = new Author().setId("f").setFirstName("李").setLastName("三");
        authorList.add(a1);
        authorList.add(a2);
        authorList.add(a3);
        authorList.add(a4);
        authorList.add(a5);
        authorList.add(a6);
    }

    @Test
    public void testQueryAuthors() {
        authorList.forEach(System.err::println);
    }
}
