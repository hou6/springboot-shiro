package com.houliu.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * 自定义的realm类
 * @author houliu
 *
 */
@Component
public class UserRealm extends AuthorizingRealm {

	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权逻辑");
		return null;
	}

	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		//假设数据库的用户名和密码
		String name = "eric";
		String password = "123456";
		/**
		 * 编写shiro判断逻辑，判断用户名和密码
		 */
		//判断用户名
		UsernamePasswordToken token = (UsernamePasswordToken)arg0;
		if (!token.getUsername().equals(name)) {
			//用户名不存在
			return null;  //shiro底层会抛出UnKnowAccountException
		}
		//判断密码
		return new SimpleAuthenticationInfo("",password,"");
	}

}
