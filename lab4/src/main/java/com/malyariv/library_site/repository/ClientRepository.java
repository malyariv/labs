package com.malyariv.library_site.repository;

import com.malyariv.library_site.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ClientRepository extends CrudRepository<Client, Integer> {
    Client findClientByEmail(String email);
}
