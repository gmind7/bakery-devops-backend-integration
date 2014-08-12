package io.gmind7.devops.ldap.common;

public enum LdapType {
	
	BASE_DN("DC=---,DC=---,DC=---"),
	PERSON_DN("OU=---,OU=---,DC=---,DC=---,DC=---"),
	GROUP_DN("OU=---,OU=---,OU=---,DC=---,DC=---,DC=----");
	         
	private String value;
	
	public String getValue() {
		return value;
	}
	
	LdapType(String value) {
		this.value = value;
		
	}
	
}
