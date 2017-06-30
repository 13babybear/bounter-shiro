package com.bounter.shiro.service;

import java.util.List;

import com.bounter.shiro.entity.BterPermission;
import com.bounter.shiro.entity.BterRole;
import com.bounter.shiro.entity.BterUser;

public interface UserService {
	
	BterUser findByUserName(String userName);
	
	BterRole getUserRole(String userName);
	
	List<BterPermission> getUserPermissions(String userName);
}
