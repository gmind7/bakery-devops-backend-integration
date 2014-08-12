package io.gmind7.devops.ldap.sonar.measure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.gmind7.devops.ldap.sonar.measure.SonarMeasure;
import io.gmind7.devops.ldap.sonar.measure.SonarMeasureRepository;
import io.gmind7.spring.foundation.config.AbstractApplicationTest;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.wsclient.services.ResourceQuery;
import org.springframework.beans.factory.annotation.Autowired;

public class SonarMeasureRepositoryTest extends AbstractApplicationTest {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ResourceQuery resourceQuery;
	
	@Autowired
	private SonarMeasureRepository measureRepository;
	
	@Test
	@Ignore
	public void findAllTest() {
		List<SonarMeasure> measures = measureRepository.findAll();
		assertNotNull(measures);
	}
	
	@Test
	public void findOneTest() {
		String resourceKey = "SCM-Deployment-Dashboard";
		SonarMeasure measure = measureRepository.findOne(resourceKey);
		log.debug("findOneTest : {}", measure.toString());
		assertEquals(resourceKey, measure.getResourcekey());
	}
	
	@Test
	public void findOneQueryTest() {
		String resourceKey = "SCM-Deployment-Dashboard";
		SonarMeasure measure = measureRepository.findOne(resourceQuery.setResourceKeyOrId(resourceKey));
		log.debug("findOneQueryTest : {}", measure.toString());
		assertEquals(resourceKey, measure.getResourcekey());
	}
}
