package com.malyariv.library_site.entity;

import com.malyariv.library_site.controllers.forms.BookForm;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "section",nullable = false)
    private String section;
    @Column(name = "rack",nullable = false)
    private int rack;
    @Column(name = "shelf",nullable = false)
    private int shelf;

    @OneToOne(mappedBy = "bookLocation", fetch = FetchType.EAGER)
    private Book book;

    public Location() {
    }

    public Location(BookForm bookForm) {
        section=bookForm.getSection();
        rack=bookForm.getRack();
        shelf=bookForm.getShelf();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getRack() {
        return rack;
    }

    public void setRack(int rack) {
        this.rack = rack;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
