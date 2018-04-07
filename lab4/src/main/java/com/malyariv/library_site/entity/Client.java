package com.malyariv.library_site.entity;

import com.malyariv.library_site.controllers.forms.Human;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client implements Human{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "firstname",nullable = false)
    private String firstname;
    @Column(name = "lastname",nullable = false)
    private String lastname;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "counter")
    private int counter;



    @OneToOne(cascade = CascadeType.ALL,mappedBy = "clientData", fetch = FetchType.EAGER)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Book> bookSet=new HashSet<>();

    public Client() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
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
}
