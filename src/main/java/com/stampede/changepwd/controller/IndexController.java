package com.stampede.changepwd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stampede.changepwd.constant.Constants;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:27:50
 * @description 首页控制器
 */
@Controller
public class IndexController {
	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午1:28:03
	 * @return 首页
	 */
	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("index")//
				.addObject("image", Constants.randomImage());
	}
}
