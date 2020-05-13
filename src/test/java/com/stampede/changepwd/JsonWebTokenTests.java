package com.stampede.changepwd;

import org.junit.Test;
import com.stampede.changepwd.util.LdapPasswordUtils;
import io.jsonwebtoken.Claims;

public class JsonWebTokenTests {
	@Test
	public void testJwt() {
		try {
			String jwt = LdapPasswordUtils.jwtEncode("zhangweiwei", 5);
			System.err.println(jwt);
			Claims ret = LdapPasswordUtils.jwtDecode(jwt);
			System.err.println(ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
