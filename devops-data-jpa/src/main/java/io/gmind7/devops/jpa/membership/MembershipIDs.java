package io.gmind7.devops.jpa.membership;

import io.gmind7.devops.jpa.common.Description;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Embeddable
public class MembershipIDs implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public MembershipIDs(){
		
	}
	
	public MembershipIDs(Long groupId, Long userId) {
		this.groupId = groupId;
		this.userId = userId;
	}
	
	@Column(name="GROUP_ID")
    @Description("부서아이디")
	private Long groupId;
    
	@Column(name="USER_ID")
    @Description("유저아이디")
    private Long userId;
    
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
