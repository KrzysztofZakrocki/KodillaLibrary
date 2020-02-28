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

import java.awt.print.Book;
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
    public BooksBorrowingDao booksBorrowingDao;

    @Autowired
    public BooksDao booksDao;

    @Autowired
    public ReadersDao readersDao;

    @Test
    public void testBooksBorrowingSave() {
        //Given
        BooksBorrowing booksBorrowing = new BooksBorrowing(new Date() , new Date());
        //When
        booksBorrowingDao.save(booksBorrowing);
        long id = booksBorrowing.getBorrowingId();
        Optional<BooksBorrowing> booksBorrowingInTest = booksBorrowingDao.findById(id);
        //Then
        assertTrue(booksBorrowingInTest.isPresent());
        //CleanUp
        booksBorrowingDao.deleteById(id);
    }

    @Test
    public void testBooksBorrowingWithOtherEntities() {
        //Given
        BooksBorrowing booksBorrowing = new BooksBorrowing(new Date() , new Date());
        Books book = new Books("borrowed");
        Readers reader = new Readers("Krzysztof", "Zakrocki");
        List<BooksBorrowing> booksBorrowingList = new ArrayList<>();
        booksBorrowingList.add(booksBorrowing);
        book.setBooksBorrowing(booksBorrowingList);
        reader.setBooksBorrowingList(booksBorrowingList);
        booksBorrowing.setReaders(reader);
        booksBorrowing.setBooks(book);
        //When
        booksBorrowingDao.save(booksBorrowing);
        readersDao.save(reader);
        booksDao.save(book);
        long id = booksBorrowing.getBorrowingId();
        Optional<BooksBorrowing> booksBorrowingInTest = booksBorrowingDao.findById(id);
        //Then
        assertTrue(booksBorrowingInTest.isPresent());
        assertEquals(new Long(id), booksBorrowingInTest.get().getBorrowingId());
        assertEquals("borrowed", booksBorrowingInTest.get().getBooks().getStatus());
        assertEquals("Zakrocki", booksBorrowingInTest.get().getReaders().getLastname());
        //CleanUp
        booksBorrowingDao.deleteById(id);
        readersDao.deleteById(reader.getReaderId());
        booksDao.deleteById(book.getBookId());
    }
}
