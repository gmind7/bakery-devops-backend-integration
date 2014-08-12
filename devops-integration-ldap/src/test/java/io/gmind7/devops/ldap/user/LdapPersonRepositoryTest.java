package io.gmind7.devops.ldap.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.gmind7.devops.ldap.person.LdapPerson;
import io.gmind7.devops.ldap.person.LdapPersonRepository;
import io.gmind7.spring.foundation.config.AbstractApplicationTest;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LdapPersonRepositoryTest extends AbstractApplicationTest {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private LdapPersonRepository ldapPersonRepository;
	
	@Test
	@Ignore
	public void findAllTest() {
		List<LdapPerson> persons = ldapPersonRepository.findAll();
		assertNotNull(persons);
	}
		
	@Test
	public void findByDnTest() {
		String dn = "---";
		LdapPerson person = ldapPersonRepository.findByDn(dn);
		log.debug("findByDnTest : {}", person.toString());
		assertEquals("---", person.getId());
	}
	
	@Test
	public void findByIdTest() {
		String id = "---";
		LdapPerson person = ldapPersonRepository.findById(id);
		log.debug("findByIdTest : {}", person.toString());
		assertEquals(id, person.getId());
	}
	
}
