package io.example.reactive.service;

import io.example.reactive.model.ClientUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * UserServiceV2Tests
 *
 * @author weiwei
 * @version v2
 * @since 2022年4月26日, 下午5:17:49
 */
@SpringBootTest
@AutoConfigureWebTestClient
public class UserServiceV2Tests {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void testGetClientUser() {
        System.err.println(this.webClient.get()//
                .uri("/v2/user/get")//
                .exchange()//
                .expectStatus()//
                .isOk()//
                .expectBody(String.class)//
                .returnResult());
    }

    @Test
    public void testGetClientUserByUserId() {
        String userId = "1001";
        System.err.println(this.webClient.get()//
                .uri("/v2/user/get/{userId}", userId)//
                .exchange()//
                .expectStatus()//
                .isOk()//
                .expectBody(String.class)//
                .returnResult());
    }

    @Test
    public void testAddClientUser() {
        ClientUser user = new ClientUser()//
                .setGender(0)//
                .setPhoneNumber("5555555")//
                .setUserId("1001")//
                .setUsername("张无忌");
        System.err.println(this.webClient.post()//
                .uri("/v2/user/add")//
                .contentType(MediaType.APPLICATION_JSON)//
                .bodyValue(user)//
                .exchange()//
                .expectStatus()//
                .isOk()//
                .expectBody(String.class)//
                .returnResult());
    }

    @Test
    public void testDelClientUser() {
        String userId = "1001";
        System.err.println(this.webClient.delete()//
                .uri("/v2/user/del/{userId}", userId)//
                .exchange()//
                .expectStatus()//
                .isOk()//
                .expectBody(String.class)//
                .returnResult());
    }

    @Test
    public void testUpdateClientUser() {
        String userId = "1001";
        ClientUser user = this.webClient.get()//
                .uri("/v2/user/get/{userId}", userId)//
                .exchange()//
                .expectStatus()//
                .isOk()//
                .expectBody(ClientUser.class)//
                .returnResult()//
                .getResponseBody();
        if (user == null) {
            System.err.println(String.format("此[%s]不存在", userId));
            return;
        }
        user.setGender(1)//
                .setPhoneNumber("999999992")//
                .setUsername("李小龙2");
        System.err.println(this.webClient.put()//
                .uri("/v2/user/update")//
                .contentType(MediaType.APPLICATION_JSON)//
                .bodyValue(user)//
                .exchange()//
                .expectStatus()//
                .isOk()//
                .expectBody(String.class)//
                .returnResult());
    }
}
