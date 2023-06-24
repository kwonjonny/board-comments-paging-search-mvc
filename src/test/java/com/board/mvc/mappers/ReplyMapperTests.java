package com.board.mvc.mappers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.board.mvc.dto.page.PageRequestDTO;
import com.board.mvc.dto.reply.ReplyCreateDTO;
import com.board.mvc.dto.reply.ReplyDTO;
import com.board.mvc.dto.reply.ReplyUpdateDTO;

import lombok.extern.log4j.Log4j2;

// Board Reply ,ReplyChild Mapper Test
@Log4j2
@SpringBootTest
public class ReplyMapperTests {
    
    // 의존성 주입 
    @Autowired(required = false)
    private ReplyMapper replyMapper;

    private static final String TEST_REPLY = "Junit Test Reply Mapper";
    private static final String TEST_REPLYER = "Junit Test Replyer Mapper";

    private static final String TEST_REPLY_CHILD = "Junit Test Reply Child Mapper";
    private static final String TEST_REPLYER_CHILD = "Junit Test Replyer Child Mapper";

    private static final Long TEST_GNO = 1L;    
    private static final Long TEST_TNO = 1L;
    private static final Long TEST_RNO = 1L;

    private ReplyCreateDTO replyCreateDTO;
    private ReplyCreateDTO replyCreateChildDTO;
    private ReplyUpdateDTO replyUpdateDTO;
    private ReplyUpdateDTO replyUpdateChildDTO;

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

    // Create Reply 
    @Test
    @Transactional
    @DisplayName("게시판 댓글 생성 메퍼 테스트")
    public void createReplyTest() {
        // GIVEN 
        log.info("========== Start Create Reply Mapper Test ==========");
        // WHEN
        int insertCount = replyMapper.createReply(replyCreateDTO);
        // THEN
        Assertions.assertEquals(1, insertCount, "Create Reply Should Be Successful");
        if(insertCount != 1) {
            throw new RuntimeException("Create Failed");
        }
        // 댓글 대댓글 구분을 위한 Create 
        Long rno = replyCreateDTO.getRno();
        replyMapper.updateGno(rno);

        // Create Check 
        ReplyDTO readReply = replyMapper.readReply(rno);
        log.info(readReply);

        // tbl_baord replyCnt Update
        replyMapper.incrementReplyCount(TEST_TNO);

        log.info("========== End Create Reply Mapper Test ==========");
    }

    // Create Reply Child 
    @Test
    @Transactional
    @DisplayName("게시판 대댓글 생성 매퍼 테스트")
    public void createReplyChildTest() {
        // GIVEN
        log.info("========== Start Create ReplyChild Mapper Test ==========");
        // WHEN
        int insertCount = replyMapper.createReplyChild(replyCreateChildDTO);
        // THEN 
        Assertions.assertEquals(1, insertCount, "Create ReplyChild Shold Be Successful");
        log.info(replyCreateChildDTO.getGno());

        // Create Check 
        ReplyDTO readReplyChild = replyMapper.readReply(TEST_RNO);
        log.info(readReplyChild);

        // tbl_board replyCnt Update
        replyMapper.incrementReplyCount(TEST_TNO);

        log.info("========== End Create ReplyChild Mapper Test ==========");
    }

    // Read Reply 
    @Test
    @Transactional
    @DisplayName("게시판 댓글 조회 매퍼 테스트")
    public void readReplyTest() {
        // GIVEN
        log.info("========== Start Read Reply Mapper Test ==========");
        // WHEN
        ReplyDTO replyDTO = replyMapper.readReply(TEST_RNO);
        // THEN
        log.info(replyDTO);
        log.info("========== End Read Reply Mapper Test ==========");
    }

    // List Reply   
    @Test
    @Transactional
    @DisplayName("게시판 리스트 조회 매퍼 테스트")
    public void listReplyTest() {
        // GIVEN
        log.info("========== Start List Reply Mapper Test ==========");
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        // WHEN 
        List<ReplyDTO> list = replyMapper.listReply(pageRequestDTO, TEST_TNO);
        // THEN 
        log.info(list);
        log.info("========== End List Reply Mapper Test ==========");
    }

    // Delete Reply
    @Test
    @Transactional
    @DisplayName("게시판 삭제 매퍼 테스트")
    public void delteReplyTest() {
        // GIVEN
        log.info("========== Start Delete Reply Mapper Test ==========");
        // WHEN
        replyMapper.deleteReply(TEST_RNO);

        // THEN
        // tbl_board replyCnt Update 
        replyMapper.decrementReplyCount(TEST_TNO);
        log.info("========== End Delete Reply Mapper Test ==========");
    }

    // Update Reply
    @Test
    @Transactional
    @DisplayName("게시판 댓글 업데이트 매퍼 테스트")
    public void updateReplyTest() {
        // GIVEN
        log.info("========== Start Update Reply Mapper Test ==========");
        // WHEN
        replyMapper.updateReply(replyUpdateDTO);

        // THEN
        ReplyDTO updatedReply = replyMapper.readReply(TEST_RNO);
        log.info(updatedReply);
        log.info("========== End Update Reply Mapper Test ==========");
    }

    // Update Reply Child
    @Test
    @Transactional
    @DisplayName("게시판 대댓글 업데이트 매퍼 테스트")
    public void updateReplyChildTest() {
        // GIVEN
        log.info("========== Start Update Reply Child Mapper TEst ==========");
        // WHEN
        replyMapper.updateReply(replyUpdateChildDTO);

        // THEN
        ReplyDTO updatedReplyChild = replyMapper.readReply(TEST_RNO);
        log.info(updatedReplyChild);
        log.info("========== End Update Reply Child Mapper Test ==========");
    }
}
