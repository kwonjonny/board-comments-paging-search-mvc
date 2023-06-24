package com.board.mvc.service;

import org.apache.ibatis.annotations.Param;

import com.board.mvc.dto.page.PageRequestDTO;
import com.board.mvc.dto.page.PageResponseDTO;
import com.board.mvc.dto.reply.ReplyCreateDTO;
import com.board.mvc.dto.reply.ReplyDTO;
import com.board.mvc.dto.reply.ReplyUpdateDTO;

// ReplyService interface
public interface ReplyService {
    
    // Create Reply Service
    Long createReply(ReplyCreateDTO replyCreateDTO);

    // Read Reply Service
    ReplyDTO readReply(Long rno);

    // List Reply Service
    PageResponseDTO<ReplyDTO> listReply(@Param("page") PageRequestDTO pageRequestDTO, @Param("tno") Long tno);

    // Delete Reply Service
    int deleteReply(Long rno);

    // Update Reply Service 
    int updateReply(ReplyUpdateDTO replyUpdateDTO);
}
