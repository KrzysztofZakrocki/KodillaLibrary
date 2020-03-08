package com.library.kodillalibrary.domain;

import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import com.library.kodillalibrary.domain.bookBorrowing.dao.BooksBorrowingDao;
import com.library.kodillalibrary.domain.books.Books;
import com.library.kodillalibrary.domain.books.dao.BooksDao;
import com.library.kodillalibrary.domain.readers.Readers;
import com.library.kodillalibrary.domain.readers.dao.ReadersDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksBorrowingTestSuite {

    @Autowired
    public BooksBorrowingDao bookBorrowingDao;

    @Autowired
    public BooksDao bookDao;

    @Autowired
    public ReadersDao readerDao;

    @Test
    public void testBooksBorrowingSave() {
        //Given
        BooksBorrowing bookBorrowing = new BooksBorrowing(new Date());
        //When
        bookBorrowingDao.save(bookBorrowing);
        long id = bookBorrowing.getBorrowingId();
        Optional<BooksBorrowing> bookBorrowingInTest = bookBorrowingDao.findById(id);
        //Then
        assertTrue(bookBorrowingInTest.isPresent());
        //CleanUp
        bookBorrowingDao.deleteById(id);
    }

    @Test
    public void testBooksBorrowingWithOtherEntities() {
        //Given
        BooksBorrowing bookBorrowing = new BooksBorrowing(new Date());
        Books book = new Books("borrowed");
        Readers reader = new Readers("Krzysztof", "Zakrocki");
        List<BooksBorrowing> booksBorrowingList = new ArrayList<>();
        booksBorrowingList.add(bookBorrowing);
        book.setBooksBorrowing(booksBorrowingList);
        reader.setBooksBorrowingList(booksBorrowingList);
        bookBorrowing.setReaders(reader);
        bookBorrowing.setBooks(book);
        //When
        bookBorrowingDao.save(bookBorrowing);
        readerDao.save(reader);
        bookDao.save(book);
        long id = bookBorrowing.getBorrowingId();
        Optional<BooksBorrowing> booksBorrowingInTest = bookBorrowingDao.findById(id);
        //Then
        assertTrue(booksBorrowingInTest.isPresent());
        assertEquals(new Long(id), booksBorrowingInTest.get().getBorrowingId());
        assertEquals("borrowed", booksBorrowingInTest.get().getBooks().getStatus());
        assertEquals("Zakrocki", booksBorrowingInTest.get().getReaders().getLastname());
        //CleanUp
        bookBorrowingDao.deleteById(id);
        readerDao.deleteById(reader.getReaderId());
        bookDao.deleteById(book.getBookId());
    }
}
