package com.stampede.changepwd;

import java.util.List;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapTemplateTests {
	@Resource
	private LdapTemplate ldapTemplate;

	@Test
	public void testSearchReturnAttributesMapper() {
		try {
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectclass", "person"));
			filter.and(new EqualsFilter("uid", "zhangweiwei1"));
			List<Object> list = this.ldapTemplate.search("", filter.encode(), new PersonAttributesMapper());
			System.err.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchReturnContextMapper() {
		try {
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectclass", "person"));
			filter.and(new EqualsFilter("uid", "zhangweiwei1"));
			List<Object> list = this.ldapTemplate.search("", filter.encode(), new PersonContextMapper());
			System.err.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAuthenticate() {
		try {
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectclass", "person"));
			filter.and(new EqualsFilter("uid", "zhangweiwei1"));
			boolean result = this.ldapTemplate.authenticate("", filter.encode(), "1q2w3e4r");
			System.err.println(result);
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

	private static class PersonContextMapper implements ContextMapper<Object> {
		@Override
		public Object mapFromContext(Object ctx) throws NamingException {
			DirContextAdapter adapter = (DirContextAdapter) ctx;
			System.err.println(adapter.getObjectAttribute("givenName"));
			System.err.println(adapter.getObjectAttribute("sn"));
			byte[] bs = (byte[]) adapter.getObjectAttribute("userPassword");
			System.err.println(new String(bs));
			System.err.println(adapter.getObjectAttribute("uidNumber"));
			System.err.println(adapter.getObjectAttribute("gidNumber"));
			System.err.println(adapter.getObjectAttribute("mail"));
			System.err.println(adapter.getObjectAttribute("uid"));
			System.err.println(adapter.getObjectAttribute("cn"));
			return ctx;
		}
	}
}
