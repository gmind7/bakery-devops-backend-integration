package io.gmind7.devops.jpa.group;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
	
	Group findByGroupId(String groupId);
	
}