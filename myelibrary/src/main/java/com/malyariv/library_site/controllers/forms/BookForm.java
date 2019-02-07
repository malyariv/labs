package com.malyariv.library_site.controllers.forms;

import com.malyariv.library_site.entity.Author;
import com.malyariv.library_site.entity.Genre;

import java.util.HashSet;
import java.util.Set;

public class BookForm {

    private String title;
    private String authors;
    private String publisher;
    private int year;
    private int pages;
    private String genres;
    private String section;
    private int rack;
    private int shelf;

    public BookForm() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
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

    public Set<Author> getSetOfAuthors() {
        String[] auths=authors.split("; ");
        Set<Author> set=new HashSet<>();
        for (String a:auths) {
            set.add(new Author(a));
        }
        return set;
    }

    public Set<Genre> getSetOfGenres() {
        String[] genreStrings=genres.split("; ");
        Set<Genre> set=new HashSet<>();
        for (String g:genreStrings) {
            set.add(new Genre(g));
        }
        return set;
    }

}
