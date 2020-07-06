package com.stampede.changepwd;

import javax.annotation.Resource;
import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.test.context.junit4.SpringRunner;
import com.stampede.changepwd.util.LdapPasswordUtils;

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
			filter.and(new EqualsFilter("uid", "alienware"));
			this.ldapTemplate.search("", filter.encode(), (Attributes attributes) -> {
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
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchReturnContextMapper() {
		try {
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectclass", "person"));
			filter.and(new EqualsFilter("uid", "alienware"));
			this.ldapTemplate.search("", filter.encode(), (Object ctx) -> {
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
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAuthenticate() {
		try {
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectclass", "person"));
			filter.and(new EqualsFilter("uid", "alienware"));
			boolean result = this.ldapTemplate.authenticate("", filter.encode(), "hello");
			System.err.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		try {
			Name dn = LdapUtils.newLdapName("uid=alienware,ou=People");
			BasicAttribute ba = new BasicAttribute("objectClass");
			ba.add("inetOrgPerson");
			ba.add("posixAccount");
			ba.add("top");
			Attributes attr = new BasicAttributes();
			attr.put(ba);
			attr.put("givenName", "外星人");
			attr.put("sn", "alienware");
			attr.put("userPassword", LdapPasswordUtils.md5Password("hello"));
			attr.put("uidNumber", "999999999");
			attr.put("gidNumber", "501");
			attr.put("mail", "alienware@5i5j.com");
			attr.put("uid", "alienware");
			attr.put("cn", "alienware");
			attr.put("homeDirectory", "/home/users/alienware");
			this.ldapTemplate.bind(dn, null, attr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
