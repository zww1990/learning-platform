package com.stampede.changepwd;

import org.junit.jupiter.api.Test;
import com.stampede.changepwd.util.LdapPasswordUtils;

public class JsonWebTokenTests {
	@Test
	public void testEncode() {
		try {
			System.err.println(LdapPasswordUtils.jwtEncode("zhangweiwei1", 5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDecode() {
		try {
			String string = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjZjViZDIyNi1lNmE4LTQ1MzAtYWMxYi1lM2I5M2NlNzA4MTUiLCJzdWIiOiJ6aGFuZ3dlaXdlaTEiLCJpYXQiOjE1ODIwMjAwNzYsImV4cCI6MTU4MjAyMDM3Nn0.pZlBvsLqO3-w1dxv_bTlOXE1t0lexC0e5m0AtV_JB1A";
			System.err.println(LdapPasswordUtils.jwtDecode(string));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
