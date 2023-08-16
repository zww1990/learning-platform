package com.example.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;

public class RoleHierarchyUtilsTests {
	@Test
	public void testRoleHierarchyFromMap() {
		try {
			Map<String, List<String>> map = new HashMap<>();
			map.put("ROLE_ADMIN", Arrays.asList("ROLE_GUEST"));
			System.err.println(RoleHierarchyUtils.roleHierarchyFromMap(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
