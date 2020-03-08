package com.library.kodillalibrary.domain;

import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import com.library.kodillalibrary.domain.bookBorrowing.dao.BooksBorrowingDao;
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
public class ReadersTestSuite {

    @Autowired
    public ReadersDao readerDao;

    @Autowired
    public BooksBorrowingDao bookBorrowingDao;

    @Test
    public void testReadersSave() {
        //Given
        Readers reader = new Readers("Krzysztof", "Zakrocki");
        //When
        readerDao.save(reader);
        long id = reader.getReaderId();
        Optional<Readers> readerInTest = readerDao.findById(id);
        //Then
        assertTrue(readerInTest.isPresent());
        //CleanUp
        readerDao.deleteById(id);
    }

    @Test
    public void testReadersEntityWithOtherEntities() {
        //Given
        Readers reader = new Readers("Krzysztof", "Zakrocki");
        BooksBorrowing bookBorrowing = new BooksBorrowing(new Date());
        List<BooksBorrowing> booksBorrowingList = new ArrayList<>();
        booksBorrowingList.add(bookBorrowing);
        reader.setBooksBorrowingList(booksBorrowingList);
        bookBorrowing.setReaders(reader);
        //When
        readerDao.save(reader);
        bookBorrowingDao.save(bookBorrowing);
        long id = reader.getReaderId();
        Optional<Readers> readerInTest = readerDao.findById(id);
        //Then
        assertTrue(readerInTest.isPresent());
        assertEquals(new Long(id), readerInTest.get().getReaderId());
        assertEquals("Zakrocki", readerInTest.get().getLastname());
        assertEquals(bookBorrowing.getBorrowingId(), readerInTest.get().getBooksBorrowingList().get(0).getBorrowingId());
        //CleanUp
        readerDao.deleteById(id);
        bookBorrowingDao.deleteById(bookBorrowing.getBorrowingId());
    }
}
