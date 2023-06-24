package com.board.mvc.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.board.mvc.dto.page.PageRequestDTO;
import com.board.mvc.dto.page.PageResponseDTO;
import com.board.mvc.dto.reply.ReplyCreateDTO;
import com.board.mvc.dto.reply.ReplyDTO;
import com.board.mvc.dto.reply.ReplyUpdateDTO;

import lombok.extern.log4j.Log4j2;

// Board Reply, ReplyChild Service Test Class 
@Log4j2
@SpringBootTest
public class ReplyServiceTests {
    
    // 의존성 주입 
    @Autowired
    private ReplyService replyService;

    private static final String TEST_REPLY = "Junit Test Reply Service";
    private static final String TEST_REPLYER = "Junit Test Replyer Service";

    private static final String TEST_REPLY_CHILD = "Junit Test Reply Child Service";
    private static final String TEST_REPLYER_CHILD = "Junit Test Replyer Child Service";

    private static final Long TEST_GNO = 1L;    
    private static final Long TEST_TNO = 1L;
    private static final Long TEST_RNO = 23L;

    // BeforeEach 사용을 위한 ReplyCreatDTO, ReplyUpdateDTO Set Up
    private ReplyCreateDTO replyCreateDTO;
    private ReplyCreateDTO replyCreateChildDTO;
    private ReplyUpdateDTO replyUpdateDTO;
    private ReplyUpdateDTO replyUpdateChildDTO;

    // Reply Create Serivce Child Set Up
    // Reply Create Serivce Set Up 
    // Reply Update Service Child Set Up
    // Reply Update Service Set Up
    @BeforeEach
    public void setUp() {
        replyCreateDTO = ReplyCreateDTO.builder()
        .tno(TEST_TNO)
        .reply(TEST_REPLY)
        .replyer(TEST_REPLYER)
        .build();

        replyCreateChildDTO = ReplyCreateDTO.builder()
        .tno(TEST_TNO)
        .reply(TEST_REPLY_CHILD)
        .replyer(TEST_REPLYER_CHILD)
        .gno(TEST_GNO)
        .build();

        replyUpdateDTO = ReplyUpdateDTO.builder()
        .tno(TEST_TNO)
        .reply(TEST_REPLY)
        .replyer(TEST_REPLYER)
        .rno(TEST_RNO)
        .build();

        replyUpdateChildDTO = ReplyUpdateDTO.builder()
        .tno(TEST_TNO)
        .reply(TEST_REPLY_CHILD)
        .replyer(TEST_REPLYER_CHILD)
        .rno(TEST_RNO)
        .gno(TEST_GNO)
        .build();
    }

    // Create Reply Test Service 
    @Test
    @Transactional
    @DisplayName("게시판 댓글 생성 테스트")
    public void createReplyServiceTest() {
        // GIVEN 
        log.info("========== Start Create Reply Service Test ==========");
        // WHEN 
        Long insertCount = replyService.createReply(replyCreateDTO);
        // THEN 
        ReplyDTO replyDTO = replyService.readReply(TEST_RNO);
        Assertions.assertNotNull(insertCount, "Create Reply Test Should Be Successful");
        log.info(replyDTO);
        log.info("========= End Create Reply Service Test ==========");
    }

    // Create ReplyChild Test Service 
    @Test
    @Transactional
    @DisplayName("게시판 대댓글 생성 서비스 테스트")
    public void createReplyChildServiceTest() {
        // GIVEN 
        log.info("========== Start Create Reply Child Service Test ==========");
        // WHEN 
        Long insetCount = replyService.createReply(replyCreateChildDTO);
        // THEN 
        ReplyDTO replyDTO = replyService.readReply(TEST_TNO);
        Assertions.assertNotNull(insetCount, "Create Reply Child Test Should Be Successful");
        log.info(replyDTO);
        log.info("========= End Create Reply Child Service Test ==========");
    }

    // Read Reply Test Service
    @Test
    @Transactional
    @DisplayName("게시판 댓글 조회 서비스 테스트")
    public void readReplyServiceTest() {
        // GIVEN 
        log.info("========== Start Read Reply Service Test ==========");
        // WHEN 
        ReplyDTO replyDTO = replyService.readReply(TEST_RNO);
        // THEN 
        log.info(replyDTO);
        log.info("========== End Read Reply Service Test ==========");
    }

    // List Reply Test Service
    @Test
    @Transactional
    @DisplayName("게시판 댓글 리스트 서비스 테스트")
    public void listRpelyServiceTest() {
        // GIVEN 
        log.info("========== Start List Reply Service Test ==========");
        // WHEN 
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        Long tno = TEST_TNO;
        PageResponseDTO<ReplyDTO> list = replyService.listReply(pageRequestDTO, tno);
        // THEN 
        log.info(list.getList());
        log.info("========== End List Reply Service Test ==========");
    }

    // Update Reply Test Service
    @Test
    @Transactional
    @DisplayName("게시판 댓글 업데이트 서비스 테스트")
    public void updateReplyServiceTest() {
        // GIVEN 
        log.info("========== Start Update Reply Service Test ==========");
        // WHEN 
        replyService.updateReply(replyUpdateDTO);
        // THEN 
        ReplyDTO updatedReply = replyService.readReply(TEST_RNO);
        log.info(updatedReply);
        Assertions.assertNotNull(updatedReply);
        log.info("========== End Update Reply Service Test ==========");
    }

    // Update Reply Child Test Service
    @Test
    @Transactional
    @DisplayName("게시판 대댓글 업데이트 서비스 테스트")
    public void updateReplyChildServiceTest() {
        // GIVEN 
        log.info("========== Start Update Reply Child Service Test ==========");
        // WHEN 
        replyService.updateReply(replyUpdateChildDTO);
        // THEN
        ReplyDTO updatedReplyChild = replyService.readReply(TEST_RNO);
        log.info(updatedReplyChild);
        Assertions.assertNotNull(updatedReplyChild);
        log.info("========== End Update Reply Child Service Test ==========");
    }

    // Delete Reply Child Test Service
    @Test
    @Transactional
    @DisplayName("게시판 댓글 삭제 서비스 테스트")
    public void dleteReplyServiceTest() {
        // GIVEN 
        log.info("========== Start Delete Reply Service Test ==========");
        // WHEN 
        replyService.deleteReply(TEST_RNO);
        // THEN 
        ReplyDTO deletedReply = replyService.readReply(TEST_RNO);
        Assertions.assertNull(deletedReply, "deletedReply Should Be Null");
        log.info("========== End Delete Reply Service Test ==========");
    }
}
