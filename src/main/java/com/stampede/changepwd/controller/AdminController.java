package com.stampede.changepwd.controller;

import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.stampede.changepwd.constant.Constants;
import com.stampede.changepwd.domain.Person;
import com.stampede.changepwd.service.PersonService;
import com.stampede.changepwd.util.LdapPasswordUtils;

/**
 * @author ZhangWeiWei
 * @date 2020年2月26日,下午10:15:06
 * @description 管理员控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private PersonService personService;

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月27日,上午9:57:27
	 * @return 管理员页面
	 */
	@GetMapping
	public ModelAndView admin() {
		return new ModelAndView("admin/sendpage").addObject("image", Constants.randomImage());
	}

	@GetMapping("/checkuid")
	@ResponseBody
	public Object checkUid(@RequestParam String uid) {
		Optional<Person> opt = this.personService.findByUsername(uid);
		Map<String, Object> data = new HashMap<>();
		if (opt.isPresent()) {
			data.put("status", true);
			data.put("message", String.format("该[%s]已存在，请重新输入。", uid));
		} else {
			data.put("status", false);
			data.put("message", String.format("该[%s]不存在，请放心使用。", uid));
		}
		return data;
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月27日,上午9:57:42
	 * @param param 用户密码参数类
	 * @param request HTTP请求
	 * @return 管理员发送邮件
	 */
	@PostMapping("/sendmail")
	public ModelAndView sendMail(@ModelAttribute Person param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/sendpage").addObject("image", Constants.randomImage());
		if (!StringUtils.hasText(param.getUid())) {
			return mav.addObject("message", "请输入uid！");
		}
		if (this.personService.findByUsername(param.getUid()).isPresent()) {
			return mav.addObject("message", "您输入的uid已存在！");
		}
		if (!StringUtils.hasText(param.getMail())) {
			return mav.addObject("message", "请输入email！");
		}
		if (!StringUtils.hasText(param.getGidNumber())) {
			return mav.addObject("message", "请输入gidNumber！");
		}
		if (!StringUtils.hasText(param.getUidNumber())) {
			return mav.addObject("message", "请输入uidNumber！");
		}
		if (this.personService.findByUidNumber(param.getUidNumber()).isPresent()) {
			return mav.addObject("message", "您输入的uidNumber已存在！");
		}
		if (!StringUtils.hasText(param.getGivenName())) {
			return mav.addObject("message", "请输入givenName！");
		}
		if (!StringUtils.hasText(param.getUserPassword())) {
			return mav.addObject("message", "请输入userPassword！");
		}
		if (param.getUserPassword().length() < 6) {
			return mav.addObject("message", "您的userPassword太短！");
		}
		if (param.getUserPassword().length() > 14) {
			return mav.addObject("message", "您的userPassword太长！");
		}
		if (LdapPasswordUtils.countLowerCase(param.getUserPassword()) < 1) {
			return mav.addObject("message", "您的userPassword没有包含足够的小写字母！");
		}
		if (LdapPasswordUtils.countUpperCase(param.getUserPassword()) < 1) {
			return mav.addObject("message", "您的userPassword没有包含足够的大写字母！");
		}
		if (LdapPasswordUtils.countDigit(param.getUserPassword()) < 1) {
			return mav.addObject("message", "您的userPassword没有包含足够的数字！");
		}
		if (LdapPasswordUtils.countCharacterType(param.getUserPassword()) < 3) {
			return mav.addObject("message", "您的userPassword没有包含足够的字符类型！");
		}
		if (param.getUserPassword().equals(param.getUid())) {
			return mav.addObject("message", "您的userPassword与uid相同！");
		}
		if (param.getUserPassword().equals(param.getGidNumber())) {
			return mav.addObject("message", "您的userPassword与您的gidNumber相同！");
		}
		if (param.getUserPassword().equals(param.getUidNumber())) {
			return mav.addObject("message", "您的userPassword与您的uidNumber相同！");
		}
		if (param.getUserPassword().equals(param.getGivenName())) {
			return mav.addObject("message", "您的userPassword与您的givenName相同！");
		}
		this.personService.sendMailForAdmin(param, String.format("%s://%s:%s%s/person/resetpage", request.getScheme(),
				request.getServerName(), request.getServerPort(), request.getContextPath()));
		mav.setViewName("admin/sendsuccess");
		return mav.addObject("email", param.getMail());
	}
}
