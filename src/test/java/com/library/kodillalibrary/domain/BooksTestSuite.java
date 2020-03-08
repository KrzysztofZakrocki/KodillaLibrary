package com.library.kodillalibrary.domain;

import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import com.library.kodillalibrary.domain.bookBorrowing.dao.BooksBorrowingDao;
import com.library.kodillalibrary.domain.books.Books;
import com.library.kodillalibrary.domain.books.dao.BooksDao;
import com.library.kodillalibrary.domain.titles.Titles;
import com.library.kodillalibrary.domain.titles.dao.TitlesDao;
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
public class BooksTestSuite {

    @Autowired
    public BooksDao bookDao;

    @Autowired
    public TitlesDao titleDao;

    @Autowired
    public BooksBorrowingDao bookBorrowingDao;

    @Test
    public void testBooksSave() {
        //Given
        Books book = new Books("Borrowed");
        //When
        bookDao.save(book);
        long id = book.getBookId();
        Optional<Books> booksInTest = bookDao.findById(id);
        //Then
        assertTrue(booksInTest.isPresent());
        //CleanUp
        bookDao.deleteById(id);
    }

    @Test
    public void testBooksWithOtherEntities() {
        //Given
        Books book = new Books("Borrowed");
        BooksBorrowing bookBorrowing = new BooksBorrowing(new Date());
        Titles title  = new Titles("Lord of the rings", "J.r.r. Tolkien", 1950);
        List<BooksBorrowing> booksBorrowingList = new ArrayList<>();
        booksBorrowingList.add(bookBorrowing);
        book.setBooksBorrowing(booksBorrowingList);
        bookBorrowing.setBooks(book);
        book.setTitle(title);
        //When
        bookDao.save(book);
        bookBorrowingDao.save(bookBorrowing);
        titleDao.save(title);
        long id = book.getBookId();
        Optional<Books> booksInTest = bookDao.findById(id);
        //Then
        assertTrue(booksInTest.isPresent());
        assertEquals(new Long(id), booksInTest.get().getBookId());
        assertEquals(new Long(bookBorrowing.getBorrowingId()), booksInTest.get().getBooksBorrowing().get(0).getBorrowingId());
        assertEquals("J.r.r. Tolkien", booksInTest.get().getTitle().getAuthor());
        //CleanUp
        bookDao.deleteById(id);
        bookBorrowingDao.deleteById(bookBorrowing.getBorrowingId());
        titleDao.deleteById(title.getTitleId());
    }
}
