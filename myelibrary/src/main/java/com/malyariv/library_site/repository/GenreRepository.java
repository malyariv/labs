package com.malyariv.library_site.repository;

import com.malyariv.library_site.entity.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GenreRepository extends CrudRepository<Genre, Integer>{
    Genre findByName(String name);
}
