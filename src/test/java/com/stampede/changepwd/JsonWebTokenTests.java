package com.stampede.changepwd;

import org.junit.Test;
import com.stampede.changepwd.util.LdapPasswordUtils;

public class JsonWebTokenTests {
	@Test
	public void testEncode() {
		try {
			System.err.println(LdapPasswordUtils.jwtEncode("", 5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDecode() {
		try {
			String string = "";
			System.err.println(LdapPasswordUtils.jwtDecode(string));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
