package com.library.kodillalibrary.domain.bookBorrowing.dao;

import com.library.kodillalibrary.domain.bookBorrowing.BookBorrowing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BookBorrowingDao extends CrudRepository<BookBorrowing, Long> {

    @Override
    List<BookBorrowing> findAll();
}
