package io.gmind7.devops.mybatis;

import static org.junit.Assert.assertEquals;
import io.gmind7.spring.foundation.config.AbstractApplicationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBatisMapperTest extends AbstractApplicationTest {
	
	@Autowired
	private TestMapper testMapper;
	
	@Test
	public void selectTest(){
		String key = testMapper.selectTest();
		assertEquals("1", key);
	}

}
