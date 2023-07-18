package com.example.oauth2authorizationserver.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 设备控制器
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午8:06:43
 */
@Controller
public class DeviceController {

	@GetMapping("/activate")
	public String activate(@RequestParam(value = "user_code", required = false) String userCode) {
		if (userCode != null) {
			// 重定向到设备验证请求
			return "redirect:/oauth2/device_verification?user_code=" + userCode;
		}
		// 跳转到设备激活页面
		return "device-activate";
	}

	@GetMapping("/activated")
	public String activated() {
		// 跳转到设备已激活页面
		return "device-activated";
	}

	@GetMapping(value = "/", params = "success")
	public String success() {
		// 跳转到设备已激活页面
		return "device-activated";
	}

}
