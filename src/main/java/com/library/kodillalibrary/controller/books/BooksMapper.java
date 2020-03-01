package com.library.kodillalibrary.controller.books;

import com.library.kodillalibrary.controller.titles.TitlesNotFoundException;
import com.library.kodillalibrary.domain.books.Books;
import com.library.kodillalibrary.domain.books.BooksDto;

public class BooksMapper {

    public Books mapToBook(BooksDto booksDto) throws TitlesNotFoundException {
        Books book = new Books(
            booksDto.getStatus()
        );
        book.setBookId(booksDto.getBookId());
        book.setTitle(booksDto.getTitle());
        book.setBooksBorrowing(booksDto.getBooksBorrowing());

        return book;
    }

    public BooksDto mapToDto(Books book) {
        return new BooksDto(
          book.getBookId(),
          book.getStatus(),
          book.getTitle(),
          book.getBooksBorrowing()
        );
    }
}
