package io.gmind7.devops.ldap.sonar.resource;

import io.gmind7.devops.ldap.sonar.measure.SonarMeasure;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SonarResource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String key;
	private String name;
	private String longName;
	private String scope;
	private String qualifier;
	private String language;
	private String version;
	private String copy;
	private String description;
	private Date date;
	private List<SonarMeasure> measures;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCopy() {
		return copy;
	}

	public void setCopy(String copy) {
		this.copy = copy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public SonarMeasure getMeasure(String metricKey) {
	    for (SonarMeasure measure : getMeasures()) {
	      if (metricKey.equals(measure.getMetricKey())) {
	        return measure;
	      }
	    }
	    return null;
	  }

	public List<SonarMeasure> getMeasures() {
		return measures;
	}

	public void setMeasures(List<SonarMeasure> measures) {
		this.measures = measures;
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
