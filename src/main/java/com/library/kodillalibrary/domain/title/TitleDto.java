package com.library.kodillalibrary.domain.title;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TitleDto {

    private Long titleId;
    private String title;
    private String author;
    private int yearOfPublication;
}
