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

    public Readers mapToReaders(ReadersDto readersDto) {
        Readers reader = new Readers(
                readersDto.getFirstname(),
                readersDto.getLastname()
        );
        reader.setReaderId(readersDto.getReaderId());
        reader.setAccountCreateDate(readersDto.getAccountCreateDate());
        reader.setBooksBorrowingList(readersDto.getBooksBorrowingList());

        return reader;
    }
}
