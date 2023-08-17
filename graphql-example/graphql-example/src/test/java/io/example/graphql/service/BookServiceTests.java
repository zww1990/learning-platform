package io.example.graphql.service;

import graphql.relay.*;
import io.example.graphql.domain.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        int first = 2;
        String after = "e";
        System.err.println("-----------------原始集合---------------------");
        authorList.forEach(System.err::println);
        int index = authorList.indexOf(authorList.stream().filter(f -> f.getId().equals(after)).findFirst().orElse(null));
        if (index == -1) {
            return;
        }
        int toindex = index + first;
        if (toindex > authorList.size()) {
            toindex = authorList.size();
        }
        List<Author> tmp = authorList.subList(index, toindex);
        System.err.println("--first=" + first + ", after=" + after + ", index=" + index + ", toIndex=" + toindex + "------------");
        tmp.forEach(System.err::println);
        String start = tmp.get(0).getId();
        String end = tmp.get(tmp.size() - 1).getId();
        List<Edge<Author>> edges = tmp
                .stream()
                .map(au -> new DefaultEdge<>(au, new DefaultConnectionCursor(au.getId())))
                .collect(Collectors.toList());
        PageInfo page = new DefaultPageInfo(
                new DefaultConnectionCursor(start),
                new DefaultConnectionCursor(end),
                index > 0,
                toindex < authorList.size()
        );
        Connection<Author> connection = new DefaultConnection<>(edges, page);
        System.err.println("-----------------getEdges()-------------------");
        connection.getEdges().forEach(System.err::println);
        System.err.println("--------------getPageInfo()-------------------");
        System.err.println(connection.getPageInfo());
    }
}
