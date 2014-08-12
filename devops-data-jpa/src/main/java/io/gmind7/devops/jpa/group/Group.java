package io.gmind7.devops.jpa.group;

import io.gmind7.devops.jpa.common.Description;
import io.gmind7.devops.jpa.user.User;

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

@Entity(name = "devops_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "jpa_devops_group")
@AttributeOverrides(@AttributeOverride(name="id", column = @Column(name = "ID")))
@Description("그룹")
public class Group extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	public Group(){		
	}
	
    public Group(Long id) {
        this.setId(id);
    }
    
    @Column(name="GROUP_ID", unique=true)
    @Description("LDAP아이디또는고유ID")
    private String groupId;
    
    @Column(name="NAME")
    @Description("부서명")
    private String name;
    
    @Column(name="LOWER_NAME")
    @Description("소문자부서명")
    private String lowerName;
    
    @Column(name="DISPLAY_NAME")
    @Description("표기명")    
    private String displayName;
    
    @Column(name="MAIL")
    @Description("메일주소")  
    private String mail;
    
    @Column(name="DESCRITION")
    @Description("설명")
    private String description;
    
    @ManyToMany
	@JoinTable(name = "nc_membership", joinColumns = {@JoinColumn(name = "GROUP_ID", nullable = false, updatable = false)}, 
			   inverseJoinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) })
    private List<User> users = Lists.newArrayList();
        
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
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
        return EqualsBuilder.reflectionEquals(this, obj, new String[]{"users"});
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[]{"users"});
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
