package com.pentu.repository.auth;

import com.pentu.common.jpa.PagingAndSpecificationRepository;
import com.pentu.domain.auth.User;

public interface UserRepository extends PagingAndSpecificationRepository<User, Integer> {

    /**
     * 根据用户名获得用户
     *
     * @param userName
     * @return
     */
    User findByUsername(String userName);

}