package io.gmind7.devops.ldap.sonar.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.gmind7.devops.ldap.sonar.resource.SonarResource;
import io.gmind7.devops.ldap.sonar.resource.SonarResourceRepository;
import io.gmind7.spring.foundation.config.AbstractApplicationTest;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.wsclient.services.ResourceQuery;
import org.springframework.beans.factory.annotation.Autowired;

public class SonarResourceRepositoryTest extends AbstractApplicationTest {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ResourceQuery resourceQuery;
	
	@Autowired
	private SonarResourceRepository resourceRepository;
	
	@Test
	@Ignore
	public void findAllTest() {
		List<SonarResource> resources = resourceRepository.findAll();
		assertNotNull(resources);
	}
	
	@Test
	public void findOneTest() {
		String resourceKey = "SCM-Deployment-Dashboard";
		SonarResource resource = resourceRepository.findOne(resourceKey);
		log.debug("findOneTest : {}", resource.toString());
		assertEquals(resourceKey, resource.getKey());
	}
	
	@Test
	public void findOneQueryTest() {
		String resourceKey = "SCM-Deployment-Dashboard";
		SonarResource resource = resourceRepository.findOne(resourceQuery.setResourceKeyOrId(resourceKey));
		log.debug("findOneQueryTest : {}", resource.toString());
		assertEquals(resourceKey, resource.getKey());
	}
}
