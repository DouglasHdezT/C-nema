package com.kdc.cnema.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdc.cnema.dtos.LoginForm;

@Controller
@CrossOrigin(origins = "*")
public class LoginController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public String main(@RequestBody LoginForm userSubmitted) {
		if(userSubmitted.getUsername() != null) {
			return "Username: "+ userSubmitted.getUsername() + " Password: "+userSubmitted.getPassword();
		}else {
			return "No sirvio";
		}
	}
	
}
