package com.stampede.changepwd.repository;

import org.springframework.data.ldap.repository.LdapRepository;
import com.stampede.changepwd.domain.Person;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:38:11
 * @description 人员数据访问操作类
 */
public interface PersonRepository extends LdapRepository<Person> {
}
