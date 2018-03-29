package com.malyariv.library_site.repository;

import com.malyariv.library_site.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Repository
@Transactional
public interface BookRepository extends CrudRepository<Book, Integer> {

    Iterable<Book> findByTitle(String title);
    Iterable<Book> findAllByReservedIsTrueAndReadyIsFalse();
    Iterable<Book> findAllByReservedIsFalseAndReadyIsTrue();
    Iterable<Book> findAllByDeadlineBefore(Date date);
}
