package com.library.kodillalibrary.domain.book.dao;

import com.library.kodillalibrary.domain.book.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BookDao extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();
}
