package com.thelong.longsmanor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LongsmanorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LongsmanorApplication.class, args);
	}

}
