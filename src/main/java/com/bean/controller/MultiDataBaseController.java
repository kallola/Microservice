package com.bean.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.book.repository.BookRepository;
import com.bean.model.Book;
import com.bean.model.User;
import com.bean.user.repository.UserRepository;

@RestController
public class MultiDataBaseController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	//@PostMapping("/postUsers")
	public void saveDataToDb()
	{
		//userRepository.saveAll(Stream.of(new User("papa"),new User("mama")).collect(Collectors.toList()));
		//bookRepository.saveAll(Stream.of(new Book("Java"),new Book("Spring Boot")).collect(Collectors.toList()));
		User user1=new User();
		user1.setId(102);
		user1.setUserName("mypapa2");
		userRepository.save(user1);
		
		Book book1=new Book();
		book1.setId(333);
		book1.setName("love1");
		bookRepository.save(book1);
		
	}

	@GetMapping("/getUsers")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/getbooks")
	public List<Book> getbooks(){
		return bookRepository.findAll();
	}
}
