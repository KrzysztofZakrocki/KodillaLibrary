package com.library.kodillalibrary.controller.books;

import com.library.kodillalibrary.controller.readers.ReaderNotFoundException;
import com.library.kodillalibrary.domain.bookBorrowing.BookBorrowing;
import com.library.kodillalibrary.domain.bookBorrowing.dao.BookBorrowingDao;
import com.library.kodillalibrary.domain.book.Book;
import com.library.kodillalibrary.domain.book.dao.BookDao;
import com.library.kodillalibrary.domain.reader.dao.ReaderDao;
import com.library.kodillalibrary.domain.title.dao.TitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbBooksService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private BookBorrowingDao bookBorrowingDao;

    public Book saveBook(Book book) {
        return bookDao.save(book);
    }

    public void changeBookStatus(Long bookId, String bookStatus) throws BookNotFoundException {
        Book book = bookDao.findById(bookId).orElseThrow(BookNotFoundException::new);
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
       List<Book> bookListForRent = bookDao.findAll().stream()
                .filter(n -> n.getTitle().getTitle().equals(bookTitle))
                .filter(b -> b.getStatus().equals("for rent"))
                .collect(Collectors.toList());
       Book bookForRent = bookListForRent.get(0);
       bookForRent.setStatus("Borrowed");
       bookDao.save(bookForRent);

        BookBorrowing bookBorrowing = new BookBorrowing(
                new Date()
        );

        bookBorrowing.setReader(readerDao.findById(readerId).orElseThrow(ReaderNotFoundException::new));
        bookBorrowing.setBook(bookDao.findById(bookForRent.getBookId()).orElseThrow(BookNotFoundException::new));
        bookBorrowingDao.save(bookBorrowing);
    }

    public void bookReturn(String bookTitle, Long readerId) throws BookNotFoundException {
        List<BookBorrowing> bookBorrowingList = bookBorrowingDao.findAll().stream()
                .filter(b -> b.getReader().getReaderId().equals(readerId))
                .filter(b -> b.getBook().getTitle().getTitle().equals(bookTitle))
                .collect(Collectors.toList());

        bookBorrowingList.get(0).setDateOfReturn(new Date());
        bookBorrowingDao.save(bookBorrowingList.get(0));

        Long bookToReturnId = bookBorrowingList.get(0).getBook().getBookId();

        Book bookToReturn = bookDao.findById(bookToReturnId).orElseThrow(BookNotFoundException::new);
        bookToReturn.setStatus("for rent");
        bookDao.save(bookToReturn);
    }
}
