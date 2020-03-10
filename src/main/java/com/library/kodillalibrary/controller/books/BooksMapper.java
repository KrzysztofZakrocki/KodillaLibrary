package com.library.kodillalibrary.controller.books;

import com.library.kodillalibrary.domain.book.Book;
import com.library.kodillalibrary.domain.book.BookDto;
import com.library.kodillalibrary.domain.title.Title;
import com.library.kodillalibrary.domain.title.dao.TitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BooksMapper {

    @Autowired
    private TitleDao titleDao;

    public Book mapToBook(BookDto bookDto) {
        Book book = new Book(
            bookDto.getStatus()
        );
        List<Title> titleList = titleDao.findAll().stream().
                filter(t -> t.getTitle().equals(bookDto.getTitle()))
                .collect(Collectors.toList());

        book.setTitle(titleList.get(0));
        book.setBookBorrowing(bookDto.getBookBorrowing());

        return book;
    }

    public BookDto mapToDto(Book book) {
        return new BookDto(
          book.getStatus(),
          book.getTitle().getTitle(),
          book.getBookBorrowing()
        );
    }
}
