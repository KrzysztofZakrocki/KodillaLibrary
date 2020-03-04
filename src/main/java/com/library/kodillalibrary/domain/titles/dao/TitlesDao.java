package com.library.kodillalibrary.domain.titles.dao;

import com.library.kodillalibrary.domain.titles.Titles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TitlesDao extends CrudRepository<Titles, Long> {

    @Override
    List<Titles> findAll();
}
