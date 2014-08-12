package io.gmind7.devops.jpa.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class SpringAuditorAware implements AuditorAware<String> {

	private String auditor;

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getCurrentAuditor() {
		return auditor;
	}

}
