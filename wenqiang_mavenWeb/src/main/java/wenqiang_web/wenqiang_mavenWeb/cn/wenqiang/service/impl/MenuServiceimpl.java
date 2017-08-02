package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.MenuMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.UserMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Menu;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.User;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.MenuService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.UserService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.PageData;
@Service("menuService")
@Transactional
public class MenuServiceimpl   implements MenuService {
	@Autowired
	MenuMapper  menumapper;

	@Override
	public void updateLastLogin(Menu menu) {
		menumapper.updateByPrimaryKey(menu);
		
	}

	@Override
	public Menu getUserAndRoleById(String id) {
		// TODO Auto-generated method stub
		return menumapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public List<Menu> listAllMenuQx(String map) {
		// TODO Auto-generated method stub
		List<Menu> menuList= menumapper.listAllMenuQx(map);
		for(Menu menu : menuList){
			menu.setSubMenu(listAllMenuQx(menu.getMenuId().toString()));
			menu.setTarget("treeFrame");
		}
		return menuList;
		
	}
	
}
