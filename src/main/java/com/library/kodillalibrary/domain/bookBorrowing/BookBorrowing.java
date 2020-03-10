package com.library.kodillalibrary.domain.bookBorrowing;

import com.library.kodillalibrary.domain.book.Book;
import com.library.kodillalibrary.domain.reader.Reader;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "BOOKS_BORROWING")
public class BookBorrowing {

    private Long borrowingId;
    private Date dateOfBorrowing;
    private Date dateOfReturn;
    private Book book;
    private Reader reader;

    public BookBorrowing(Date dateOfBorrowing) {
        this.dateOfBorrowing = dateOfBorrowing;
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
    public Book getBook() {
        return book;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "READER_ID")
    public Reader getReader() {
        return reader;
    }
}
