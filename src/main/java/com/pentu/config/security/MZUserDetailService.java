package com.pentu.config.security;

import com.pentu.domain.auth.Privilege;
import com.pentu.domain.auth.Role;
import com.pentu.domain.auth.User;
import com.pentu.manager.auth.RoleManager;
import com.pentu.manager.auth.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MZUserDetailService implements UserDetailsService {

    @Autowired
    private UserManager userManager;
    @Autowired
    private RoleManager roleManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userManager.getUserByName(username);
        if (null == user) {
            throw new UsernameNotFoundException("not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
                true, true, true, getRoleAuthorities(user.getRoles()));
    }

    /**
     * 将角色Privilege 做为权限返回
     *
     * @param roles
     * @return
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    /**
     * 将角色做为Authorities 返回
     *
     * @param roles
     * @return
     */
    private Collection<? extends GrantedAuthority> getRoleAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * 获取Privileges
     *
     * @param roles
     * @return
     */
    private List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> privilegeList = new ArrayList<>();
        for (Role role : roles) {
            privilegeList.addAll(role.getPrivileges());
        }
        for (Privilege privilege : privilegeList) {
            privileges.add(privilege.getName());
        }
        return privileges;
    }

    /**
     * 构造 GrantedAuthority List
     *
     * @param privileges
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}