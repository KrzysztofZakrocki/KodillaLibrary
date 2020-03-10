package com.library.kodillalibrary.domain.reader.dao;

import com.library.kodillalibrary.domain.reader.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ReaderDao extends CrudRepository<Reader, Long> {
}
