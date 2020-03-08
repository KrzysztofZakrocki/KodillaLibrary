package com.library.kodillalibrary.controller.titles;

import com.library.kodillalibrary.domain.titles.Titles;
import com.library.kodillalibrary.domain.titles.TitlesDto;
import org.springframework.stereotype.Component;

@Component
public class TitlesMapper {

    public Titles mapToTitle(TitlesDto titleDto) {
        Titles title = new Titles(
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getYearOfPublication()
        );
        title.setTitleId(titleDto.getTitleId());
        return title;
    }

    public TitlesDto mapToDto(Titles title) {
        return new TitlesDto(
            title.getTitleId(),
            title.getTitle(),
            title.getAuthor(),
            title.getYearOfPublication()
        );
    }
}
