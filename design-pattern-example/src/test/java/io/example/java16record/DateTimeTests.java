package io.example.java16record;

public class DateTimeTests {
    public static void main(String[] args) {
        DateTime dt = new DateTime("2023-9-21", "15:28:22");
        DateTime dt2 = dt.clone();
        System.err.println(dt.date());
        System.err.println(dt.time());
        System.err.println(dt);
        System.err.println(dt2);
        System.err.println(dt2 == dt);
        System.err.println(dt2.equals(dt));
    }
}
