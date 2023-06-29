package com.board.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.mvc.dto.page.PageRequestDTO;
import com.board.mvc.dto.page.PageResponseDTO;
import com.board.mvc.dto.reply.ReplyCreateDTO;
import com.board.mvc.dto.reply.ReplyDTO;
import com.board.mvc.dto.reply.ReplyUpdateDTO;
import com.board.mvc.mappers.ReplyMapper;

import lombok.extern.log4j.Log4j2;

// ReplyService implemetns class 
@Service
public class ReplyServiceImpl implements ReplyService {

    // 의존성 주입 
    private final ReplyMapper replyMapper;

    // Autowired 명시적 표시 
    @Autowired
    public ReplyServiceImpl(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }


    // Create ReplyServiceImpl
    @Override
    @Transactional
    public Long createReply(ReplyCreateDTO replyCreateDTO) {
        Long result = null;
        Long gno = replyCreateDTO.getGno();

        if(gno == null || gno == 0) {
            int count = replyMapper.createReply(replyCreateDTO);
            if(count != 1) {
                throw new RuntimeException("insert error child");
            }
            Long rno = replyCreateDTO.getRno();
            replyMapper.updateGno(rno); 
            result = rno; 
            replyMapper.incrementReplyCount(replyCreateDTO.getTno());
        } else {
            int count = replyMapper.createReplyChild(replyCreateDTO);
            if(count != 1) {
                throw new RuntimeException("insert error child");
            }
            result = replyCreateDTO.getRno();
            replyMapper.incrementReplyCount(replyCreateDTO.getTno());
        }
        return result;
    }

    // Read ReplyServiceImpl
    @Override
    @Transactional
    public ReplyDTO readReply(Long rno) {
        return replyMapper.readReply(rno);
    }

    // List ReplyServiceImpl
    @Override
    @Transactional
    public PageResponseDTO<ReplyDTO> listReply(PageRequestDTO pageRequestDTO, Long tno) {
        List<ReplyDTO> list = replyMapper.listReply(pageRequestDTO, tno);
        int total = replyMapper.totalReply(tno);
        
        return PageResponseDTO.<ReplyDTO>withAll()
        .list(list)
        .total(total)
        .build();
    }

    // Delete ReplySerivceImpl
    @Override
    @Transactional
    public int deleteReply(Long rno) {
       ReplyDTO replyDTO = replyMapper.readReply(rno);
        if (replyDTO == null) {
            throw new RuntimeException("Reply not found for rno: " + rno);
        }
        Long tno = replyDTO.getTno();
        replyMapper.deleteReply(rno);
        return replyMapper.decrementReplyCount(tno);
    }

    // Update ReplyServiceImpl
    @Override
    @Transactional
    public int updateReply(ReplyUpdateDTO replyUpdateDTO) {
        return replyMapper.updateReply(replyUpdateDTO);
    } 
}
