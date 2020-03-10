package com.library.kodillalibrary.domain;

import com.library.kodillalibrary.domain.bookBorrowing.BookBorrowing;
import com.library.kodillalibrary.domain.bookBorrowing.dao.BookBorrowingDao;
import com.library.kodillalibrary.domain.book.Book;
import com.library.kodillalibrary.domain.book.dao.BookDao;
import com.library.kodillalibrary.domain.title.Title;
import com.library.kodillalibrary.domain.title.dao.TitleDao;
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
public class BookTestSuite {

    @Autowired
    public BookDao bookDao;

    @Autowired
    public TitleDao titleDao;

    @Autowired
    public BookBorrowingDao bookBorrowingDao;

    @Test
    public void testBooksSave() {
        //Given
        Book book = new Book("Borrowed");
        //When
        bookDao.save(book);
        long id = book.getBookId();
        Optional<Book> booksInTest = bookDao.findById(id);
        //Then
        assertTrue(booksInTest.isPresent());
        //CleanUp
        bookDao.deleteById(id);
    }

    @Test
    public void testBooksWithOtherEntities() {
        //Given
        Book book = new Book("Borrowed");
        BookBorrowing bookBorrowing = new BookBorrowing(new Date());
        Title title  = new Title("Lord of the rings", "J.r.r. Tolkien", 1950);
        List<BookBorrowing> bookBorrowingList = new ArrayList<>();
        bookBorrowingList.add(bookBorrowing);
        book.setBookBorrowing(bookBorrowingList);
        bookBorrowing.setBook(book);
        book.setTitle(title);
        //When
        bookDao.save(book);
        bookBorrowingDao.save(bookBorrowing);
        titleDao.save(title);
        long id = book.getBookId();
        Optional<Book> booksInTest = bookDao.findById(id);
        //Then
        assertTrue(booksInTest.isPresent());
        assertEquals(new Long(id), booksInTest.get().getBookId());
        assertEquals(new Long(bookBorrowing.getBorrowingId()), booksInTest.get().getBookBorrowing().get(0).getBorrowingId());
        assertEquals("J.r.r. Tolkien", booksInTest.get().getTitle().getAuthor());
        //CleanUp
        bookDao.deleteById(id);
        bookBorrowingDao.deleteById(bookBorrowing.getBorrowingId());
        titleDao.deleteById(title.getTitleId());
    }
}
