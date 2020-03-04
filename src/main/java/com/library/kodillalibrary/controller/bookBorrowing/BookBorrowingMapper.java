package com.library.kodillalibrary.controller.bookBorrowing;

import com.library.kodillalibrary.controller.books.BookNotFoundException;
import com.library.kodillalibrary.controller.readers.ReadersNotFoundException;
import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowingDto;
import com.library.kodillalibrary.domain.books.dao.BooksDao;
import com.library.kodillalibrary.domain.readers.dao.ReadersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookBorrowingMapper {

    @Autowired
    private BooksDao booksDao;

    @Autowired
    private ReadersDao readersDao;

    public BooksBorrowing mapToBorrowingBook (BooksBorrowingDto booksBorrowingDto) throws BookNotFoundException, ReadersNotFoundException {

        BooksBorrowing booksBorrowing = new BooksBorrowing(
            booksBorrowingDto.getDateOfBorrowing()
        );

        booksBorrowing.setBorrowingId(booksBorrowingDto.getBorrowingId());
        booksBorrowing.setBooks(booksDao.findById(booksBorrowingDto.getBooksId()).orElseThrow(BookNotFoundException::new));
        booksBorrowing.setReaders(readersDao.findById(booksBorrowingDto.getReadersId()).orElseThrow(ReadersNotFoundException::new));

        return booksBorrowing;
    }

    public BooksBorrowingDto mapToDto (BooksBorrowing booksBorrowing) {

        return new BooksBorrowingDto(
          booksBorrowing.getBorrowingId(),
          booksBorrowing.getDateOfBorrowing(),
          booksBorrowing.getDateOfReturn(),
          booksBorrowing.getBooks().getBookId(),
          booksBorrowing.getReaders().getReaderId()
        );
    }
}
