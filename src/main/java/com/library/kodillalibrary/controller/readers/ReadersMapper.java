package com.library.kodillalibrary.controller.readers;

import com.library.kodillalibrary.domain.reader.Reader;
import com.library.kodillalibrary.domain.reader.ReaderDto;
import org.springframework.stereotype.Component;

@Component
public class ReadersMapper {

    public ReaderDto mapToDto(Reader reader) {
        return new ReaderDto(
                reader.getReaderId(),
                reader.getFirstname(),
                reader.getLastname(),
                reader.getAccountCreateDate(),
                reader.getBookBorrowingList()
        );
    }

    public Reader mapToReader(ReaderDto readerDto) {
        Reader reader = new Reader(
                readerDto.getFirstname(),
                readerDto.getLastname()
        );
        reader.setReaderId(readerDto.getReaderId());
        if(readerDto.getAccountCreateDate() != null){
            reader.setAccountCreateDate(readerDto.getAccountCreateDate());
        }
        reader.setBookBorrowingList(readerDto.getBookBorrowingList());

        return reader;
    }
}
