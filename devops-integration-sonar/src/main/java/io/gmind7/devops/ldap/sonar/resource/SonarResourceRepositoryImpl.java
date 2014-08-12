package io.gmind7.devops.ldap.sonar.resource;

import java.util.List;

import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.services.ResourceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

@Repository
public class SonarResourceRepositoryImpl implements SonarResourceRepository {

	private final Sonar sonarTemplate;
	private final ResourceQuery sonarResourceQuery;
	private final SonarResourceMapper sonarResourceMapper;
	
	@Autowired
	public SonarResourceRepositoryImpl(Sonar sonarTemplate, ResourceQuery sonarResourceQuery, SonarResourceMapper sonarResourceMapper) {
		this.sonarTemplate = sonarTemplate;
		this.sonarResourceQuery = sonarResourceQuery;
		this.sonarResourceMapper = sonarResourceMapper;
	}

	@Override
	@Cacheable(value="sonar_resource", key="#id")
	public SonarResource findOne(String id) {
		return findOne(sonarResourceQuery.setResourceKeyOrId(id));
	}
	
	@Override
	@Cacheable(value="sonar_resource", key="#query.resourceKeyOrId")
	public SonarResource findOne(ResourceQuery query) {
		List<SonarResource> resources = findAll(query);
		return resources.size() <=0 ? null : resources.get(0);
	}

	@Override
	@Cacheable(value="sonar_resource")
	public List<SonarResource> findAll() {
		return findAll(sonarResourceQuery);
	}

	@Override
	@Cacheable(value="sonar_resource", key="#query.resourceKeyOrId")
	public List<SonarResource> findAll(ResourceQuery query) {
		List<org.sonar.wsclient.services.Resource> sonarResources = sonarTemplate.findAll(query);
		List<SonarResource> resources = Lists.newArrayList();
		if(sonarResources!=null) {
			for(org.sonar.wsclient.services.Resource sonarResource : sonarResources) {
				resources.add(sonarResourceMapper.apply(sonarResource));
			}
		}
		return resources;
	}
	
}
