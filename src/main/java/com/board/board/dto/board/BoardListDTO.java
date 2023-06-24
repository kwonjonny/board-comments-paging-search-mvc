package com.board.board.dto.board;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardListDTO {
    private Long tno;
    private String title;
    private String content;
    private String writer;
    private LocalDate registDate;
    private LocalDate updateDate;
}
