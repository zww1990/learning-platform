package com.example.hello.controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.config.ApplicationConfig.Address;
import com.example.hello.config.ApplicationConfig.UserInfo;
import com.example.hello.model.JobInfo;
import com.example.hello.model.ResponseBody;
import com.example.hello.model.UserLogin;
import com.example.hello.service.HelloService;

/**
 * HelloController
 * 
 * @author zhang weiwei
 * @since 2022年8月6日,下午4:16:05
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
	@Resource
	private HelloService helloService;
	@Resource
	private ServerProperties properties;

	@GetMapping("/wsurl")
	public ResponseBody<String> wsUrl() throws Exception {
		return new ResponseBody<String>()//
				.setCode(HttpStatus.OK.value())//
				.setStatus(ResponseBody.SUCCESS)//
				.setData(String.format("ws://%s:%s/websocket/%s", this.getLocalHostLANAddress().getHostAddress(),
						this.properties.getPort(), UUID.randomUUID().toString().replace("-", "")));
	}

	@GetMapping("/pausejob")
	public ResponseBody<?> pauseJob() throws Exception {
		return this.helloService.pauseJob();
	}

	@GetMapping("/resumejob")
	public ResponseBody<?> resumeJob() throws Exception {
		return this.helloService.resumeJob();
	}

	@PostMapping("/savejob")
	public ResponseBody<?> saveJob(@RequestBody JobInfo jobInfo) throws Exception {
		return this.helloService.saveJob(jobInfo);
	}

	@GetMapping("/users")
	public ResponseBody<List<UserInfo>> getUsers() {
		return this.helloService.getUsers();
	}

	@GetMapping("/addresses")
	public ResponseBody<List<Address>> getAddresses() {
		return this.helloService.getAddresses();
	}

	@PostMapping("/saveaddress")
	public ResponseBody<?> saveAddress(@RequestBody Address address) {
		return this.helloService.saveAddress(address);
	}

	@PostMapping("/initstaffclock")
	public ResponseBody<?> initStaffClock(@RequestBody UserLogin userLogin) {
		try {
			return this.helloService.initStaffClock(userLogin);
		} catch (Exception e) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())//
					.setData(e.getLocalizedMessage());
		}
	}

	@PostMapping("/v2/userloginandstaffclock")
	public ResponseBody<?> userLoginAndStaffClockV2(@RequestBody UserLogin userLogin) {
		return this.helloService.userLoginAndStaffClockV2(userLogin);
	}

	@PostMapping("/v1/userloginandstaffclock")
	public ResponseBody<?> userLoginAndStaffClockV1(@RequestBody UserLogin userLogin) {
		return this.helloService.userLoginAndStaffClockV1(userLogin);
	}

	@PostMapping("/selectappstaffclockloglist")
	public ResponseBody<?> selectAppStaffClockLogList(@RequestBody UserLogin userLogin) {
		return this.helloService.selectAppStaffClockLogList(userLogin);
	}

	@PostMapping("/selectdevicelist")
	public ResponseBody<?> selectDeviceList(@RequestBody UserLogin userLogin) {
		return this.helloService.selectDeviceList(userLogin);
	}

	@PostMapping("/resetbinddevice")
	public ResponseBody<?> resetBindDevice(@RequestParam String staffNo, @RequestParam Integer id) {
		return this.helloService.resetBindDevice(staffNo, id);
	}

	private InetAddress getLocalHostLANAddress() throws Exception {
		InetAddress candidateAddress = null;
		// 遍历所有的网络接口
		for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces
				.hasMoreElements();) {
			NetworkInterface iface = ifaces.nextElement();
			// 在所有的接口下再遍历IP
			for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
				InetAddress inetAddr = inetAddrs.nextElement();
				if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
					if (inetAddr.isSiteLocalAddress()) {
						// 如果是site-local地址，就是它了
						return inetAddr;
					} else if (candidateAddress == null) {
						// site-local类型的地址未被发现，先记录候选地址
						candidateAddress = inetAddr;
					}
				}
			}
		}
		if (candidateAddress != null) {
			return candidateAddress;
		}
		// 如果没有发现 non-loopback地址.只能用最次选的方案
		return InetAddress.getLocalHost();
	}

}
