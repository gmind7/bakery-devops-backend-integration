package io.gmind7.devops.ldap.group;

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
public class LdapGroupRepositoryImpl implements LdapGroupRepository {

    private final LdapTemplate ldapTemplate;

    @Autowired
    public LdapGroupRepositoryImpl(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }
    
    @Override
    @Cacheable(value="ldap_group", key="#dn")
	public LdapGroup findByDn(String dn) {
    	if(!dn.endsWith(LdapType.BASE_DN.getValue())) return null;
		List<LdapGroup> group = findAll(query().base(dn).where("objectClass").is("group"));
		return group.size() <=0 ? null : group.get(0);	
	}

	@Override
	@Cacheable(value="ldap_group", key="#id")
	public LdapGroup findById(String id) {
		List<LdapGroup> group = findAll(query().base(LdapType.GROUP_DN.getValue()).where("objectClass").is("group").and("sAMAccountName").is(id));
		return group.size() <=0 ? null : group.get(0);	
	}

	@Override
	@Cacheable(value="ldap_group")
	public List<LdapGroup> findAll() {
		return findAll(query().base(LdapType.GROUP_DN.getValue()).where("objectClass").is("group"));
	}

	@Override
	@Cacheable(value="ldap_group", key="{#query.base, #query.filter}")
	public List<LdapGroup> findAll(LdapQuery query) {
		List<LdapGroup> groups = ldapTemplate.search(query, GROUP_CONTEXT_MAPPER);
		return groups == null ? Lists.newArrayList(new LdapGroup()) : groups;
	}
	
	private final static ContextMapper<LdapGroup> GROUP_CONTEXT_MAPPER = new AbstractContextMapper<LdapGroup>() {
		@Override
		public LdapGroup doMapFromContext(DirContextOperations context) {
        	//LdapName dn = LdapUtils.newLdapName(context.getDn());
        	LdapGroup group = new LdapGroup();
        	group.setId(context.getStringAttribute("sAMAccountName"));
        	group.setName(StringUtils.defaultIfBlank(context.getStringAttribute("name"),""));
        	group.setDisplayName(StringUtils.defaultIfBlank(context.getStringAttribute("displayName"),""));
        	group.setMail(StringUtils.defaultIfBlank(context.getStringAttribute("mail"),""));
        	group.setDescription(StringUtils.defaultIfBlank(context.getStringAttribute("description"),""));
        	String[] members = context.getStringAttributes("member");
        	String[] memberGroups = context.getStringAttributes("memberOf");
        	if(members!=null) group.setMembers(Lists.newArrayList(members));
        	if(memberGroups!=null) group.setMemberGroups(Lists.newArrayList(memberGroups));
			return group;
		}
	};
    
}
