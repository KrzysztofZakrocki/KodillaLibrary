package com.library.kodillalibrary.controller.titles;

import com.library.kodillalibrary.domain.titles.Titles;
import com.library.kodillalibrary.domain.titles.TitlesDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TitlesMapper {

    public Titles mapToTitles(TitlesDto titlesDto) {
        Titles titles = new Titles(
                titlesDto.getTitle(),
                titlesDto.getAuthor(),
                titlesDto.getYearOfPublication()
        );
        titles.setTitleId(titlesDto.getTitleId());
        return titles;
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
