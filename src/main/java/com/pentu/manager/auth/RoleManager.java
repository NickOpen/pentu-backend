package com.pentu.manager.auth;

import com.pentu.common.anotation.Manager;
import com.pentu.common.jpa.BaseCRUD;
import com.pentu.domain.auth.Role;
import com.pentu.repository.auth.RoleRepository;

@Manager
public class RoleManager extends BaseCRUD<Role, RoleRepository> {

   public Role getRole(String name){
        return repository.findByName(name);
    }
}