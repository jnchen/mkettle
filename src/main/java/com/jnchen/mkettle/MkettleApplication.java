package com.jnchen.mkettle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MkettleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MkettleApplication.class, args);
	}
}
