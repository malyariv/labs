package com.malyariv.library_site.repository;

import com.malyariv.library_site.entity.Book;
import com.malyariv.library_site.service.Queries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Repository
@Transactional
public interface BookRepository extends CrudRepository<Book, Integer> {

    Iterable<Book> findByTitleContains(String title);
    Iterable<Book> findAllByReservedIsTrueAndReadyIsFalse();
    Iterable<Book> findAllByReservedIsTrueAndReadyIsTrueAndAvailableIsTrue();
    Iterable<Book> findAllByReservedIsFalseAndReadyIsTrue();
    Iterable<Book> findAllByAvailableIsFalse();
    Iterable<Book> findAllByDeadlineBefore(Date date);

    @Query(value = Queries.findBooksBy, nativeQuery = true)
    Iterable<Book> findBy(String param);
    @Query(value = Queries.findTopBooks, nativeQuery = true)
    Iterable<Book> findTop(int n);
}
