package com.library.kodillalibrary.domain.books;

import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import com.library.kodillalibrary.domain.titles.Titles;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "BOOKS")
public class Books {

    private Long bookId;
    private String status;
    private Titles title;
    private List<BooksBorrowing> booksBorrowing;

    public Books (String status) {
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TITLE_ID")
    public Titles getTitle() {
        return title;
    }

    @OneToMany(
            targetEntity = BooksBorrowing.class,
            mappedBy = "books",
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    public List<BooksBorrowing> getBooksBorrowing() {
        return booksBorrowing;
    }
}
