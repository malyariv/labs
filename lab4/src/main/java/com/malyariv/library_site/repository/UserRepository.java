package com.malyariv.library_site.repository;

import com.malyariv.library_site.entity.User;
import com.malyariv.library_site.service.Queries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = Queries.findByEmployeeData,nativeQuery = true)
    Iterable<User> findByEmployeeData(String name);

    @Query(value = Queries.findByClientData,nativeQuery = true)
    Iterable<User> findByClientData(String name);

    User findByUsername(String username);
    Iterable<User> findByRoleName(String role);
    Iterable<User> findByClientDataFirstnameContains(String name);
    Iterable<User> findByClientDataLastnameContains (String name);
    Iterable<User> findByClientDataEmailContains(String email);
    Iterable<User> findByEmployeeDataFirstnameContains(String name);
    Iterable<User> findByEmployeeDataLastnameContains (String name);
    Iterable<User> findByEmployeeDataEmailContains(String email);

}
