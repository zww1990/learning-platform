package com.stampede.changepwd.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stampede.changepwd.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	@Resource
	private PersonService personService;
}
