package com.library.kodillalibrary.domain.bookBorrowing;

import com.library.kodillalibrary.domain.books.Books;
import com.library.kodillalibrary.domain.readers.Readers;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "BOOKS_BORROWING")
public class BooksBorrowing {

    private Long borrowingId;
    private Date dateOfBorrowing;
    private Date dateOfReturn;
    private Books books;
    private Readers readers;

    public BooksBorrowing(Date dateOfBorrowing, Date dateOfReturn) {
        this.dateOfBorrowing = dateOfBorrowing;
        this.dateOfReturn = dateOfReturn;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BORROWING_ID", unique = true)
    public Long getBorrowingId() {
        return borrowingId;
    }

    @Column(name = "DATE_OF_BORROWING")
    public Date getDateOfBorrowing() {
        return dateOfBorrowing;
    }

    @Column(name = "DATE_OF_RETURN")
    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "BOOK_ID")
    public Books getBooks() {
        return books;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "READER_ID")
    public Readers getReaders() {
        return readers;
    }
}
