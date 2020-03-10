package com.library.kodillalibrary.domain.book;

import com.library.kodillalibrary.domain.bookBorrowing.BookBorrowing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {

    private String status;
    private String title;
    private List<BookBorrowing> bookBorrowing;
}
