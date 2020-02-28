package com.library.kodillalibrary.domain.readers;

import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReadersDto {

    private Long readerId;
    private String firstname;
    private String lastname;
    private Date accountCreateDate;
    private List<BooksBorrowing> booksBorrowingList;
}
