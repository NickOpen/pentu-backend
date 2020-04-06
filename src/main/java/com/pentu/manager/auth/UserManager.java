package com.pentu.manager.auth;


import com.pentu.common.anotation.Manager;
import com.pentu.common.jpa.BaseCRUD;
import com.pentu.domain.auth.User;
import com.pentu.repository.auth.UserRepository;

@Manager
public class UserManager extends BaseCRUD<User, UserRepository> {

    /**
     * 根据用户名称获得用户
     *
     * @param username
     * @return
     */
    public User getUserByName(String username) {
        return repository.findByUsername(username);
    }

}