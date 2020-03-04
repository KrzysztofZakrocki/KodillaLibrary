package com.library.kodillalibrary.controller.titles;

import com.library.kodillalibrary.domain.titles.Titles;
import com.library.kodillalibrary.domain.titles.dao.TitlesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbTitleService {

    @Autowired
    private TitlesDao titlesDao;

    public Titles addTitle(Titles title) {
        return titlesDao.save(title);
    }
}
