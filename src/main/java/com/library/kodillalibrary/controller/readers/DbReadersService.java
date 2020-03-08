package com.library.kodillalibrary.controller.readers;

import com.library.kodillalibrary.domain.readers.Readers;
import com.library.kodillalibrary.domain.readers.dao.ReadersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbReadersService {

    @Autowired
    private ReadersDao readerDao;

    public Readers saveReader(Readers reader) {
        return readerDao.save(reader);
    }
}
