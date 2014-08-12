package io.gmind7.devops.ldap.sonar.common;

import java.util.List;

import org.sonar.wsclient.services.ResourceQuery;

public interface SonarRepository<T, ID> {
	
	T findOne(ID id);
	
	T findOne(ResourceQuery query);
	
	List<T> findAll();
	
	List<T> findAll(ResourceQuery query);
	
}
