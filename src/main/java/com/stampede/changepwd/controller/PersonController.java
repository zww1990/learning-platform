package com.stampede.changepwd.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.stampede.changepwd.constant.Constants;
import com.stampede.changepwd.domain.PersonParam;
import com.stampede.changepwd.service.PersonService;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:33:42
 * @description 人员控制器
 */
@Controller
@RequestMapping("/person")
public class PersonController {
	@Resource
	private PersonService personService;

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午1:34:42
	 * @param param 用户密码参数类
	 * @return 更新人员密码
	 */
	@PostMapping("/update")
	public ModelAndView update(@ModelAttribute PersonParam param) {
		ModelAndView mav = new ModelAndView("index").addObject("image", Constants.randomImage());
		if (!StringUtils.hasText(param.getUsername())) {
			return mav.addObject("message", "请输入用户名！");
		}
		if (!StringUtils.hasText(param.getPassword())) {
			return mav.addObject("message", "请输入旧密码！");
		}
		if (!StringUtils.hasText(param.getNewpassword())) {
			return mav.addObject("message", "请输入新密码！");
		}
		if (!StringUtils.hasText(param.getRepassword())) {
			return mav.addObject("message", "请输入确认密码！");
		}
		if (param.getNewpassword().length()<6) {
			return mav.addObject("message", "您的新密码太短！");
		}
		if (param.getNewpassword().length()>14) {
			return mav.addObject("message", "您的新密码太长！");
		}
		if (!this.personService.checkUserPassword(param)) {
			return mav.addObject("message", "您输入的用户名或旧密码不正确！");
		}
		System.err.println(param.getUsername());
		System.err.println(param.getPassword());
		System.err.println(param.getNewpassword());
		System.err.println(param.getRepassword());
		mav.setViewName("person/success");
		return mav;
	}
}
