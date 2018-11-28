package com.xiao.gs.data.jpa.repository;

import com.xiao.gs.data.jpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * 通过用户名查找用户
     *
     * @param userName 用户名
     * @return 用户
     */
    User findByUsername(String userName);

    Page<User> findAll(Pageable pageable);

}
