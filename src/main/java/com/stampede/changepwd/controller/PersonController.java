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
import com.stampede.changepwd.util.LdapPasswordUtils;

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
		if (param.getNewpassword().length() < 6) {
			return mav.addObject("message", "您的新密码太短！");
		}
		if (param.getNewpassword().length() > 14) {
			return mav.addObject("message", "您的新密码太长！");
		}
		if (LdapPasswordUtils.countLowerCase(param.getNewpassword()) < 1) {
			return mav.addObject("message", "您的新密码没有包含足够的小写字母！");
		}
		if (LdapPasswordUtils.countUpperCase(param.getNewpassword()) < 1) {
			return mav.addObject("message", "您的新密码没有包含足够的大写字母！");
		}
		if (LdapPasswordUtils.countDigit(param.getNewpassword()) < 1) {
			return mav.addObject("message", "您的新密码没有包含足够的数字！");
		}
		if (LdapPasswordUtils.countCharacterType(param.getNewpassword()) < 3) {
			return mav.addObject("message", "您的新密码没有包含足够的字符类型！");
		}
		if (param.getNewpassword().equals(param.getPassword())) {
			return mav.addObject("message", "您的新密码与旧密码相同！");
		}
		if (param.getNewpassword().equals(param.getUsername())) {
			return mav.addObject("message", "您的新密码与您的用户名相同！");
		}
		if (!this.personService.checkUserPassword(param)) {
			return mav.addObject("message", "您输入的用户名或旧密码不正确！");
		}
		this.personService.updateUserPassword(param);
		mav.setViewName("person/success");
		return mav;
	}
}
