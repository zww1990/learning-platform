package io.example.java16record;

import java.io.Serializable;

/**
 * Java 16 新特性：record类
 *
 * @param date 字符串
 * @param time 字符串
 */
public record DateTime(String date, String time) implements Serializable, Cloneable {
    @Override
    public DateTime clone() {
        try {
            return (DateTime) super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }
}
