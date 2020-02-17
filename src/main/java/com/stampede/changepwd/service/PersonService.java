package com.stampede.changepwd.service;

import javax.annotation.Resource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;
import com.stampede.changepwd.domain.PersonParam;
import com.stampede.changepwd.repository.PersonRepository;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:36:20
 * @description 人员服务实现类
 */
@Service
public class PersonService {
	@Resource
	private PersonRepository personRepository;
	@Resource
	private LdapTemplate ldapTemplate;

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午1:36:44
	 * @param param 用户密码参数类
	 * @return 验证用户名密码是否正确
	 */
	public boolean checkUserPassword(PersonParam param) {
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "person"));
		filter.and(new EqualsFilter("uid", param.getUsername()));
		return this.ldapTemplate.authenticate("", filter.encode(), param.getPassword());
	}
}
