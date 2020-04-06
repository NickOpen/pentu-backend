package com.pentu.repository.auth;

import com.pentu.common.jpa.PagingAndSpecificationRepository;
import com.pentu.domain.auth.Privilege;

public interface PrivilegeRepository extends PagingAndSpecificationRepository<Privilege, Integer> {

    Privilege findByName(String name);

}