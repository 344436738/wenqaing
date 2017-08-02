package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service;

import java.util.List;
import java.util.Map;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Menu;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.User;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.PageData;

public interface MenuService {
	 
	   void updateLastLogin(Menu menu);
	   Menu getUserAndRoleById(String id);
	   List<Menu> listAllMenuQx(String map);
	   
	   
}
