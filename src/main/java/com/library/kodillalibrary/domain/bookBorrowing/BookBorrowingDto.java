package com.library.kodillalibrary.domain.bookBorrowing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookBorrowingDto {

    private Long borrowingId;
    private Date dateOfBorrowing;
    private Date dateOfReturn;
    private Long bookId;
    private Long readerId;
}
