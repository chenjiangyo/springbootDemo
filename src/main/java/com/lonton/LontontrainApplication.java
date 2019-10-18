package com.lonton;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lonton.mapper")
public class LontontrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(LontontrainApplication.class, args);
	}

}
