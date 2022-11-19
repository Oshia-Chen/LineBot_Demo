package com.example.LineBot_Simple_Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LineBotSimpleDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LineBotSimpleDemoApplication.class, args);
//		String token = new String(Files.readAllBytes(Paths.get("LineBot_Token")));
//		LineMessagingClient client = LineMessagingClient.builder(token).build();
//		client.getBotInfo();
	}

}
