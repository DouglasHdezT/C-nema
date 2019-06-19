package com.kdc.cnema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mainController {

	@RequestMapping("/")
	@ResponseBody
	public String main() {
		
		
		
		return "Funcionando";
	}
	
}
