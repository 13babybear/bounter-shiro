package com.bounter.shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bounter.shiro.entity.BterPermission;
import com.bounter.shiro.entity.BterRole;
import com.bounter.shiro.entity.BterUser;
import com.bounter.shiro.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public BterUser findByUserName(String userName) {
		// hardcode实现，省去数据库相关操作
		if("Bounter".equals(userName)) {
			BterUser user = new BterUser();
			user.setId(100001L);
			user.setUserName("Bounter");
			user.setPassword("111111");
			user.setEnabled(1);
			user.setRoleId(101L);
			return user;
		}
		return null;
	}

	@Override
	public BterRole getUserRole(String userName) {
		// hardcode实现，省去数据库相关操作
		if("Bounter".equals(userName)) {
			BterRole role = new BterRole();
			role.setId(101L);
			role.setRoleName("admin");
			role.setDescription("系统管理员");
			role.setEnabled(1);
			return role;
		}
		return null;
	}

	@Override
	public List<BterPermission> getUserPermissions(String userName) {
		// hardcode实现，省去数据库相关操作
		if("Bounter".equals(userName)) {
			List<BterPermission> perms = new ArrayList<>();
			BterPermission perm = new BterPermission();
			perm.setId(1001L);
			perm.setPermName("操作订单");
			perm.setPermValue("user:*");
			perm.setRoleId(101L);
			perms.add(perm);
			return perms;
		}
		return null;
	}

}
