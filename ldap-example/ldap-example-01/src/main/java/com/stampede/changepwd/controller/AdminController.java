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
import org.springframework.web.servlet.ModelAndView;
import com.stampede.changepwd.constant.Constants;
import com.stampede.changepwd.domain.Person;
import com.stampede.changepwd.domain.PersonParam;
import com.stampede.changepwd.service.PersonService;

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

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月27日,上午9:57:42
	 * @param param 用户密码参数类
	 * @param request HTTP请求
	 * @return 管理员发送邮件
	 */
	@PostMapping("/sendmail")
	public ModelAndView sendMail(@ModelAttribute PersonParam param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/sendpage").addObject("image", Constants.randomImage());
		if (!StringUtils.hasText(param.getUsername())) {
			return mav.addObject("message", "请输入用户名！");
		}
		Optional<Person> optional = this.personService.findByUsername(param);
		if (!optional.isPresent()) {
			return mav.addObject("message", "您输入的用户名不存在！");
		}
		Person person = optional.get();
		this.personService.sendMailForAdmin(person, String.format("%s://%s:%s%s/person/resetpage", request.getScheme(),
				request.getServerName(), request.getServerPort(), request.getContextPath()));
		mav.setViewName("admin/sendsuccess");
		return mav.addObject("email", person.getMail());
	}
}
