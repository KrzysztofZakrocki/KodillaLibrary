package com.library.kodillalibrary.domain.bookBorrowing.dao;

import com.library.kodillalibrary.domain.bookBorrowing.BooksBorrowing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BooksBorrowingDao extends CrudRepository<BooksBorrowing, Long> {

}
