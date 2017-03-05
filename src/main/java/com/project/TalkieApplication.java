package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan("com.project")
public class TalkieApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalkieApplication.class, args);
	}
}
