package com.library.kodillalibrary.domain;

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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitlesTestSuite {

    @Autowired
    public TitlesDao titleDao;

    @Autowired
    public BooksDao bookDao;

    @Test
    public void testTitlesSave() {
        //Given
        Titles title = new Titles("Lord of The Rings", "Tolkien", 1950);
        //When
        titleDao.save(title);
        long id = title.getTitleId();
        Optional<Titles> titleInTest = titleDao.findById(id);
        //Then
        assertTrue(titleInTest.isPresent());
        //CleanUp
        titleDao.deleteById(id);
    }

    @Test
    public void testTitleEntityWithOtherEntities() {
        //Given
        Titles title = new Titles("Lord of The Rings", "Tolkien", 1950);
        Books book = new Books("Borrowed");
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        book.setTitle(title);
        title.setBooksList(booksList);
        //When
        bookDao.save(book);
        titleDao.save(title);
        long titleId = title.getTitleId();
        Optional<Titles> titleInTest = titleDao.findById(titleId);
        //Then
        assertTrue(titleInTest.isPresent());
        assertEquals("Tolkien", titleInTest.get().getAuthor());
        assertEquals(1950, bookDao.findById(book.getBookId()).get().getTitle().getYearOfPublication());
        assertEquals("Borrowed", titleDao.findById(titleId).get().getBooksList().get(0).getStatus());
        //CleanUp
        titleDao.deleteById(titleId);
    }
}
