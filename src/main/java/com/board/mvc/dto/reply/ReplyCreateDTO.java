package com.board.mvc.dto.reply;

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
public class ReplyCreateDTO {
    private Long rno;
    private Long tno;
    private String reply;
    private String replyer;
    private String replyDate;
    private Long gno;
}
