package com.malyariv.library_site.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name",unique = true,nullable = false)
    private String name;
    @Column(name = "demand")
    private int demand=0;
    @Column(name = "counter")
    private int counter=0;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    private Set<Book> booksByGenres =new HashSet<>();

    public Genre() {
    }

    public Genre(String g) {
        name=g;
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

    public int getCounter() {
        return counter;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Set<Book> getBooksByGenres() {
        return booksByGenres;
    }

    public void setBooksByGenres(Set<Book> booksByGenres) {
        this.booksByGenres = booksByGenres;
    }

    public void increment(){
        demand++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;

        Genre genre = (Genre) o;

        return name != null ? name.equals(genre.name) : genre.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
