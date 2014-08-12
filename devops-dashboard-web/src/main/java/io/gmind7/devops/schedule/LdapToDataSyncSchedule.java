package io.gmind7.devops.schedule;

import io.gmind7.devops.jpa.config.SpringAuditorAware;
import io.gmind7.devops.jpa.group.Group;
import io.gmind7.devops.jpa.group.GroupRepository;
import io.gmind7.devops.jpa.membership.Membership;
import io.gmind7.devops.jpa.membership.MembershipIDs;
import io.gmind7.devops.jpa.membership.MembershipRepository;
import io.gmind7.devops.jpa.user.User;
import io.gmind7.devops.jpa.user.UserRepository;
import io.gmind7.devops.ldap.group.LdapGroup;
import io.gmind7.devops.ldap.group.LdapGroupRepository;
import io.gmind7.devops.ldap.person.LdapPerson;
import io.gmind7.devops.ldap.person.LdapPersonRepository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LdapToDataSyncSchedule {
	
	@Autowired
	private LdapPersonRepository ldapPersonRepository;
	
	@Autowired
	private LdapGroupRepository ldapGroupRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private MembershipRepository membershipRepository;
	
	@Autowired
	private SpringAuditorAware springAuditorAware;
	
	@PostConstruct
	public void postConstruct() {
		springAuditorAware.setAuditor("system");
	}
	
	@Scheduled(cron="0 3 * * * *")
	public void DailyLdapToDBSync() {
		
		List<LdapGroup> ldapGroups = ldapGroupRepository.findAll();
		
		for(LdapGroup ldapGroup: ldapGroups) {
			
			Group group = groupRepository.findByGroupId(ldapGroup.getId());
			
			if(group==null) group = new Group();
			
			group.setGroupId(ldapGroup.getId());
			group.setName(ldapGroup.getName());
			group.setLowerName(ldapGroup.getName().toLowerCase());
			group.setDisplayName(ldapGroup.getDisplayName());
			group.setMail(ldapGroup.getMail());
			group.setDescription(ldapGroup.getDescription());
			
			group = groupRepository.save(group);
			
			LdapPersonToDBPerson(ldapGroup);
			UserNGroupToMembership(ldapGroup, group);
			
		}
		
	}
	
	private void LdapPersonToDBPerson(LdapGroup ldapGroup) {
		
		List<String> ldapPersonDns = ldapGroup.getMembers();
		
		for(String personDn : ldapPersonDns) {
			
			LdapPerson ldapPerson = ldapPersonRepository.findByDn(personDn);
			
			if(ldapPerson==null) continue;
			
			User user = userRepository.findByUserId(ldapPerson.getId());
			
			if(user==null) user = new User();
			
			user.setUserId(ldapPerson.getId());
			user.setName(ldapPerson.getName());
			user.setLowerName(ldapPerson.getName().toLowerCase());
			user.setDisplayName(ldapPerson.getDisplayName());
			user.setMail(ldapPerson.getMail());
			user.setDescription(ldapPerson.getDisplayName());
			
			userRepository.save(user);
			
		}
		
	}

	private void UserNGroupToMembership(LdapGroup ldapGroup, Group group) {
		
		List<String> memberDns = ldapGroup.getMembers();
		
		for(String memberDn: memberDns) {
			
			LdapPerson ldapPerson = ldapPersonRepository.findByDn(memberDn);
			
			if(ldapPerson==null) continue;
			if(ldapPerson.getMail()==null) continue;
			
			User user = userRepository.findByUserId(ldapPerson.getId());
			
			if(user!=null) {
				MembershipIDs membershipIDs = new MembershipIDs(group.getId(), user.getId());
				
				Membership membership = membershipRepository.findOne(membershipIDs);
				
				if(membership==null) {
					membershipRepository.save(new Membership(membershipIDs));
				}
			}
			
		}
		
	}

}
