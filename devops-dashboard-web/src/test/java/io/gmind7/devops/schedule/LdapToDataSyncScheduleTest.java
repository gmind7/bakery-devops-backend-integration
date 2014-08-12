package io.gmind7.devops.schedule;

import io.gmind7.devops.schedule.LdapToDataSyncSchedule;
import io.gmind7.spring.foundation.config.AbstractWebApplicationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LdapToDataSyncScheduleTest extends AbstractWebApplicationTest {
	
	@Autowired
	private LdapToDataSyncSchedule ldapToDataSyncSchedule;
	
	@Test
	public void DailyLdapToDBSyncTest() {
		
		ldapToDataSyncSchedule.DailyLdapToDBSync();
		
	}
	
}
