package io.gmind7.devops.jpa.project;

import io.gmind7.devops.jpa.common.Description;
import io.gmind7.devops.jpa.group.Group;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name = "devops_project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "jpa_devops_project")
@AttributeOverrides(@AttributeOverride(name="id", column = @Column(name = "ID")))
@Description("프로젝트")
public class Project extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = -5824690050951755884L;

	public Project(){		
	}
	
    public Project(Long id) {
        this.setId(id);
    }
    
    @Column(name="NAME", unique=true)
    @Description("프로젝트명")
    private String name;
    
    @Column(name="LOWER_NAME", unique=true)
    @Description("소문자프로젝트명")
    private String lowerName;
    
    @Column(name="PROJECT_KEY", unique=true)
    @Description("프로젝트키")
    private String projectKey;
    
    @Column(name="DISPLAY_NAME")
    @Description("표기명")
    private String displayName;
    
    @Column(name="LANGUAGE")
    @Description("개발언어")
    private String language;
    
    @Column(name="VERSION")
    @Description("개발버전")
    private String version;
    
    @Column(name="SONAR_ID")
    @Description("프로젝트명")
    private String sonarId;
    
    @Column(name="DESCRIPTION")
    @Description("프로젝트명")
    private String description;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Group group;
        
    @CreatedBy
    @Column(name="CREATE_BY", updatable=false)
    @Description("생성아이디")
	private String createdBy;
	
    @CreatedDate
    @Column(name="CREATE_DATE", updatable=false)
    @Type(type="java.sql.Timestamp")   
    @Description("생성일시")
	private Date createdDate;
	
    @LastModifiedBy
    @Column(name="LAST_MODIFIED_BY", updatable=false)
    @Description("수정아이디")
	private String lastModifiedBy;
	
	@LastModifiedDate
	@Column(name="LAST_MODIFIED_DATE", updatable=false)
	@Description("수정일시")
	private Date lastModifiedDate;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLowerName() {
		return lowerName;
	}

	public void setLowerName(String lowerName) {
		this.lowerName = lowerName;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public String getSonarId() {
		return sonarId;
	}

	public void setSonarId(String sonarId) {
		this.sonarId = sonarId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, new String[]{"serialVersionUID"});
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[]{"serialVersionUID"});
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
