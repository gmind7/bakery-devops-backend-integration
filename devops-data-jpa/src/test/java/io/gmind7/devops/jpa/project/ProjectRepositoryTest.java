package io.gmind7.devops.jpa.project;

import static org.junit.Assert.assertNotNull;
import io.gmind7.devops.jpa.config.SpringAuditorAware;
import io.gmind7.devops.jpa.project.Project;
import io.gmind7.devops.jpa.project.ProjectRepository;
import io.gmind7.spring.foundation.config.AbstractApplicationTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectRepositoryTest extends AbstractApplicationTest {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private SpringAuditorAware springAuditorAware;
	
	@Before
	public void beforeSetting() {
		springAuditorAware.setAuditor("system");
	}
	
	@Test	
	public void saveTest() {
		
		Project project = projectRepository.findOne(1L);
		
		if(project==null) project = new Project();
		
		project.setProjectKey("testKey");
		project.setName("testName");
		project.setLanguage("testLanguage");
		
		Project result = projectRepository.save(project);
		
		assertNotNull(result.getId());
		
	}
	
	@Test
	public void findOne() {
		Project result = projectRepository.findOne(1L);
		assertNotNull(result);
	}

}
