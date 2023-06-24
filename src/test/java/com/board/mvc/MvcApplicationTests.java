package com.board.mvc;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class MvcApplicationTests {

	// 의존성 주입 
	@Autowired
	private DataSource dataSource;

	// Database Connection Check
	@Test
	@Transactional
	public void connetionCheck() {
		try (Connection connection = dataSource.getConnection()){
			log.info("Database Connection Is Ok Go Create Web Application");	
		} catch (Exception e) {
			log.info("Hey Your Connection Is Not Ok Find Something");	
		}
	}

}
