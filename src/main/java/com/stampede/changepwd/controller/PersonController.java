package com.stampede.changepwd.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.stampede.changepwd.domain.PersonParam;
import com.stampede.changepwd.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
	@Resource
	private PersonService personService;

	@PostMapping("/update")
	public ModelAndView update(@ModelAttribute PersonParam param) {
		System.err.println(param.getUsername());
		System.err.println(param.getPassword());
		System.err.println(param.getNewpassword());
		System.err.println(param.getRepassword());
		return new ModelAndView("person/success");
	}
}
