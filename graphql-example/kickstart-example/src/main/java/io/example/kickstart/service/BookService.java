package io.example.kickstart.service;

import io.example.kickstart.domain.Author;
import io.example.kickstart.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Book Service
 *
 * @author zww
 * @since 2023-08-14 18:05:20
 */
@Service
@Slf4j
public class BookService implements CommandLineRunner {
    private final Map<Integer, Book> bookMap = new HashMap<>();
    private final List<Author> authorList = new ArrayList<>();

    public Book queryBook(Integer id) {
        log.info("queryBook(): id = {}", id);
        if (id == null) {
            return null;
        }
        return bookMap.get(id);
    }

    public List<Author> queryAuthorByBookId(Integer bookId) {
        log.info("queryAuthorByBookId(): bookId = {}", bookId);
        return authorList.stream().filter(f -> f.getBookId().equals(bookId)).toList();
    }

    public Map<Integer, List<Author>> queryAuthorMapList(Set<Integer> bookIds) {
        log.info("queryAuthorMapList(): bookIds = {}", bookIds);
        return authorList.stream().collect(Collectors.groupingBy(Author::getBookId));
    }

    @Override
    public void run(String... args) {
        Book b1 = new Book().setId(1).setName("java基础").setPageCount(100);
        Book b2 = new Book().setId(2).setName("java进阶").setPageCount(200);
        Book b3 = new Book().setId(3).setName("java实战").setPageCount(300);
        bookMap.put(b1.getId(), b1);
        bookMap.put(b2.getId(), b2);
        bookMap.put(b3.getId(), b3);
        Author a1 = new Author().setId(UUID.randomUUID().toString()).setFirstName("张").setLastName("大").setBookId(b1.getId());
        Author a2 = new Author().setId(UUID.randomUUID().toString()).setFirstName("张").setLastName("二").setBookId(b1.getId());
        Author a3 = new Author().setId(UUID.randomUUID().toString()).setFirstName("张").setLastName("三").setBookId(b2.getId());
        Author a4 = new Author().setId(UUID.randomUUID().toString()).setFirstName("李").setLastName("大").setBookId(b2.getId());
        Author a5 = new Author().setId(UUID.randomUUID().toString()).setFirstName("李").setLastName("二").setBookId(b3.getId());
        Author a6 = new Author().setId(UUID.randomUUID().toString()).setFirstName("李").setLastName("三").setBookId(b3.getId());
        authorList.add(a1);
        authorList.add(a2);
        authorList.add(a3);
        authorList.add(a4);
        authorList.add(a5);
        authorList.add(a6);
        log.info("run(): 数据初始化完成");
    }

    public List<Book> queryBooks() {
        log.info("queryBooks(): ");
        return bookMap.values().stream().toList();
    }

    public Book createBook(Book book) {
        log.info("createBook(): book = {}", book);
        book.setId(bookMap.size() + 1);
        bookMap.put(book.getId(), book);
        book.getAuthors().forEach(f -> {
            f.setId(UUID.randomUUID().toString()).setBookId(book.getId());
            authorList.add(f);
        });
        return book;
    }

    public Book updateBook(Book book) {
        log.info("updateBook(): book = {}", book);
        if (book == null || book.getId() == null) {
            return null;
        }
        if (!bookMap.containsKey(book.getId())) {
            return null;
        }
        bookMap.put(book.getId(), book);
        Map<String, Author> authorMap = authorList.stream().collect(Collectors.toMap(Author::getId, Function.identity()));
        book.getAuthors().forEach(f ->
                Optional.ofNullable(authorMap.get(f.getId()))
                        .ifPresentOrElse(m -> m.setFirstName(f.getFirstName())
                                        .setLastName(f.getLastName()),
                                () -> log.error("不存在: {}", f)));
        return book;
    }

    public Boolean deleteById(Integer id) {
        log.info("deleteById(): id = {}", id);
        if (id == null) {
            return false;
        }
        if (!bookMap.containsKey(id)) {
            return false;
        }
        bookMap.remove(id);
        authorList.removeIf(p -> id.equals(p.getBookId()));
        return true;
    }

    public List<Author> queryAuthorList() {
        return authorList;
    }

}
