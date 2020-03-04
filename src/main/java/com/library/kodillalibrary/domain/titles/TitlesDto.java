package com.library.kodillalibrary.domain.titles;

import com.library.kodillalibrary.domain.books.Books;
import com.library.kodillalibrary.domain.books.BooksDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TitlesDto {

    private Long titleId;
    private String title;
    private String author;
    private int yearOfPublication;
}
