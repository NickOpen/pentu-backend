package com.pentu.manager.auth;

import com.pentu.common.anotation.Manager;
import com.pentu.common.jpa.BaseCRUD;
import com.pentu.domain.auth.Privilege;
import com.pentu.repository.auth.PrivilegeRepository;

@Manager
public class PrivilegeManager extends BaseCRUD<Privilege, PrivilegeRepository> {

    /**
     * 根据名获得Privilege
     * @param name privilege name
     * @return Privilege
     */
    public Privilege getPrivilege(String name){
        return repository.findByName(name);
    }
}