package io.online.videosite;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class SimpleTests {
    @Test
    public void testPasswordEncoder() {
        try {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String raw = "123456";
            System.err.println("原始密码：" + raw);
            String encode = encoder.encode(raw);
            System.err.println("加密后密码：" + encode);
            System.err.println("是否匹配：" + encoder.matches(raw, encode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSubList() {
        try {
            List<Object> list = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
            int limit = 10;
            System.err.println(list.size());
            System.err.println(list.subList(0, limit));
            System.err.println(list.subList(limit, list.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
