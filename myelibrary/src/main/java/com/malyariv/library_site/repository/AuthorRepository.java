package com.malyariv.library_site.repository;

import com.malyariv.library_site.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AuthorRepository extends CrudRepository<Author, Integer>{
    Author findByFullnameContains(String fullname);
}
