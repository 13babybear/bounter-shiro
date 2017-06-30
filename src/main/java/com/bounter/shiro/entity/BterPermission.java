package com.bounter.shiro.entity;

public class BterPermission {

    private Long id;
    
    private String permName;
    
    private String permValue;
    
    private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermName() {
		return permName;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

	public String getPermValue() {
		return permValue;
	}

	public void setPermValue(String permValue) {
		this.permValue = permValue;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
