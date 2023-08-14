package com.assessment.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Replace with your React.js application's URL
@RequestMapping("/user")
public class FacebookController {
	  @GetMapping
	    public Principal getUser(final Principal user) {
	        return user;
	    }
}
