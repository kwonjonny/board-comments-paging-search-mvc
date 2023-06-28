package com.board.mvc.dto.reply;

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
public class ReplyDTO {
    private Long rno;
    private Long tno;
    private String reply;
    private String replyer;
    private LocalDate replyDate;
    private LocalDate modifyDate;
    private Long isDeleted;

    @Builder.Default
    private Long gno = 0L;
    private int step;
}
