package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.UserMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.User;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.UserService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.PageData;
@Service("userService")
@Transactional
public class UserServiceimpl   implements UserService {
	@Autowired
	UserMapper  usermapper;
	  public User getUserByNameAndPwd(PageData pd) {
		  
		return usermapper.selectUserLogin(pd);
	}
	@Override
	public void updateLastLogin(User user) {
		usermapper.updateByPrimaryKey(user);
		
	}
	@Override
	public User getUserAndRoleById(String id) {
		// TODO Auto-generated method stub
		return usermapper.getUserAndRoleById(id);
	}
}
