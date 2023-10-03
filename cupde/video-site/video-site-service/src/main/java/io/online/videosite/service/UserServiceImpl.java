package io.online.videosite.service;

import io.online.videosite.api.UserService;
import io.online.videosite.domain.User;
import io.online.videosite.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户服务接口实现类
 *
 * @author 张维维
 * @since 2023-10-02 14:16:47
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User query(User user) {
        return this.userRepository.findByUsername(user.getUsername());
    }
}
