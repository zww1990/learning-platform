package com.example.demo.web;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/hello")
	public String hello(@AuthenticationPrincipal Authentication authentication) {
		System.err.println(authentication.getCredentials());
		System.err.println(authentication.getDetails());
		System.err.println(authentication.getName());
		System.err.println(authentication.getPrincipal());
		System.err.println(authentication.getAuthorities());
		return "hello";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/cookie")
	public String cookie(@RequestParam("browser") String browser, HttpServletRequest request, HttpSession session) {
		// 取出session中的browser
		Object sessionBrowser = session.getAttribute("browser");
		if (sessionBrowser == null) {
			System.out.println("不存在session，设置browser=" + browser);
			session.setAttribute("browser", browser);
		} else {
			System.out.println("存在session，browser=" + sessionBrowser.toString());
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName() + " : " + cookie.getValue());
			}
		}
		return "index";
	}

	@GetMapping("/findByUsername")
	@ResponseBody
	public Object findByUsername(@RequestParam String username) {
		return this.sessionRepository
				.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username);
	}

	@Resource
	private FindByIndexNameSessionRepository<Session> sessionRepository;
}
