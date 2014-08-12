package io.gmind7.devops.jpa.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByUserId(String userId);
	
}