package io.gmind7.devops.ldap.common;

import static org.junit.Assert.assertNotNull;
import static org.springframework.ldap.query.LdapQueryBuilder.query;
import io.gmind7.devops.ldap.common.LdapType;
import io.gmind7.spring.foundation.config.AbstractApplicationTest;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;

public class LdapQueryTest extends AbstractApplicationTest {
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Test	
	public void ldapQueryTest(){
		LdapQuery query = query().base(LdapType.BASE_DN.getValue()).attributes("sAMAccountName").where("objectclass").is("person").and("sAMAccountName").is("---");
		List<String> list = ldapTemplate.search(query,
		         new AttributesMapper<String>() {
		            public String mapFromAttributes(Attributes attrs) throws NamingException {
		               return (String) attrs.get("sAMAccountName").get();
		            }
		         });
		
		assertNotNull(list);
	}
	
	
}
