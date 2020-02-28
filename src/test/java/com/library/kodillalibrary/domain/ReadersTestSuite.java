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
    public ReadersDao readersDao;

    @Autowired
    public BooksBorrowingDao booksBorrowingDao;

    @Test
    public void testReadersSave() {
        //Given
        Readers reader = new Readers("Krzysztof", "Zakrocki");
        //When
        readersDao.save(reader);
        long id = reader.getReaderId();
        Optional<Readers> readerInTest = readersDao.findById(id);
        //Then
        assertTrue(readerInTest.isPresent());
        //CleanUp
        readersDao.deleteById(id);
    }

    @Test
    public void testReadersEntityWithOtherEntities() {
        //Given
        Readers reader = new Readers("Krzysztof", "Zakrocki");
        BooksBorrowing bookBorrowing = new BooksBorrowing(new Date() , new Date());
        List<BooksBorrowing> booksBorrowingList = new ArrayList<>();
        booksBorrowingList.add(bookBorrowing);
        reader.setBooksBorrowingList(booksBorrowingList);
        bookBorrowing.setReaders(reader);
        //When
        readersDao.save(reader);
        booksBorrowingDao.save(bookBorrowing);
        long id = reader.getReaderId();
        Optional<Readers> readerInTest = readersDao.findById(id);
        //Then
        assertTrue(readerInTest.isPresent());
        assertEquals(new Long(id), readerInTest.get().getReaderId());
        assertEquals("Zakrocki", readerInTest.get().getLastname());
        assertEquals(bookBorrowing.getBorrowingId(), readerInTest.get().getBooksBorrowingList().get(0).getBorrowingId());
        //CleanUp
        readersDao.deleteById(id);
        booksBorrowingDao.deleteById(bookBorrowing.getBorrowingId());
    }
}
