package com.bounter.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bounter.shiro.entity.BterPermission;
import com.bounter.shiro.entity.BterRole;
import com.bounter.shiro.entity.BterUser;
import com.bounter.shiro.service.UserService;

/**
 * 
 * @author simon
 *
 */
@Component
public class BterRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	private String authorizationCacheName = "authorizationCache";

	@Override
	public String getAuthorizationCacheName() {
		return authorizationCacheName;
	}

	@Override
	public void setAuthorizationCacheName(String authorizationCacheName) {
		this.authorizationCacheName = authorizationCacheName;
	}

	/**
	 * 获取当前登陆成功的用户的角色和权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户名
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//查询用户的角色
		BterRole role = userService.getUserRole(userName);
		Set<String> roles = new HashSet<>();
		roles.add(role.getRoleName());
		authorizationInfo.setRoles(roles);
		//查询用户的权限
		List<BterPermission> permList = userService.getUserPermissions(userName);
		Set<String> perms = new HashSet<>();
		for(BterPermission perm : permList) {
			perms.add(perm.getPermValue());
		}
		authorizationInfo.setStringPermissions(perms);
		return authorizationInfo;
	}

	/**
	 * 根据当前登录的用户，获取认证信息,给Shiro进行认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户名
		String userName = (String) token.getPrincipal();
		// 根据用户名查询用户信息，不涉及密码验证
		BterUser user = userService.findByUserName(userName);
		if (user != null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), "bterRealm");
			return authcInfo;
		}
		return null;
	}

}
