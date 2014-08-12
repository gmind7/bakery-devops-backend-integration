package io.gmind7.devops.ldap.common;

import java.util.List;

import org.springframework.ldap.query.LdapQuery;

public interface LdapRepository<T, ID> {
	
	T findByDn(ID id);
	
	T findById(ID id);
	
	List<T> findAll();
	
	List<T> findAll(LdapQuery query);
	
}
