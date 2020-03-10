package com.library.kodillalibrary.domain;

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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitleTestSuite {

    @Autowired
    public TitleDao titleDao;

    @Autowired
    public BookDao bookDao;

    @Test
    public void testTitlesSave() {
        //Given
        Title title = new Title("Lord of The Rings", "Tolkien", 1950);
        //When
        titleDao.save(title);
        long id = title.getTitleId();
        Optional<Title> titleInTest = titleDao.findById(id);
        //Then
        assertTrue(titleInTest.isPresent());
        //CleanUp
        titleDao.deleteById(id);
    }

    @Test
    public void testTitleEntityWithOtherEntities() {
        //Given
        Title title = new Title("Lord of The Rings", "Tolkien", 1950);
        Book book = new Book("Borrowed");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        book.setTitle(title);
        title.setBookList(bookList);
        //When
        bookDao.save(book);
        titleDao.save(title);
        long titleId = title.getTitleId();
        Optional<Title> titleInTest = titleDao.findById(titleId);
        //Then
        assertTrue(titleInTest.isPresent());
        assertEquals("Tolkien", titleInTest.get().getAuthor());
        assertEquals(1950, bookDao.findById(book.getBookId()).get().getTitle().getYearOfPublication());
        assertEquals("Borrowed", titleDao.findById(titleId).get().getBookList().get(0).getStatus());
        //CleanUp
        titleDao.deleteById(titleId);
    }
}
