package io.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;

public class ClassUtilsTests {
    @Test
    public void testIsAssignable() {
        try {
            System.err.println(ClassUtils.isAssignable(Instant.class, LocalDate.class));
            System.err.println(ClassUtils.isAssignable(Instant.class, LocalTime.class));
            System.err.println(ClassUtils.isAssignable(Instant.class, LocalDateTime.class));
            System.err.println(ClassUtils.isAssignable(Instant.class, Instant.class));
            System.err.println("---------------------------------------------------------------");
            System.err.println(ClassUtils.isAssignable(LocalDateTime.class, LocalDate.class));
            System.err.println(ClassUtils.isAssignable(LocalDateTime.class, LocalTime.class));
            System.err.println(ClassUtils.isAssignable(LocalDateTime.class, LocalDateTime.class));
            System.err.println(ClassUtils.isAssignable(LocalDateTime.class, Instant.class));
            System.err.println("---------------------------------------------------------------");
            System.err.println(ClassUtils.isAssignable(LocalDate.class, LocalDateTime.class));
            System.err.println(ClassUtils.isAssignable(LocalDate.class, LocalTime.class));
            System.err.println(ClassUtils.isAssignable(LocalDate.class, LocalDate.class));
            System.err.println(ClassUtils.isAssignable(LocalDate.class, Instant.class));
            System.err.println("---------------------------------------------------------------");
            System.err.println(ClassUtils.isAssignable(LocalTime.class, LocalDateTime.class));
            System.err.println(ClassUtils.isAssignable(LocalTime.class, LocalDate.class));
            System.err.println(ClassUtils.isAssignable(LocalTime.class, LocalTime.class));
            System.err.println(ClassUtils.isAssignable(LocalTime.class, Instant.class));
            System.err.println("---------------------------------------------------------------");
            System.err.println(ClassUtils.isAssignable(long.class, Long.class));
            System.err.println(ClassUtils.isAssignable(Long.class, long.class));
            System.err.println("---------------------------------------------------------------");
            System.err.println(ClassUtils.isAssignable(Date.class, java.util.Date.class));
            System.err.println(ClassUtils.isAssignable(Time.class, java.util.Date.class));
            System.err.println(ClassUtils.isAssignable(Timestamp.class, java.util.Date.class));
            System.err.println(ClassUtils.isAssignable(java.util.Date.class, java.util.Date.class));
            System.err.println("---------------------------------------------------------------");
            System.err.println(ClassUtils.isAssignable(Date.class, Date.class));
            System.err.println(ClassUtils.isAssignable(Time.class, Date.class));
            System.err.println(ClassUtils.isAssignable(Timestamp.class, Date.class));
            System.err.println(ClassUtils.isAssignable(java.util.Date.class, Date.class));
            System.err.println("---------------------------------------------------------------");
            System.err.println(ClassUtils.isAssignable(Date.class, Time.class));
            System.err.println(ClassUtils.isAssignable(Time.class, Time.class));
            System.err.println(ClassUtils.isAssignable(Timestamp.class, Time.class));
            System.err.println(ClassUtils.isAssignable(java.util.Date.class, Time.class));
            System.err.println("---------------------------------------------------------------");
            System.err.println(ClassUtils.isAssignable(Date.class, Timestamp.class));
            System.err.println(ClassUtils.isAssignable(Time.class, Timestamp.class));
            System.err.println(ClassUtils.isAssignable(Timestamp.class, Timestamp.class));
            System.err.println(ClassUtils.isAssignable(java.util.Date.class, Timestamp.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDateTime() {
        try {
            System.err.println(Instant.now());
            System.err.println(LocalDateTime.now());
            System.err.println(LocalDate.now());
            System.err.println(LocalTime.now());
            System.err.println("---------------------------------------------------------------");
            System.err.println(Duration.ZERO);
            System.err.println(Period.ZERO);
            System.err.println(ZoneId.systemDefault());
            System.err.println("---------------------------------------------------------------");
            System.err.println(new Date(System.currentTimeMillis()));
            System.err.println(new Time(System.currentTimeMillis()));
            System.err.println(new Timestamp(System.currentTimeMillis()));
            System.err.println(new java.util.Date(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
