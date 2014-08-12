package io.gmind7.devops.jpa.membership;

import io.gmind7.devops.jpa.common.Description;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity(name = "devops_membership")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "jpa_devops_membership")
@Description("유저-그룹릴레이션")
public class Membership implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	public Membership(){		
	}
	
	public Membership(MembershipIDs ids) {
		this.ids = ids;
	}
	
	@EmbeddedId
	private MembershipIDs ids;
	
	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
