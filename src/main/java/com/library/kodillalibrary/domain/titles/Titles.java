package com.library.kodillalibrary.domain.titles;

import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "TITLES")
public class Titles {

    private Long titleId;
    private String title;
    private String author;
    private int yearOfPublication;

    public Titles(String title, String author, int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "TITLE_ID", unique = true)
    public Long getTitleId() {
        return titleId;
    }

    @NotNull
    @Column(name = "TITLE")
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
}
