package com.library.kodillalibrary.domain.readers.dao;

import com.library.kodillalibrary.domain.readers.Readers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ReadersDao extends CrudRepository<Readers, Long> {
}
