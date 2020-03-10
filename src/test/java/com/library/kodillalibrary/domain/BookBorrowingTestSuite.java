package com.library.kodillalibrary.domain;

import com.library.kodillalibrary.domain.bookBorrowing.BookBorrowing;
import com.library.kodillalibrary.domain.bookBorrowing.dao.BookBorrowingDao;
import com.library.kodillalibrary.domain.book.Book;
import com.library.kodillalibrary.domain.book.dao.BookDao;
import com.library.kodillalibrary.domain.reader.Reader;
import com.library.kodillalibrary.domain.reader.dao.ReaderDao;
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
public class BookBorrowingTestSuite {

    @Autowired
    public BookBorrowingDao bookBorrowingDao;

    @Autowired
    public BookDao bookDao;

    @Autowired
    public ReaderDao readerDao;

    @Test
    public void testBooksBorrowingSave() {
        //Given
        BookBorrowing bookBorrowing = new BookBorrowing(new Date());
        //When
        bookBorrowingDao.save(bookBorrowing);
        long id = bookBorrowing.getBorrowingId();
        Optional<BookBorrowing> bookBorrowingInTest = bookBorrowingDao.findById(id);
        //Then
        assertTrue(bookBorrowingInTest.isPresent());
        //CleanUp
        bookBorrowingDao.deleteById(id);
    }

    @Test
    public void testBooksBorrowingWithOtherEntities() {
        //Given
        BookBorrowing bookBorrowing = new BookBorrowing(new Date());
        Book book = new Book("borrowed");
        Reader reader = new Reader("Krzysztof", "Zakrocki");
        List<BookBorrowing> bookBorrowingList = new ArrayList<>();
        bookBorrowingList.add(bookBorrowing);
        book.setBookBorrowing(bookBorrowingList);
        reader.setBookBorrowingList(bookBorrowingList);
        bookBorrowing.setReader(reader);
        bookBorrowing.setBook(book);
        //When
        bookBorrowingDao.save(bookBorrowing);
        readerDao.save(reader);
        bookDao.save(book);
        long id = bookBorrowing.getBorrowingId();
        Optional<BookBorrowing> booksBorrowingInTest = bookBorrowingDao.findById(id);
        //Then
        assertTrue(booksBorrowingInTest.isPresent());
        assertEquals(new Long(id), booksBorrowingInTest.get().getBorrowingId());
        assertEquals("borrowed", booksBorrowingInTest.get().getBook().getStatus());
        assertEquals("Zakrocki", booksBorrowingInTest.get().getReader().getLastname());
        //CleanUp
        bookBorrowingDao.deleteById(id);
        readerDao.deleteById(reader.getReaderId());
        bookDao.deleteById(book.getBookId());
    }
}
