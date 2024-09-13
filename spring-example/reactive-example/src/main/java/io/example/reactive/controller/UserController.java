package io.example.reactive.controller;

import io.example.reactive.model.ClientUser;
import io.example.reactive.service.UserServiceV2;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserServiceV2 userService;

    @GetMapping(path = "/get")
    public Flux<ClientUser> getAll() {
    	return this.userService.getClientUser();
    }

    @PostMapping(path = "/add")
    public Mono<ClientUser> add(@RequestBody ClientUser user) {
    	return this.userService.addClientUser(user);
    }

    @PutMapping(path = "/update")
    public Mono<ClientUser> update(@RequestBody ClientUser user) {
    	return this.userService.updateClientUser(user);
    }

    @DeleteMapping(path = "/del/{userId}")
    public Mono<Long> del(@PathVariable String userId) {
    	return this.userService.delClientUser(userId);
    }

    @GetMapping(path = "/get/{userId}")
    public Mono<ClientUser> get(@PathVariable String userId) {
    	return this.userService.getClientUserByUserId(userId);
    }
}
