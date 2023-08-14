package io.example.graphql.service;

import io.example.graphql.domain.Author;
import io.example.graphql.domain.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Book Service
 *
 * @author zww
 * @since 2023-08-14 18:05:20
 */
@Service
public class BookService implements CommandLineRunner {
    private final Map<Long, Book> bookMap = new HashMap<>();
    private final Map<Long, List<Author>> authorMap = new HashMap<>();

    public Book queryBook(Long id) {
        Book book = bookMap.get(id);
        if (book == null) {
            return null;
        }
        List<Author> authors = authorMap.get(id);
        book.setAuthor(authors);
        return book;
    }

    @Override
    public void run(String... args) {
        Book b1 = new Book().setId(1L).setName("java基础").setPageCount(100);
        Book b2 = new Book().setId(2L).setName("java进阶").setPageCount(200);
        Book b3 = new Book().setId(3L).setName("java实战").setPageCount(300);
        bookMap.put(b1.getId(), b1);
        bookMap.put(b2.getId(), b2);
        bookMap.put(b3.getId(), b3);
        Author a1 = new Author().setId(11L).setFirstName("张").setLastName("大");
        Author a2 = new Author().setId(22L).setFirstName("张").setLastName("二");
        Author a3 = new Author().setId(33L).setFirstName("张").setLastName("三");
        Author a4 = new Author().setId(44L).setFirstName("李").setLastName("大");
        Author a5 = new Author().setId(55L).setFirstName("李").setLastName("二");
        Author a6 = new Author().setId(66L).setFirstName("李").setLastName("三");
        authorMap.put(b1.getId(), List.of(a1, a2));
        authorMap.put(b2.getId(), List.of(a3, a4));
        authorMap.put(b3.getId(), List.of(a5, a6));
    }
}
