package com.library.kodillalibrary.controller.titles;

import com.library.kodillalibrary.domain.title.Title;
import com.library.kodillalibrary.domain.title.dao.TitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbTitleService {

    @Autowired
    private TitleDao titleDao;

    public Title addTitle(Title title) {
        return titleDao.save(title);
    }
}
