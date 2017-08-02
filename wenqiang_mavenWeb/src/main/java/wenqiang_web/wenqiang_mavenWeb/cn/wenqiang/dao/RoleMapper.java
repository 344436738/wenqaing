package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}