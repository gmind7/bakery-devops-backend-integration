package io.gmind7.spring.foundation.constant;

public enum EnvType {
	
	SPRING_PROFILE_ACTIVE("spring.profiles.active"),
	DEFAULT_ACTIVE_PROFILE("loc");
	
	private String key;
	
	private EnvType(String key) {
		this.key = key;
	};
	
	public String getKey(){
		return this.key;
	}
	
}
