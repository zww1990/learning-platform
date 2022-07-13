package com.stampede.changepwd.controller;

import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.stampede.changepwd.constant.Constants;
import com.stampede.changepwd.domain.Person;
import com.stampede.changepwd.domain.PersonParam;
import com.stampede.changepwd.service.PersonService;
import com.stampede.changepwd.util.LdapPasswordUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

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
	 * @return 更新人员密码，并发送邮件
	 */
	@PostMapping("/update")
	public Object update(@ModelAttribute PersonParam param) {
		ModelAndView mav = new ModelAndView("index")//
				.addObject("image", Constants.randomImage());
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
		if (!param.getNewpassword().equals(param.getRepassword())) {
			return mav.addObject("message", "请确认您的新密码！");
		}
		if (!this.personService.checkUserPassword(param)) {
			return mav.addObject("message", "您输入的用户名或旧密码不正确！");
		}
		this.personService.updateUserPassword(param);
		return new RedirectView("updatesuccess");
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午8:21:27
	 * @return 更新成功页面
	 */
	@GetMapping("/updatesuccess")
	public ModelAndView updateSuccess() {
		return new ModelAndView("person/updatesuccess")//
				.addObject("image", Constants.randomImage());
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午10:43:04
	 * @return 忘记密码-邮件页面
	 */
	@GetMapping("/emailpage")
	public ModelAndView emailPage() {
		return new ModelAndView("person/emailpage")//
				.addObject("image", Constants.randomImage());
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月18日,上午9:42:17
	 * @param param   用户密码参数类
	 * @param request HTTP请求
	 * @return 忘记密码-发送邮件
	 */
	@PostMapping("/sendmail")
	public ModelAndView sendMail(@ModelAttribute PersonParam param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("person/emailpage")//
				.addObject("image", Constants.randomImage());
		if (!StringUtils.hasText(param.getUsername())) {
			return mav.addObject("message", "请输入用户名！");
		}
		Optional<Person> optional = this.personService.findByUsername(param.getUsername());
		if (!optional.isPresent()) {
			return mav.addObject("message", "您输入的用户名不存在！");
		}
		Person person = optional.get();
		this.personService.sendMail(person, String.format("%s://%s:%s%s", request.getScheme(), request.getServerName(),
				request.getServerPort(), request.getContextPath()));
		mav.setViewName("person/sendsuccess");
		return mav.addObject("email", person.getMail());
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月18日,下午6:37:07
	 * @param token JWT编码
	 * @return 密码重置页面
	 */
	@GetMapping("/resetpage")
	public ModelAndView resetPage(@RequestParam String token) {
		try {
			Claims body = LdapPasswordUtils.jwtDecode(token);
			return new ModelAndView("person/resetpage")//
					.addObject("image", Constants.randomImage())//
					.addObject("username", body.getSubject());
		} catch (ExpiredJwtException e) {
			throw new RuntimeException("密码重置链接已失效！");
		}
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月18日,下午9:33:24
	 * @param param 用户密码参数类
	 * @return 重置密码，并发送邮件
	 */
	@PostMapping("/reset")
	public Object reset(@ModelAttribute PersonParam param) {
		ModelAndView mav = new ModelAndView("person/resetpage")//
				.addObject("image", Constants.randomImage());
		if (!StringUtils.hasText(param.getUsername())) {
			return mav.addObject("message", "请输入用户名！");
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
		if (param.getNewpassword().equals(param.getUsername())) {
			return mav.addObject("message", "您的新密码与您的用户名相同！");
		}
		if (!param.getNewpassword().equals(param.getRepassword())) {
			return mav.addObject("message", "请确认您的新密码！");
		}
		this.personService.updateUserPassword(param);
		return new RedirectView("updatesuccess");
	}
}
