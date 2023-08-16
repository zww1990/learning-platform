package com.example.hello.config;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.Duration;
import java.util.Enumeration;

import javax.annotation.Resource;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.example.hello.service.job.HelloJob;

import lombok.extern.slf4j.Slf4j;

/**
 * HelloAutoConfiguration
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午9:38:58
 */
@Configuration
@Slf4j
public class HelloAutoConfiguration implements CommandLineRunner {
	@Resource
	private ApplicationConfig appConfig;

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restBuilder) {
		return restBuilder.setConnectTimeout(Duration.ofSeconds(5))//
				.setReadTimeout(Duration.ofSeconds(5))//
				.build();
	}

	@Bean
	JobDetail jobDetail() {
		return JobBuilder.newJob(HelloJob.class)//
				.withIdentity(appConfig.getJobConfig().getJobKey())//
				.storeDurably()//
				.build();
	}

	@Bean
	ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Override
	public void run(String... args) throws Exception {
		this.appConfig.setHostAddress(this.getLocalHostLANAddress().getHostAddress());
		log.info("{}", this.appConfig);
	}

	private InetAddress getLocalHostLANAddress() throws Exception {
		InetAddress candidateAddress = null;
		// 遍历所有的网络接口
		for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); //
				ifaces.hasMoreElements();) {
			NetworkInterface iface = ifaces.nextElement();
			// 在所有的接口下再遍历IP
			for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); //
					inetAddrs.hasMoreElements();) {
				InetAddress inetAddr = inetAddrs.nextElement();
				// 排除loopback类型地址
				if (!inetAddr.isLoopbackAddress()) {
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
