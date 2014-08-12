package io.gmind7.devops.jpa.membership;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MembershipRepository extends PagingAndSortingRepository<Membership, MembershipIDs> {

}