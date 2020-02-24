package com.library.kodillalibrary.domain.readers;


import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "READERS")
public class Readers {

    private Long readerId;
    private String firstname;
    private String lastname;
    private Date accountCreateDate;
    private List<BooksBorrowing> booksBorrowingList;

    public Readers(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountCreateDate = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
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
            targetEntity = BooksBorrowing.class,
            mappedBy = "readers",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<BooksBorrowing> getBooksBorrowingList() {
        return booksBorrowingList;
    }
}
