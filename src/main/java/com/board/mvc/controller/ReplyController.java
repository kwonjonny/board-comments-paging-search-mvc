package com.board.mvc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.mvc.dto.page.PageRequestDTO;
import com.board.mvc.dto.page.PageResponseDTO;
import com.board.mvc.dto.reply.ReplyCreateDTO;
import com.board.mvc.dto.reply.ReplyDTO;
import com.board.mvc.dto.reply.ReplyUpdateDTO;
import com.board.mvc.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/replies/")
public class ReplyController {
    
    // 의존성 주입 
    private final ReplyService replyService;

    // Autowried 명시적 표시 
    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    // List Reply
    @GetMapping("{tno}/list")
    public PageResponseDTO<ReplyDTO> getReplyList(@PathVariable("tno") Long tno, PageRequestDTO pageRequestDTO) {
        log.info("RestController : ReplyList");
        return replyService.listReply(pageRequestDTO, tno);
    }

    // Create Reply & Create Reply Child 
    @PostMapping("{tno}/new")
    public Map<String, Long> createReply(@PathVariable("tno") Long tno, @RequestBody ReplyCreateDTO replyCreateDTO) {
        log.info("RestController : ReplyCreate");

        log.info(replyCreateDTO + "ReplyCreateDTO");

        replyCreateDTO.setTno(tno);
        Long rno = replyService.createReply(replyCreateDTO);
        return Map.of("result", rno);
    }

    // Read Reply
    @GetMapping("{rno}")
    public ReplyDTO readReply(@PathVariable("rno") Long rno) {
        log.info("RestController : ReplyRead");
        ReplyDTO replyDTO = replyService.readReply(rno);
        return replyDTO;
    }

    // Delete Reply 
    @DeleteMapping("{rno}")
    public Map<String, Integer> deleteReply(@PathVariable("rno") Long rno) {
        log.info("RestContoller : ReplyDelete");
        int result = replyService.deleteReply(rno);
        return Map.of("result", result);
    }

    // Update Reply
    @PutMapping("update")
    public Map<String, Integer> updateReply(@RequestBody ReplyUpdateDTO replyUpdateDTO) {
        log.info("RestController : ReplyUpdate");
        int result = replyService.updateReply(replyUpdateDTO);
        return Map.of("result", result);
    }
}
