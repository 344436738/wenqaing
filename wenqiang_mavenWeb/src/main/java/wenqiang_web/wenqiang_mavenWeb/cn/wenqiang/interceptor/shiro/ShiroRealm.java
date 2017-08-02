package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.interceptor.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.User;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.UserService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.PageData;

public class ShiroRealm extends AuthorizingRealm {
	@Resource
    UserService  userService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		 String username = (String)token.getPrincipal();
		
		 PageData pd=new PageData();
		 pd.put("USERNAME", username);
			
			
			User	user = userService.getUserByNameAndPwd(pd);//根据用户名和密码去读取用户信息
		 //得到用户名 
		 ByteSource bytes = ByteSource.Util.bytes(user.getUsername()
					+ user.getSalt());
			/**
			 * 获得用户的相关密码信息后交给RetryLimitHashedCredentialsMatcher来处理
			 * 
			 * 
			 * @see RetryLimitHashedCredentialsMatcher
			 * 
			 * */
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
					user.getUsername(), user.getPassword(), bytes, getName());
			return authenticationInfo;
	     
	    
	}
	   

}
