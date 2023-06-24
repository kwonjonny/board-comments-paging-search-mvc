package com.board.mvc.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.board.mvc.dto.board.BoardCreateDTO;
import com.board.mvc.dto.board.BoardDTO;
import com.board.mvc.dto.board.BoardUpdateDTO;
import com.board.mvc.dto.page.PageRequestDTO;

import lombok.extern.log4j.Log4j2;

// Board Mapper Test Class 
@Log4j2
@SpringBootTest
public class BoardMapperTests {
    

    // 의존성 주입 
    @Autowired(required = false)
    private BoardMapper boardMapper;


    private static final String TEST_TITLE = "JunitTestTitleMapper";
    private static final String TEST_WRITER = "JunitTestWriterMapper";
    private static final String TEST_CONTENT = "JunitTestContentMapper";
    private static final Long TEST_TNO = 1L;


    // BeforeEach 사용을 위한 BoardCreateDTO , BoardUpdateDTO 정의 
    private BoardCreateDTO boardCreateDTO;
    private BoardUpdateDTO boardUpdateDTO;


    // BoardMapper Create Test Set Up
    // BoardMapper Update TEst Set Up
    @BeforeEach
    public void setUp() {
       boardCreateDTO = BoardCreateDTO.builder()
       .title(TEST_TITLE)
       .writer(TEST_WRITER)
       .content(TEST_CONTENT)
       .build();

       boardUpdateDTO = BoardUpdateDTO.builder()
       .tno(TEST_TNO)
       .title(TEST_TITLE)
       .writer(TEST_WRITER)
       .content(TEST_CONTENT)
       .build();
    }

    // BoardMapper Create Test 
    @Test
    @Transactional
    @DisplayName("생성 테스트 게시판 매퍼")
    public void createBoardMapperTest() {
        // GIEVEN
        log.info("========== Start Create Board Mapper ==========");
        // WHEN
        int insertCount = boardMapper.createBoard(boardCreateDTO);
        Assertions.assertEquals(1, insertCount,"create board test should be sucessful");
        // THEN
        BoardDTO boardDTO = boardMapper.readBoard(TEST_TNO);
        log.info(boardDTO);
        log.info("========== End Create Board Mapper Test ==========");
    }

    // BoardMapper List Test 
    @Test
    @Transactional
    @DisplayName("리스트 테스트 게시판 매퍼")
    public void listBoardMapperTest() {
        // GIVEN
        log.info("========== Start List Board Mapper ==========");
        // WHEN 
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        // THEN 
        log.info(boardMapper.listBoard(pageRequestDTO));
        log.info("========== End List Board Mapper Test ==========");
    }

    // BoardMapper Read Test
    @Test
    @Transactional
    @DisplayName("조회 테스트 게시판 매퍼")
    public void readBoardMapperTest() {
        // GIVEN 
        log.info("========== Start Read Board Mapper ==========");
        // WHEN 
        BoardDTO boardDTO = boardMapper.readBoard(TEST_TNO);
        // THEN 
        log.info(boardDTO);
        Assertions.assertNotNull(boardDTO , "boardDTO should be not null");
        log.info("========== End Read Board Mapper Test ==========");
    }

    // BoardMapper Delete Test
    @Test
    @Transactional
    @DisplayName("삭제 테스트 게시판 매퍼")
    public void deleteBoardMapperTest() {
        // GIVEN
        log.info("========== Start Delete Board Mapper ==========");
        // WHEN 
        boardMapper.deleteBoard(TEST_TNO);
        // THEN 
        BoardDTO boardDTO = boardMapper.readBoard(TEST_TNO);
        Assertions.assertNull(boardDTO, "deletedBoard Should Be Null");
        log.info("========== End Delete Board Mapper ==========");
    }

    // BoardMapper Update Test 
    @Test
    @Transactional
    @DisplayName("업데이트 테스트 게시판 매퍼")
    public void updateBoardMapperTest() {
        // GIVEN 
        log.info("========== Start Update Board Mapper =========");
        // WHEN 
        boardMapper.updateBoard(boardUpdateDTO);
        // THEN 
        BoardDTO updateDTO = boardMapper.readBoard(TEST_TNO);
        Assertions.assertNotNull(updateDTO , "boardDTO should be update sucessful");
        log.info(updateDTO);
        log.info("========== End Update Board Mapper =========");
    }

    // BoardMapper Total Test
    @Test
    @Transactional
    @DisplayName("TotalCount 테스트 게시판 매퍼")
    public void totalBoardMapperTest() {
        // GIEVEN 
        log.info("========== Start Board Mapper Total ==========");
        // WHEN 
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        Integer totalCount = boardMapper.total(pageRequestDTO);
        // THEN 
        Assertions.assertNotNull(totalCount >= 0 , "Total count should be non-negative");
        log.info(totalCount);
        log.info("=========== End Board Mapper Total ==========");
    }
}
