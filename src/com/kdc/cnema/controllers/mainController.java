package com.kdc.cnema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {

	@RequestMapping("/")
	public ModelAndView main() {
		ModelAndView mav =  new ModelAndView();
		
		mav.setViewName("index");
		
		return mav;
	}
	
}
