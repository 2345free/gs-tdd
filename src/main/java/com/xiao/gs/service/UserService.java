package com.xiao.gs.service;

import com.xiao.gs.data.jpa.domain.User;

/**
 * @author luoxiaoxiao
 */
public interface UserService {

    User getById(Long id);

}
