package io.gmind7.devops.jpa.user;

import io.gmind7.devops.jpa.common.Description;
import io.gmind7.devops.jpa.group.Group;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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

import com.google.common.collect.Lists;

@Entity(name = "devops_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "jpa_devops_user")
@AttributeOverrides(@AttributeOverride(name="id", column = @Column(name = "ID")))
@Description("사용자")
public class User extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	public User(){		
	}
	
    public User(Long id) {
        this.setId(id);
    }
    
    @Column(name="USER_ID", unique=true)
    @Description("LDAP아이디또는기타ID")
    private String userId;
    
    @Column(name="NAME")
    @Description("사용자명")
    private String name;
    
    @Column(name="LOWER_NAME")
    @Description("소문자사용자명")
    private String lowerName;
    
    @Column(name="DISPLAYNAME")
    @Description("표기명")
    private String displayName;
    
    @Column(name="MAIL")
    @Description("메일")
    private String mail;
    
    @Column(name="DESCRIPTION")
    @Description("설명")
    private String description;
    
    @ManyToMany
	@JoinTable(name = "nc_membership", joinColumns = {@JoinColumn(name = "USER_ID", nullable = false, updatable = false)}, 
			   inverseJoinColumns = { @JoinColumn(name = "GROUP_ID", nullable = false, updatable = false) })
    private List<Group> groups = Lists.newArrayList();
        
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
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
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
        return EqualsBuilder.reflectionEquals(this, obj, new String[]{"groups"});
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[]{"groups"});
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
