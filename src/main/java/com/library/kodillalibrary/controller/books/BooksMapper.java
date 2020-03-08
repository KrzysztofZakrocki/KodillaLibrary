package com.library.kodillalibrary.controller.books;

import com.library.kodillalibrary.domain.books.Books;
import com.library.kodillalibrary.domain.books.BooksDto;
import com.library.kodillalibrary.domain.titles.Titles;
import com.library.kodillalibrary.domain.titles.dao.TitlesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BooksMapper {

    @Autowired
    private TitlesDao titlesDao;

    public Books mapToBook(BooksDto bookDto) {
        Books book = new Books(
            bookDto.getStatus()
        );
        List<Titles> titlesList = titlesDao.findAll().stream().
                filter(t -> t.getTitle().equals(bookDto.getTitle()))
                .collect(Collectors.toList());

        book.setTitle(titlesList.get(0));
        book.setBooksBorrowing(bookDto.getBooksBorrowing());

        return book;
    }

    public BooksDto mapToDto(Books book) {
        return new BooksDto(
          book.getStatus(),
          book.getTitle().getTitle(),
          book.getBooksBorrowing()
        );
    }
}
