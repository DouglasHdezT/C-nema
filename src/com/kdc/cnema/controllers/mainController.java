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
public class mainController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String main(@RequestBody LoginForm user) {
		if(user.getUsername() != null) {
			return user.getUsername();
		}else {
			return "No sirvio";
		}
	}
	
}
