package com.xiao.gs.service;

import com.xiao.gs.data.jpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author luoxiaoxiao
 */
public interface UserService {

    User getById(Long id);

    Page<User> findAll(Pageable pageable);

}
