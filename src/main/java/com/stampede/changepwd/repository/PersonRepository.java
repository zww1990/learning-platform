package com.stampede.changepwd.repository;

import org.springframework.data.ldap.repository.LdapRepository;
import com.stampede.changepwd.domain.Person;

public interface PersonRepository extends LdapRepository<Person> {
}
