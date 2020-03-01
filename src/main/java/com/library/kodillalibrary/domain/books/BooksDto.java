package com.library.kodillalibrary.domain.books;

import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import com.library.kodillalibrary.domain.titles.Titles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BooksDto {

    private Long bookId;
    private String status;
    private Titles title;
    private List<BooksBorrowing> booksBorrowing;
}
