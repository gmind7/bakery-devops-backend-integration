package io.gmind7.devops.ldap.sonar.measure;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SonarMeasure implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String resourceId;
	private String resourcekey;
	private String resourceName;
	private String metricKey;
	private String metricName;
	private String value;
	private String formattedValue;
	private String data;
	private String characteristicKey;
	private String characteristicName;

	private String trend;
	private String var;

	private String ruleKey;
	private String ruleName;
	private String ruleSeverity;
	
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourcekey() {
		return resourcekey;
	}

	public void setResourcekey(String resourcekey) {
		this.resourcekey = resourcekey;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getMetricKey() {
		return metricKey;
	}

	public void setMetricKey(String metricKey) {
		this.metricKey = metricKey;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFormattedValue() {
		return formattedValue;
	}

	public void setFormattedValue(String formattedValue) {
		this.formattedValue = formattedValue;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCharacteristicKey() {
		return characteristicKey;
	}

	public void setCharacteristicKey(String characteristicKey) {
		this.characteristicKey = characteristicKey;
	}

	public String getCharacteristicName() {
		return characteristicName;
	}

	public void setCharacteristicName(String characteristicName) {
		this.characteristicName = characteristicName;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getRuleKey() {
		return ruleKey;
	}

	public void setRuleKey(String ruleKey) {
		this.ruleKey = ruleKey;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleSeverity() {
		return ruleSeverity;
	}

	public void setRuleSeverity(String ruleSeverity) {
		this.ruleSeverity = ruleSeverity;
	}

	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, new String[]{"Serializable"});
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[]{"Serializable"});
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
