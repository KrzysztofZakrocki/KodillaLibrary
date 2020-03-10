package com.library.kodillalibrary.domain.reader;

import com.library.kodillalibrary.domain.bookBorrowing.BookBorrowing;
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
public class ReaderDto {

    private Long readerId;
    private String firstname;
    private String lastname;
    private Date accountCreateDate;
    private List<BookBorrowing> bookBorrowingList;
}
