package com.malyariv.library_site.service;

public class Queries {
    public static final String findBooksBy= "SELECT * FROM books WHERE title LIKE %?1% " +
            "UNION " +
            "SELECT * FROM books WHERE books.id IN " +
            "   (SELECT books_genres.books_id FROM books_genres " +
            "       WHERE books_genres.genres_id IN " +
            "           (SELECT genres.id FROM genres WHERE name LIKE %?1%))" +
            "UNION " +
            "SELECT * FROM books WHERE books.id IN " +
            "   (SELECT books_authors.books_id FROM books_authors" +
            "       WHERE books_authors.authors_id IN" +
            "           (SELECT authors.id FROM authors WHERE authors.fullname like %?1%));";

    public static final String findTopBooks="SELECT * from books order by counter DESC limit ?1";
    public static final String findTopClient="SELECT * from clients order by counter DESC limit ?1";

    public static final String findByEmployeeData="SELECT * FROM users " +
            "       WHERE users.role_id = (SELECT id FROM roles " +
            "                                   WHERE name='ROLE_STAFF')" +
            "       AND employee_id IN (SELECT employees.id FROM employees " +
            "                                   WHERE employees.firstname like %?1%" +
            "                                   OR employees.lastname like %?1%" +
            "                                   OR employees.email like %?1%)";

    public static final String findByClientData="SELECT * FROM users " +
            "       WHERE users.role_id = (SELECT id FROM roles " +
            "                                   WHERE name='ROLE_USER')" +
            "       AND client_id IN (SELECT clients.id FROM clients " +
            "                                   WHERE clients.firstname like %?1%" +
            "                                   OR clients.lastname like %?1%" +
            "                                   OR clients.email like %?1%)";
}
