package com.mashinarius.hs.compare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CompareApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CompareApplication.class, args);
	}

	@PostConstruct
	public void init () {

		new ClassicGame().startGame();
	}

}
