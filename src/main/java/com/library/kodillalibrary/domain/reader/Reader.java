package com.library.kodillalibrary.domain.reader;


import com.library.kodillalibrary.domain.bookBorrowing.BookBorrowing;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "READERS")
public class Reader {

    private Long readerId;
    private String firstname;
    private String lastname;
    private Date accountCreateDate;
    private List<BookBorrowing> bookBorrowingList;

    public Reader(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountCreateDate = new Date();
        this.bookBorrowingList = new ArrayList<>();
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "READER_ID", unique = true)
    public Long getReaderId() {
        return readerId;
    }

    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    @NotNull
    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    @NotNull
    @Column(name = "ACCOUNT_CREATE_DATE")
    public Date getAccountCreateDate() {
        return accountCreateDate;
    }

    @OneToMany(
            targetEntity = BookBorrowing.class,
            mappedBy = "reader",
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    public List<BookBorrowing> getBookBorrowingList() {
        return bookBorrowingList;
    }
}
