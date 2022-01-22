package com.s22617.library.Models;

import com.s22617.library.Models.Book;

import javax.persistence.*;
import java.util.List;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
    private int rating;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> books;

    public Library() {
    }

    public Library(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Library(Integer id, String name, String location, int rating, List<Book> books) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                '}';
    }
}
