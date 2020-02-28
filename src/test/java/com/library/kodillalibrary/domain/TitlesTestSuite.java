package com.library.kodillalibrary.domain;

import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import com.library.kodillalibrary.domain.books.Books;
import com.library.kodillalibrary.domain.books.dao.BooksDao;
import com.library.kodillalibrary.domain.readers.Readers;
import com.library.kodillalibrary.domain.titles.Titles;
import com.library.kodillalibrary.domain.titles.dao.TitlesDao;
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
public class TitlesTestSuite {

    @Autowired
    public TitlesDao titlesDao;

    @Autowired
    public BooksDao booksDao;

    @Test
    public void testTitlesSave() {
        //Given
        Titles title = new Titles("Lord of The Rings", "Tolkien", 1950);
        //When
        titlesDao.save(title);
        long id = title.getTitleId();
        Optional<Titles> titleInTest = titlesDao.findById(id);
        //Then
        assertTrue(titleInTest.isPresent());
        //CleanUp
        titlesDao.deleteById(id);
    }

    @Test
    public void testTitleEntityWithOtherEntities() {
        //Given
        Titles title = new Titles("Lord of The Rings", "Tolkien", 1950);
        Books book = new Books("Borrowed");
        book.setTitle(title);
        //When
        booksDao.save(book);
        titlesDao.save(title);
        long titleId = title.getTitleId();
        Optional<Titles> titleInTest = titlesDao.findById(titleId);
        //Then
        assertTrue(titleInTest.isPresent());
        assertEquals("Tolkien", titleInTest.get().getAuthor());
        assertEquals(1950, booksDao.findById(book.getBookId()).get().getTitle().getYearOfPublication());
        //CleanUp
        titlesDao.deleteById(titleId);
    }
}
