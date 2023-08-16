package com.example.oauth2authorizationserver.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 默认错误控制器
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午8:09:30
 */
@Controller
public class DefaultErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(Model model, HttpServletRequest request) {
		String errorMessage = getErrorMessage(request);
		if (errorMessage.startsWith("[access_denied]")) {
			model.addAttribute("errorTitle", "访问被拒绝");
			model.addAttribute("errorMessage", "您已被拒绝访问。");
		} else {
			model.addAttribute("errorTitle", "错误");
			model.addAttribute("errorMessage", errorMessage);
		}
		// 跳转到错误页面
		return "error";
	}

	private String getErrorMessage(HttpServletRequest request) {
		String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		return StringUtils.hasText(errorMessage) ? errorMessage : "";
	}

}
