package com.board.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.board.mvc.**.mappers")
public class MvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
	}
}