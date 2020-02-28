package com.library.kodillalibrary.domain.titles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TitlesDto {

    private Long titleId;
    private String title;
    private String author;
    private int yearOfPublication;
}
