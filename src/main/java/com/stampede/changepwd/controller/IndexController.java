package com.stampede.changepwd.controller;

import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	private static final String[] images = { "clouds", "sky", "space", "stars" };

	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("index").addObject("image", images[new Random().nextInt(images.length)]);
	}
}
