package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl;


import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.UserMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.User;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.UserService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.UserWebService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.PageData;
public class UserWebServiceimpl   implements UserWebService {
	@Autowired
	UserMapper  usermapper;
	  public String getUserByNameAndPwd(@WebParam(name = "pd")PageData pd) {
		  Gson gson = new Gson();
		  String fenInformation = gson.toJson(usermapper.selectUserLogin(pd));
		return fenInformation;
	}
	public String updateLastLogin(@WebParam(name = "user")User user) {
		usermapper.updateByPrimaryKey(user);
		return "更新成功";
	}
	public String getUserAndRoleById(@WebParam(name = "id")String id) {
		// TODO Auto-generated method stub
		 Gson gson = new Gson();
		  String fenInformation = gson.toJson(usermapper.getUserAndRoleById(id));
		return fenInformation;
	}
	public String selectByPrimaryKey(@WebParam(name = "userId")String userId) {
		// TODO Auto-generated method stub
		 Gson gson = new Gson();
		  String fenInformation = gson.toJson(usermapper.selectByPrimaryKey(userId));
		return fenInformation;
	}
	
	
}
