package com.library.kodillalibrary.domain.titles;

import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import com.library.kodillalibrary.domain.books.Books;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "TITLES")
public class Titles {

    private Long titleId;
    private String title;
    private String author;
    private int yearOfPublication;
    private List<Books> booksList;

    public Titles(String title, String author, int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        booksList = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "TITLE_ID", unique = true)
    public Long getTitleId() {
        return titleId;
    }

    @NotNull
    @Column(name = "TITLE", unique = true)
    public String getTitle() {
        return title;
    }

    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    @Column(name = "YEAR_OF_PUBLICATION")
    public int getYearOfPublication() {
        return yearOfPublication;
    }

    @OneToMany(
            targetEntity = Books.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Books> getBooksList() {
        return booksList;
    }

}
