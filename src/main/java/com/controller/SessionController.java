package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@RequestMapping(method = RequestMethod.POST , value = "/signup")
	public String signup() 
	{		
		return "Signup Complete";
	}
	
	
	
}
