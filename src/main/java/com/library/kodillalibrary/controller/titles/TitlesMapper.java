package com.library.kodillalibrary.controller.titles;

import com.library.kodillalibrary.domain.title.Title;
import com.library.kodillalibrary.domain.title.TitleDto;
import org.springframework.stereotype.Component;

@Component
public class TitlesMapper {

    public Title mapToTitle(TitleDto titleDto) {
        Title title = new Title(
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getYearOfPublication()
        );
        title.setTitleId(titleDto.getTitleId());
        return title;
    }

    public TitleDto mapToDto(Title title) {
        return new TitleDto(
            title.getTitleId(),
            title.getTitle(),
            title.getAuthor(),
            title.getYearOfPublication()
        );
    }
}
