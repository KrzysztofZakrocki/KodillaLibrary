package com.library.kodillalibrary.controller.readers;

import com.library.kodillalibrary.domain.reader.Reader;
import com.library.kodillalibrary.domain.reader.dao.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbReadersService {

    @Autowired
    private ReaderDao readerDao;

    public Reader saveReader(Reader reader) {
        return readerDao.save(reader);
    }
}
