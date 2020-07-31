package org.entitylabs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

	@GetMapping
	public ResponseEntity<String> sayhello() {
		System.out.println("Say Hello !!");
		
		return new ResponseEntity<String>("Say Hello", HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<String> helloUser() {

		return new ResponseEntity<String>("Hello User", HttpStatus.OK);
	}
}
