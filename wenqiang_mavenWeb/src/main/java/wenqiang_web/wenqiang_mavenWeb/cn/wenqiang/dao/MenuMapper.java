package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import java.util.List;
import java.util.Map;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> listAllMenuQx(String str);
    
    
    List<Menu> listAllMenuQxByPage(Map map);
}