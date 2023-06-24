package com.board.mvc.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.board.mvc.dto.page.PageRequestDTO;
import com.board.mvc.dto.reply.ReplyCreateDTO;
import com.board.mvc.dto.reply.ReplyDTO;
import com.board.mvc.dto.reply.ReplyUpdateDTO;

// ReplyMapper interface 
@Mapper
public interface ReplyMapper {
    
    // Create Reply
    int createReply(ReplyCreateDTO replyCreateDTO);

    // Create ReplyChild
    int createReplyChild(ReplyCreateDTO replyCreateDTO);

    // Read Reply
    ReplyDTO readReply(Long rno);

    // Delete Reply
    int deleteReply(Long rno);

    // List Reply
    List<ReplyDTO> listReply(@Param("page") PageRequestDTO pageRequestDTO, @Param("tno") Long tno);

    //  댓글 대댓글 구분을 위한 gno update  
    int updateGno(Long gno);

    // Total Reply 
    int totalReply(Long tno);

    // Update Reply
    int updateReply(ReplyUpdateDTO replyUpdateDTO);

    // Increment Reply Count 
    int incrementReplyCount(Long tno);

    // Decrement Reply Count 
    int decrementReplyCount(Long tno);
}
