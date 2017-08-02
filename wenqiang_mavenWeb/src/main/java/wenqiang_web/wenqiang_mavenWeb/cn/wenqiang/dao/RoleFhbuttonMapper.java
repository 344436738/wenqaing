package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.RoleFhbutton;

public interface RoleFhbuttonMapper {
    int deleteByPrimaryKey(String rbId);

    int insert(RoleFhbutton record);

    int insertSelective(RoleFhbutton record);

    RoleFhbutton selectByPrimaryKey(String rbId);

    int updateByPrimaryKeySelective(RoleFhbutton record);

    int updateByPrimaryKey(RoleFhbutton record);
}