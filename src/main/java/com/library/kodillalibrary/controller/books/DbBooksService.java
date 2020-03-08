package com.library.kodillalibrary.controller.books;

import com.library.kodillalibrary.controller.readers.ReaderNotFoundException;
import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import com.library.kodillalibrary.domain.bookBorrowing.dao.BooksBorrowingDao;
import com.library.kodillalibrary.domain.books.Books;
import com.library.kodillalibrary.domain.books.dao.BooksDao;
import com.library.kodillalibrary.domain.readers.dao.ReadersDao;
import com.library.kodillalibrary.domain.titles.dao.TitlesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbBooksService {

    @Autowired
    private BooksDao bookDao;

    @Autowired
    private ReadersDao readerDao;

    @Autowired
    private TitlesDao titleDao;

    @Autowired
    private BooksBorrowingDao booksBorrowingDao;

    public Books saveBook(Books book) {
        return bookDao.save(book);
    }

    public void changeBookStatus(Long bookId, String bookStatus) throws BookNotFoundException {
        Books book = bookDao.findById(bookId).orElseThrow(BookNotFoundException::new);
        book.setStatus(bookStatus);
        bookDao.save(book);
    }

    public Long getNumberOfCopiesOfTheBook(String bookTitle) {

        Long numberOfCopies = bookDao.findAll().stream()
                .filter(n -> n.getTitle().getTitle().equals(bookTitle))
                .filter(b -> b.getStatus().equals("for rent"))
                .count();
        return numberOfCopies;
    }

    public void bookBorrowing(String bookTitle, Long readerId) throws ReaderNotFoundException, BookNotFoundException {
       List<Books> booksListForRent = bookDao.findAll().stream()
                .filter(n -> n.getTitle().getTitle().equals(bookTitle))
                .filter(b -> b.getStatus().equals("for rent"))
                .collect(Collectors.toList());
       Books bookForRent = booksListForRent.get(0);
       bookForRent.setStatus("Borrowed");
       bookDao.save(bookForRent);

        BooksBorrowing bookBorrowing = new BooksBorrowing(
                new Date()
        );

        bookBorrowing.setReaders(readerDao.findById(readerId).orElseThrow(ReaderNotFoundException::new));
        bookBorrowing.setBooks(bookDao.findById(bookForRent.getBookId()).orElseThrow(BookNotFoundException::new));
        booksBorrowingDao.save(bookBorrowing);
    }

    public void bookReturn(String bookTitle, Long readerId) throws BookNotFoundException {
        List<BooksBorrowing> booksBorrowingList = booksBorrowingDao.findAll().stream()
                .filter(b -> b.getReaders().getReaderId().equals(readerId))
                .filter(b -> b.getBooks().getTitle().getTitle().equals(bookTitle))
                .collect(Collectors.toList());

        booksBorrowingList.get(0).setDateOfReturn(new Date());
        booksBorrowingDao.save(booksBorrowingList.get(0));

        Long bookToReturnId = booksBorrowingList.get(0).getBooks().getBookId();

        Books bookToReturn = bookDao.findById(bookToReturnId).orElseThrow(BookNotFoundException::new);
        bookToReturn.setStatus("for rent");
        bookDao.save(bookToReturn);
    }
}
