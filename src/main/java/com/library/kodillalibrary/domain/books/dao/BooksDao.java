package com.library.kodillalibrary.domain.books.dao;

import com.library.kodillalibrary.domain.books.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BooksDao extends CrudRepository<Books, Long> {
}