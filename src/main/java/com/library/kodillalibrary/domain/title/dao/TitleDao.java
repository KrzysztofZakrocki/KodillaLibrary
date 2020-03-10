package com.library.kodillalibrary.domain.title.dao;

import com.library.kodillalibrary.domain.title.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TitleDao extends CrudRepository<Title, Long> {

    @Override
    List<Title> findAll();
}
