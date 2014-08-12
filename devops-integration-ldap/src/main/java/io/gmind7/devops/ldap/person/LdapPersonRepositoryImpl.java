package io.gmind7.devops.ldap.person;

import static org.springframework.ldap.query.LdapQueryBuilder.query;
import io.gmind7.devops.ldap.common.LdapType;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

@Repository
public class LdapPersonRepositoryImpl implements LdapPersonRepository {
	
	private final LdapTemplate ldapTemplate;
	
	@Autowired
	public LdapPersonRepositoryImpl(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
	
	@Override
	@Cacheable(value="ldap_person", key="#dn")
	public LdapPerson findByDn(String dn) {
		if(!dn.endsWith(LdapType.BASE_DN.getValue())) return null;
		List<LdapPerson> person = findAll(query().base(dn).where("objectClass").is("person"));
		return person.size() <= 0 ? null : person.get(0);	
	}
	
	@Override
	@Cacheable(value="ldap_person", key="#id")
	public LdapPerson findById(String id) {
		List<LdapPerson> person = findAll(query().base(LdapType.PERSON_DN.getValue()).where("objectClass").is("person").and("sAMAccountName").is(id));
		return person.size() <= 0 ? null : person.get(0);	
	}

	@Override
	@Cacheable(value="ldap_person")
	public List<LdapPerson> findAll() {
		return findAll(query().base(LdapType.PERSON_DN.getValue()).where("objectClass").is("person"));
	}

	@Override
	@Cacheable(value="ldap_person", key="{#query.base, #query.filter}")
	public List<LdapPerson> findAll(LdapQuery query) {
		List<LdapPerson> persons = ldapTemplate.search(query, PERSON_CONTEXT_MAPPER);
		return persons == null ? Lists.newArrayList(new LdapPerson()) : persons;
	}
	
	private final static ContextMapper<LdapPerson> PERSON_CONTEXT_MAPPER = new AbstractContextMapper<LdapPerson>() {
        @Override
		public LdapPerson doMapFromContext(DirContextOperations context) {
        	//LdapName dn = LdapUtils.newLdapName(context.getDn());
        	LdapPerson person = new LdapPerson();
        	person.setId(context.getStringAttribute("sAMAccountName"));
        	person.setName(StringUtils.defaultIfBlank(context.getStringAttribute("name"),""));
        	person.setFirstName(StringUtils.defaultIfBlank(context.getStringAttribute("firstName"),""));
        	person.setLastName(StringUtils.defaultIfBlank(context.getStringAttribute("lastName"),""));
        	person.setDisplayName(StringUtils.defaultIfBlank(context.getStringAttribute("displayName"),""));
        	person.setEmployeeID(StringUtils.defaultIfBlank(context.getStringAttribute("employeeID"),""));
        	person.setTitle(StringUtils.defaultIfBlank(context.getStringAttribute("title"),""));
        	person.setUnit(StringUtils.defaultIfBlank(context.getStringAttribute("unit"),""));
        	person.setMail(StringUtils.defaultIfBlank(context.getStringAttribute("mail"),""));
        	person.setTelephoneNumber(StringUtils.defaultIfBlank(context.getStringAttribute("telephoneNumber"),""));
        	person.setDepartment(StringUtils.defaultIfBlank(context.getStringAttribute("department"),""));
        	String[] groups = context.getStringAttributes("memberOf");        	
        	if(groups!=null) person.setGroups(Lists.newArrayList(groups));
			return person;
		}
	};

}
