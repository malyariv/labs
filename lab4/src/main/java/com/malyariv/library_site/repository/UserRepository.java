package com.malyariv.library_site.repository;

import com.malyariv.library_site.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    Iterable<User> findByRole(String role);
}
