package com.bounter.shiro.entity;

public class BterRole {

    private Long id;

    // 角色名称
    private String roleName;
    
    // 角色描述
    private String description;
    
    // 角色状态: 角色是否可用，0-不可用，1-可用
    private int enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
    
}
