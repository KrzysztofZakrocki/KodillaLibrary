package com.library.kodillalibrary.controller;

import com.library.kodillalibrary.controller.books.BookNotFoundException;
import com.library.kodillalibrary.controller.books.BooksMapper;
import com.library.kodillalibrary.controller.books.DbBooksService;
import com.library.kodillalibrary.controller.readers.DbReadersService;
import com.library.kodillalibrary.controller.readers.ReadersMapper;
import com.library.kodillalibrary.controller.readers.ReaderNotFoundException;
import com.library.kodillalibrary.controller.titles.DbTitleService;
import com.library.kodillalibrary.controller.titles.TitlesMapper;
import com.library.kodillalibrary.domain.book.BookDto;
import com.library.kodillalibrary.domain.reader.ReaderDto;
import com.library.kodillalibrary.domain.title.TitleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    @Autowired
    private DbTitleService titleService;
    @Autowired
    private DbReadersService readerService;
    @Autowired
    private DbBooksService bookService;

    @Autowired
    private TitlesMapper titleMapper;
    @Autowired
    private ReadersMapper readerMapper;
    @Autowired
    private BooksMapper bookMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/readers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReaderDto addReader(@RequestBody ReaderDto readerDto) {
        return readerMapper.mapToDto(readerService.saveReader(readerMapper.mapToReader(readerDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/titles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleDto addTitle(@RequestBody TitleDto titleDto) {
        return titleMapper.mapToDto(titleService.addTitle(titleMapper.mapToTitle(titleDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookDto addBook(@RequestBody BookDto bookDto) {
       return bookMapper.mapToDto(bookService.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/books/{bookId}/status/{status}")
    public void changeBookStatus(@PathVariable Long bookId, @PathVariable String status) throws BookNotFoundException {
        bookService.changeBookStatus(bookId, status);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{bookTitle}")
    public Long getNumberOfCopiesOfTheBook(@PathVariable String bookTitle) {
        return bookService.getNumberOfCopiesOfTheBook(bookTitle);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/bookBorrowing/{bookTitle}/reader/{readerId}")
    public void bookBorrowing(@PathVariable String bookTitle, @PathVariable Long readerId) throws BookNotFoundException, ReaderNotFoundException {
        bookService.bookBorrowing(bookTitle, readerId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/bookReturn/{bookTitle}/reader/{readerId}")
    public void bookReturn(@PathVariable String bookTitle, @PathVariable Long readerId) throws BookNotFoundException {
        bookService.bookReturn(bookTitle, readerId);
    }
}
