package com.library.kodillalibrary.controller.readers;

import com.library.kodillalibrary.domain.readers.Readers;
import com.library.kodillalibrary.domain.readers.ReadersDto;
import org.springframework.stereotype.Component;

@Component
public class ReadersMapper {

    public ReadersDto mapToDto(Readers reader) {
        return new ReadersDto(
                reader.getReaderId(),
                reader.getFirstname(),
                reader.getLastname(),
                reader.getAccountCreateDate(),
                reader.getBooksBorrowingList()
        );
    }

    public Readers mapToReader(ReadersDto readerDto) {
        Readers reader = new Readers(
                readerDto.getFirstname(),
                readerDto.getLastname()
        );
        reader.setReaderId(readerDto.getReaderId());
        if(readerDto.getAccountCreateDate() != null){
            reader.setAccountCreateDate(readerDto.getAccountCreateDate());
        }
        reader.setBooksBorrowingList(readerDto.getBooksBorrowingList());

        return reader;
    }
}
