package com.board.mvc.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.board.board.mappers.CheckTimeMapper;

import lombok.extern.log4j.Log4j2;

// 마이바티스 Connection Check 클래스 
@Log4j2
@SpringBootTest
public class CheckTimeMapperTests {
    
    // 의존성 주입 
    @Autowired
    private CheckTimeMapper checkTimeMapper;

    // MyBatis Connection Test
    @Test
    @Transactional
    public void checkTimeMybatisTest() {
        log.info( checkTimeMapper.getTime());
    }
}
