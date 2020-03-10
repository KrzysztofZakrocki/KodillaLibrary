package com.library.kodillalibrary.domain.book;

import com.library.kodillalibrary.domain.bookBorrowing.BookBorrowing;
import com.library.kodillalibrary.domain.title.Title;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "BOOKS")
public class Book {

    private Long bookId;
    private String status;
    private Title title;
    private List<BookBorrowing> bookBorrowing;

    public Book(String status) {
        this.status = status;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID", unique = true)
    public Long getBookId() {
        return bookId;
    }

    @NotNull
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "TITLE_ID")
    public Title getTitle() {
        return title;
    }

    @OneToMany(
            targetEntity = BookBorrowing.class,
            mappedBy = "book",
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    public List<BookBorrowing> getBookBorrowing() {
        return bookBorrowing;
    }
}
