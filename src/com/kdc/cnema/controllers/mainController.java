package com.kdc.cnema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdc.cnema.domain.User;

@Controller
@CrossOrigin(origins = "*")
public class mainController {

	@RequestMapping("/")
	@ResponseBody
	public String main(@RequestBody User user) {
		if(user.getFistname() != null) {
			return user.getFistname();
		}else {
			return "No sirvio";
		}
	}
	
}