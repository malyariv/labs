package com.malyariv.library_site.repository;

import com.malyariv.library_site.entity.Book;
import com.malyariv.library_site.entity.Client;
import com.malyariv.library_site.service.Queries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ClientRepository extends CrudRepository<Client, Integer> {

    Client findClientByEmail(String email);

    @Query(value = Queries.findTopClient, nativeQuery = true)
    Iterable<Client> findTop(int n);
}
