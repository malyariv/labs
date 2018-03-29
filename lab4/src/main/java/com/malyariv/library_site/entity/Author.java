package com.malyariv.library_site.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name="fullname",unique = true,nullable = false)
    private String fullname;


    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<Book> books=new HashSet<>();

    public Author() {
    }

    public Author(String author) {
        fullname=author;
        String[] auths=author.split(" ");
        name=auths[0];
        surname=auths[1];
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = new HashSet<>(books);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        return fullname != null ? fullname.equals(author.fullname) : author.fullname == null;
    }

    @Override
    public int hashCode() {
        return fullname != null ? fullname.hashCode() : 0;
    }
}
