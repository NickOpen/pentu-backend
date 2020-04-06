package com.pentu.repository.auth;

import com.pentu.common.jpa.PagingAndSpecificationRepository;
import com.pentu.domain.auth.Role;

public interface RoleRepository extends PagingAndSpecificationRepository<Role, Integer> {

    Role findByName(String name);
}