package com.bounter.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bounter.shiro.entity.BterUser;
import com.bounter.shiro.entity.ResponseData;

/**
 * Restful 登录控制器
 * @author simon
 *
 */
@RestController
public class LoginController {

	/**
	 * Shiro Restful 登录接口
	 * @param user
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    private ResponseData<String> shiroLogin(BterUser user, HttpServletRequest request){
    	ResponseData<String> reponseData = new ResponseData<>();
    	
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
        	//shiro登录,会跳到我们自定义的realm中
            subject.login(token);
            //保存用户到session中
            request.getSession().setAttribute("user", user);
            reponseData.setData("登录成功！");
            reponseData.setSuccess(true);
        } catch (Exception ex) {		
        	reponseData.setData("登录失败！");
        	reponseData.setSuccess(false);
        	reponseData.setErrorMsg("用户名或密码错误！");
        } 
        
        return reponseData;
    }

    /**
     * Shiro Restful 注销接口
     * @return
     */
    @RequestMapping("/logout")
    private ResponseData<String> logout(){
    	ResponseData<String> reponseData = new ResponseData<>();
        SecurityUtils.getSubject().logout();
        reponseData.setData("注销成功！");
        reponseData.setSuccess(true);
        return reponseData;
    }
    
    /**
     * 需要角色的资源的Restful接口
     * @return
     */
    @RequestMapping("/role/resource")
    private ResponseData<String> accessWithRole() {
    	ResponseData<String> reponseData = new ResponseData<>();
    	
        reponseData.setData("成功访问admin角色才能访问的资源");
        reponseData.setSuccess(true);
        
        return reponseData;
    }
    
    /**
     * 需要权限的资源的Restful接口
     * @return
     */
    @RequestMapping("/permission/resource")
    private ResponseData<String> accessWithPermission() {
    	ResponseData<String> reponseData = new ResponseData<>();
    	
        reponseData.setData("成功访问用户操作权限才能访问的资源");
        reponseData.setSuccess(true);
        
        return reponseData;
    }
    
    /**
     * 权限不足时的Restful接口
     * @return
     */
    @RequestMapping("/unauthorized")
    private ResponseData<String> unauthorized() {
    	ResponseData<String> reponseData = new ResponseData<>();
    	
        reponseData.setData("权限不足！");
        reponseData.setSuccess(false);
        reponseData.setErrorMsg("用户没有相应的角色或权限！");
        
        return reponseData;
    }

    /**
     * 没有登录时的Restful接口
     * @return
     */
    @RequestMapping("/unauthenticate")
    private ResponseData<String> unauthenticate() {
        ResponseData<String> reponseData = new ResponseData<>();

        reponseData.setData("没有登录！");
        reponseData.setSuccess(false);
        reponseData.setErrorMsg("用户没有登录认证！");

        return reponseData;
    }
}
