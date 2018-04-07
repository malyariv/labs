package com.malyariv.library_site.repository;

import com.malyariv.library_site.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
