package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.User;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.PageData;
@WebService(targetNamespace="http://impl.service.wenqiang.cn.wenqiang_mavenWeb.wenqiang_web/")
@SOAPBinding(style = Style.RPC)
public interface UserWebService {
	String getUserByNameAndPwd(@WebParam(name = "pd")PageData pd);
	String updateLastLogin(@WebParam(name = "user")User user);
	   String getUserAndRoleById(@WebParam(name = "id")String id);
	   String selectByPrimaryKey(@WebParam(name = "userId")String userId);
	   
}
