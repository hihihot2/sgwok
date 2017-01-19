package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.domain.Question;

@Controller
@SpringBootApplication
public class Myproject001Application {
	private static final Logger log = LoggerFactory.getLogger(Myproject001Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Myproject001Application.class, args);
		log.debug(">>redirect:main -> " + args[0].toString());
	}
}
//	chmod +x <이름>
//	.mvnw clean package