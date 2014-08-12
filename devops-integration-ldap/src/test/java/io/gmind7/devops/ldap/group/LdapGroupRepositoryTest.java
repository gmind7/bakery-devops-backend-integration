package io.gmind7.devops.ldap.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.gmind7.devops.ldap.group.LdapGroup;
import io.gmind7.devops.ldap.group.LdapGroupRepository;
import io.gmind7.spring.foundation.config.AbstractApplicationTest;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LdapGroupRepositoryTest extends AbstractApplicationTest {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LdapGroupRepository ldapGroupRepository;
	
	@Test
	@Ignore
	public void findAllTest() {
		List<LdapGroup> groups = ldapGroupRepository.findAll();
		assertNotNull(groups);
	}
	
	@Test
	public void findByDnTest() {
		String dn = "---";
		LdapGroup group = ldapGroupRepository.findByDn(dn);
		log.debug("findByDnTest : {}", group.toString());
		assertEquals("---", group.getId());
	}
	
	@Test
	public void findByIDTest() {
		String id = "---";
		LdapGroup group = ldapGroupRepository.findById(id);
		log.debug("findByIDTest : {}", group.toString());
		assertEquals(id, group.getId());
	}

}
