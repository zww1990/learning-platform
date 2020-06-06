package com.example.security;

import java.net.InetAddress;
import java.net.NetworkInterface;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SimpleTests {
	@Test
	public void testPasswordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.err.println(encoder.encode("admin"));
		System.err.println(encoder.encode("guest1"));
		System.err.println(encoder.encode("guest2"));
		System.err.println(encoder.encode("guest3"));
		System.err.println(encoder.encode("guest4"));
	}

	@Test
	public void testNetworkInterface() {
		try {
			byte[] bytes = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (byte bt : bytes) {
				sb.append(Integer.toHexString(bt & 0xff)).append('-');
			}
			sb.delete(sb.length() - 1, sb.length());
			System.err.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
