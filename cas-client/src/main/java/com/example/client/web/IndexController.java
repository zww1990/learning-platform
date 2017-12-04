package com.example.client.web;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class IndexController {
	@GetMapping("/")
	public ModelAndView index(Principal principal) {
		System.err.println("当前用户：" + principal);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("user", "hello world!");
		return mav;
	}
}
