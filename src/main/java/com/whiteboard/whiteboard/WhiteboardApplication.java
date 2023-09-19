package com.whiteboard.whiteboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = "com.whiteboard.whiteboard.entity")
public class WhiteboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhiteboardApplication.class, args);

		
	}

}
