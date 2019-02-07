package com.malyariv.library_site.entity;

import com.malyariv.library_site.controllers.forms.BookForm;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "year")
    private int year;
    @Column(name = "pages")
    private int pages;
    @Column(name = "available")
    private boolean available=true;
    @Column(name = "reserved")
    private boolean reserved=false;
    @Column(name = "ready")
    private boolean ready=false;
    @Column(name = "deadline")
    private Date deadline;
    @Column(name = "cover")
    private String cover;
    @Column(name = "counter")
    private int counter;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location bookLocation;

    @ManyToMany(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "books_id")},
            inverseJoinColumns = {@JoinColumn(name = "authors_id")})
    private Set<Author> authors=new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "books_genres",
            joinColumns = {@JoinColumn(name = "books_id")},
            inverseJoinColumns = {@JoinColumn(name = "genres_id")})
    private Set<Genre> genres=new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;


    public Book() {
    }
    public Book(BookForm bookForm) {
        title=bookForm.getTitle();
        publisher=bookForm.getPublisher();
        year=bookForm.getYear();
        pages=bookForm.getPages();
        authors=bookForm.getSetOfAuthors();
        genres=bookForm.getSetOfGenres();

    }


    public Set<Author> getAuthors() {
        return new HashSet<>(authors);
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = new HashSet<>(authors);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Location getBookLocation() {
        return bookLocation;
    }

    public void setBookLocation(Location bookLocation) {
        this.bookLocation = bookLocation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increment(){
        counter++;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", available=" + available +
                ", reserved=" + reserved +
                ", ready=" + ready +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        return id == book.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
