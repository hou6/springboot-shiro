package com.houliu.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 *   Shiro的配置类
 * @author houliu
 *
 */
@Configuration
public class ShiroConfig {
	
	/**
	 * 创建ShiroFilterFactoryBean	
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		/**
		 * 	shiro内置过滤器，可以实现权限相关的拦截器
		 * 	常用的过滤器：
		 * 		anon: 无需认证即可访问
		 * 		authc: 必须认证才可访问
		 * 		user: 如果使用rememberMe功能可以直接访问
		 * 		perms: 该资源必须得到资源权限才可以访问
		 * 		role: 该资源必须得到角色权限才可以访问
		 */
		Map<String,String> filterMap = new LinkedHashMap<String, String>();
//		filterMap.put("/add", "authc");    // 必须认证才可访问
//		filterMap.put("/update", "authc");
		filterMap.put("/testThymeleaf", "anon");  //表示 /testThymeleaf 这个请求不用认证即可访问
		//放行login.html
		filterMap.put("/login", "anon");  //表示 /testThymeleaf 这个请求不用认证即可访问
		filterMap.put("/add", "perms[user:add]");   //user:add 为自定义的授权字符串
		filterMap.put("/update", "perms[user:update]");   //user:update 为自定义的授权字符串
		filterMap.put("/*", "authc");   //表示/下的所有资源都使用authc过滤,这个过滤必须写在最后
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		//修改调整的登录页面
		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		//设置未授权的提示页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//关联realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	
	/**
	 * 创建realm
	 */
	@Bean(name = "userRealm")
	public UserRealm getRealm() {
		return new UserRealm();
	}
	
	/**
	 * 配置ShiroDialect,用于thymeleaf和shiro整合使用
	 */
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}
	
	

}
