package io.gmind7.devops.jpa.group;

import static org.junit.Assert.assertNotNull;
import io.gmind7.devops.jpa.config.SpringAuditorAware;
import io.gmind7.devops.jpa.group.Group;
import io.gmind7.devops.jpa.group.GroupRepository;
import io.gmind7.spring.foundation.config.AbstractApplicationTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupRepositoryTest extends AbstractApplicationTest {
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private SpringAuditorAware springAuditorAware;
	
	@Before
	public void beforeSetting() {
		springAuditorAware.setAuditor("system");
	}
	
	@Test	
	public void saveTest() {
		
		Group group = groupRepository.findOne(1L);
		
		if(group==null) group = new Group();
		
		group.setName("---");
		group.setDisplayName("--");
		group.setDescription("--");
		group.setMail("---@----");
		
		Group result = groupRepository.save(group);
		
		assertNotNull(result.getId());
		
	}
	
	@Test
	public void findOne() {
		Group group = groupRepository.findOne(1L);
		assertNotNull(group);
	}

}
