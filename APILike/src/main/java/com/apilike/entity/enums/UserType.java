package com.apilike.entity.enums;

public enum UserType {

	PHYSICAL_PERSON(1, "Physical Person"),
	LEGAL_PERSON(2, "Legal Person");
	
	private int code;
	private String description;
	
	private UserType(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	public String getDescrition() {
		return description;
	}
	
	public static UserType toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		for (UserType x : UserType.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
	throw new IllegalArgumentException("id invild"+ code);
	}
	
}
