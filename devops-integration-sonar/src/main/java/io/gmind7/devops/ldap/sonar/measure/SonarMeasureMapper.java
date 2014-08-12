package io.gmind7.devops.ldap.sonar.measure;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class SonarMeasureMapper {

	public SonarMeasure apply(org.sonar.wsclient.services.Resource inputResource, org.sonar.wsclient.services.Measure inputMeasure) {
		
		io.gmind7.devops.ldap.sonar.measure.SonarMeasure output = new io.gmind7.devops.ldap.sonar.measure.SonarMeasure();
		
		output.setResourceId(String.valueOf(inputResource.getId()));
		output.setResourcekey(StringUtils.defaultIfBlank(inputResource.getKey(),""));
		output.setResourceName(StringUtils.defaultIfBlank(inputResource.getName(),""));
		output.setMetricKey(StringUtils.defaultIfBlank(inputMeasure.getMetricKey(),""));
		output.setMetricName(StringUtils.defaultIfBlank(inputMeasure.getMetricName(),""));
		output.setValue(StringUtils.defaultIfBlank(String.valueOf(inputMeasure.getValue()),""));
		output.setFormattedValue(StringUtils.defaultIfBlank(inputMeasure.getFormattedValue(),""));
		output.setData(StringUtils.defaultIfBlank(inputMeasure.getData(),""));
		output.setCharacteristicKey(StringUtils.defaultIfBlank(inputMeasure.getCharacteristicKey(),""));
		output.setCharacteristicName(StringUtils.defaultIfBlank(inputMeasure.getCharacteristicName(),""));
		output.setTrend(StringUtils.defaultIfBlank(String.valueOf(inputMeasure.getTrend()),""));
		output.setVar(StringUtils.defaultIfBlank(String.valueOf(inputMeasure.getVar()),""));
		output.setRuleKey(StringUtils.defaultIfBlank(inputMeasure.getRuleKey(),""));
		output.setRuleName(StringUtils.defaultIfBlank(inputMeasure.getRuleName(),""));
		output.setRuleSeverity(StringUtils.defaultIfBlank(inputMeasure.getRuleSeverity(),""));
		
		return output;
	}

}
