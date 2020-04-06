package com.pentu.config;

import com.pentu.domain.auth.Privilege;
import com.pentu.domain.auth.Role;
import com.pentu.domain.auth.User;
import com.pentu.manager.auth.PrivilegeManager;
import com.pentu.manager.auth.RoleManager;
import com.pentu.manager.auth.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private UserManager userManager;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private PrivilegeManager privilegeManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean alreadySetup = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        List<Privilege> userPrivileges = Collections.singletonList(readPrivilege);
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        Role userRole = createRoleIfNotFound("ROLE_USER", userPrivileges);
        createUserIfNotFound("13888888888", passwordEncoder.encode("Pass1234"), true,
                Collections.singletonList(adminRole));
        alreadySetup = true;
    }

    @Transactional
    protected User createUserIfNotFound(String username, String password, boolean enable, Collection<Role> roles) {
        User user = userManager.getUserByName(username);
        if (null == user) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRoles(roles);
            userManager.addDomain(user);
        }
        return user;
    }

    @Transactional
    protected Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeManager.getPrivilege(name);
        if (null == privilege) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegeManager.addDomain(privilege);
        }

        return privilege;
    }

    @Transactional
    protected Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleManager.getRole(name);
        if (null == role) {
            role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleManager.addDomain(role);
        }
        return role;
    }
}