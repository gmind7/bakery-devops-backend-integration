package io.gmind7.spring.foundation.config;

import io.gmind7.spring.foundation.config.AppConfig;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@TransactionConfiguration(defaultRollback = true)
@ActiveProfiles({"inetdev", "loc"})
public abstract class AbstractApplicationTest {
	
}