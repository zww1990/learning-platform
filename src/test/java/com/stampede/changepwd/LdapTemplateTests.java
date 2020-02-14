package com.stampede.changepwd;

import java.util.List;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

@SpringBootTest
public class LdapTemplateTests {
	@Resource
	private LdapTemplate ldapTemplate;

	@Test
	public void testSearchReturnAttributesMapper() {
		try {
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectclass", "person"));
			filter.and(new EqualsFilter("uid", "admin"));
			List<Object> list = this.ldapTemplate.search("", filter.encode(), new PersonAttributesMapper());
			System.err.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class PersonAttributesMapper implements AttributesMapper<Object> {
		@Override
		public Object mapFromAttributes(Attributes attributes) throws NamingException {
			System.err.println(attributes.get("givenname").get());
			System.err.println(attributes.get("sn").get());
			byte[] bs = (byte[]) attributes.get("userpassword").get();
			System.err.println(new String(bs));
			System.err.println(attributes.get("uidnumber").get());
			System.err.println(attributes.get("gidnumber").get());
			System.err.println(attributes.get("mail").get());
			System.err.println(attributes.get("uid").get());
			System.err.println(attributes.get("cn").get());
			return attributes;
		}
	}
}
