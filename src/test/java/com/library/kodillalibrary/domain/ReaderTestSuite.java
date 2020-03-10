package com.library.kodillalibrary.domain;

import com.library.kodillalibrary.domain.bookBorrowing.BookBorrowing;
import com.library.kodillalibrary.domain.bookBorrowing.dao.BookBorrowingDao;
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
public class ReaderTestSuite {

    @Autowired
    public ReaderDao readerDao;

    @Autowired
    public BookBorrowingDao bookBorrowingDao;

    @Test
    public void testReadersSave() {
        //Given
        Reader reader = new Reader("Krzysztof", "Zakrocki");
        //When
        readerDao.save(reader);
        long id = reader.getReaderId();
        Optional<Reader> readerInTest = readerDao.findById(id);
        //Then
        assertTrue(readerInTest.isPresent());
        //CleanUp
        readerDao.deleteById(id);
    }

    @Test
    public void testReadersEntityWithOtherEntities() {
        //Given
        Reader reader = new Reader("Krzysztof", "Zakrocki");
        BookBorrowing bookBorrowing = new BookBorrowing(new Date());
        List<BookBorrowing> bookBorrowingList = new ArrayList<>();
        bookBorrowingList.add(bookBorrowing);
        reader.setBookBorrowingList(bookBorrowingList);
        bookBorrowing.setReader(reader);
        //When
        readerDao.save(reader);
        bookBorrowingDao.save(bookBorrowing);
        long id = reader.getReaderId();
        Optional<Reader> readerInTest = readerDao.findById(id);
        //Then
        assertTrue(readerInTest.isPresent());
        assertEquals(new Long(id), readerInTest.get().getReaderId());
        assertEquals("Zakrocki", readerInTest.get().getLastname());
        assertEquals(bookBorrowing.getBorrowingId(), readerInTest.get().getBookBorrowingList().get(0).getBorrowingId());
        //CleanUp
        readerDao.deleteById(id);
        bookBorrowingDao.deleteById(bookBorrowing.getBorrowingId());
    }
}
