package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.User;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.PageData;

public interface UserService {
	   User getUserByNameAndPwd(PageData pd);
	   void updateLastLogin(User user);
	   User getUserAndRoleById(String id);
	   
	   
}
