package io.gmind7.devops.ldap.sonar.measure;

import java.util.List;

import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.services.ResourceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

@Repository
public class SonarMeasureRepositoryImpl implements SonarMeasureRepository {

	private final Sonar sonarTemplate;
	private final ResourceQuery sonarResourceQuery;
	private final SonarMeasureMapper sonarMeasureMapper;
	
	@Autowired
	public SonarMeasureRepositoryImpl(Sonar sonarTemplate, ResourceQuery sonarResourceQuery, SonarMeasureMapper sonarMeasureMapper) {
		this.sonarTemplate = sonarTemplate;
		this.sonarResourceQuery = sonarResourceQuery;
		this.sonarMeasureMapper = sonarMeasureMapper;
	}

	@Override
	public SonarMeasure findOne(String id) {
		return findOne(sonarResourceQuery.setResourceKeyOrId(id));
	}

	@Override
	public SonarMeasure findOne(ResourceQuery query) {
		List<SonarMeasure> measures = findAll(query);
		return measures.size() <= 0 ? null : measures.get(0);
	}

	@Override
	public List<SonarMeasure> findAll() {
		return findAll(sonarResourceQuery);
	}

	@Override
	public List<SonarMeasure> findAll(ResourceQuery query) {
		
		List<SonarMeasure> measures = Lists.newArrayList();
		
		List<org.sonar.wsclient.services.Resource> sonarResources = sonarTemplate.findAll(query);
		
		if(sonarResources!=null) {
			for(org.sonar.wsclient.services.Resource sonarResource : sonarResources) {
				List<org.sonar.wsclient.services.Measure> sonarMeasures = sonarResource.getMeasures();
				if(sonarMeasures!=null) {
					for(org.sonar.wsclient.services.Measure sonarMeasure : sonarMeasures) {
						measures.add(sonarMeasureMapper.apply(sonarResource, sonarMeasure));
					}
				}
			}
		}
		
		return measures;
	}

}
