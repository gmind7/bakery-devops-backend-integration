package io.gmind7.devops.ldap.sonar.resource;

import io.gmind7.devops.ldap.sonar.measure.SonarMeasureMapper;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class SonarResourceMapper {

	@Autowired
	private SonarMeasureMapper sonarMeasureMapper;
	
	public SonarResource apply(org.sonar.wsclient.services.Resource input) {
		
		io.gmind7.devops.ldap.sonar.resource.SonarResource output = new io.gmind7.devops.ldap.sonar.resource.SonarResource();
		
		output.setId(String.valueOf(input.getId()));
		output.setKey(StringUtils.defaultIfBlank(input.getKey(),""));
		output.setName(StringUtils.defaultIfBlank(input.getName(),""));
		output.setLongName(StringUtils.defaultIfBlank(input.getLongName(),""));
		output.setScope(StringUtils.defaultIfBlank(input.getScope(),""));
		output.setQualifier(StringUtils.defaultIfBlank(input.getQualifier(),""));
		output.setLanguage(StringUtils.defaultIfBlank(input.getLanguage(),""));
		output.setVersion(StringUtils.defaultIfBlank(input.getVersion(),""));
		output.setCopy(StringUtils.defaultIfBlank(String.valueOf(input.getCopy()),""));
		output.setDescription(StringUtils.defaultIfBlank(input.getDescription(),""));
		output.setDate(input.getDate());
		
		List<org.sonar.wsclient.services.Measure> sonarMeasures = input.getMeasures();
		
		if(sonarMeasures!=null) {
			List<io.gmind7.devops.ldap.sonar.measure.SonarMeasure> measures = Lists.newArrayList();
			for(org.sonar.wsclient.services.Measure sonarMeasure : sonarMeasures) {
				measures.add(sonarMeasureMapper.apply(input, sonarMeasure));
			}
			output.setMeasures(measures);
		} else {			
			output.setMeasures(Lists.newArrayList(new io.gmind7.devops.ldap.sonar.measure.SonarMeasure()));
		}
		
		return output;
	}

}
