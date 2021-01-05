package com.springdatajpa.restful_practice;

import com.springdatajpa.restful_practice.model.Emp;
import com.springdatajpa.restful_practice.repository.EmpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulPracticeApplication implements CommandLineRunner{

	public static void main(String[] args) {
		// System.out.println("Hello");
		SpringApplication.run(RestfulPracticeApplication.class, args);
	}

	@Autowired
	EmpRepository empRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		empRepository.save(new Emp(1,"심재형",100000));
		empRepository.save(new Emp(2,"추경민",200000));
		empRepository.save(new Emp(3,"재경",300000));
		empRepository.save(new Emp(4,"형민",400000));
		empRepository.save(new Emp(5,"재민",500000));
		empRepository.save(new Emp(6,"형경",600000));
	}
	
}
