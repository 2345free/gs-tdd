package com.xiao.gs.service.impl;

import com.xiao.gs.data.jpa.domain.User;
import com.xiao.gs.data.jpa.repository.UserRepository;
import com.xiao.gs.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author luoxiaoxiao
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(cacheNames = "redis", key = "#id")
    @Override
    public User getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

}
