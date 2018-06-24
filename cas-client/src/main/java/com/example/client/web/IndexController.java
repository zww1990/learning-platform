package com.example.client.web;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class IndexController {
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@GetMapping("/")
	public ModelAndView index(Principal principal) {
		log.info("当前用户：{}", principal);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("user", principal);
		return mav;
	}
}
