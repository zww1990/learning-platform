package com.stampede.changepwd.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.stampede.changepwd.constant.Constants;
import com.stampede.changepwd.domain.Person;
import com.stampede.changepwd.service.PersonService;

/**
 * @author ZhangWeiWei
 * @date 2020年2月26日,下午10:15:06
 * @description 管理员控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
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

	@GetMapping("/sendsuccess")
	public ModelAndView sendSuccess() {
		return new ModelAndView("admin/sendsuccess").addObject("image", Constants.randomImage());
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

	@GetMapping("/queryuser")
	@ResponseBody
	public Object queryUser(@RequestParam String userId) {
		Map<String, Object> data = this.personService.queryForUserMap(userId);
		data.put("status", !data.isEmpty());
		return data;
	}

	@GetMapping("/querywaibao")
	@ResponseBody
	public Object queryWaibao() {
		long waibaoId = this.personService.queryWaibaoId();
		Map<String, Long> data = new HashMap<>();
		data.put("waibaoId", waibaoId);
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
	public Object sendMail(@ModelAttribute Person param, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
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
		this.personService.sendMailForAdmin(param, String.format("%s://%s:%s%s", request.getScheme(),
				request.getServerName(), request.getServerPort(), request.getContextPath()));
		log.info("管理页面>>>账号[{}]已创建，邮件已发至邮箱[{}]", param.getUid(), param.getMail());
		redirectAttributes.addFlashAttribute("email", param.getMail());
		return new RedirectView("sendsuccess");
	}
}
