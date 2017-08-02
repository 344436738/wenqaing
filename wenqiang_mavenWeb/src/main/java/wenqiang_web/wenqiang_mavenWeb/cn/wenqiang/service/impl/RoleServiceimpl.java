package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.RoleMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Role;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.RoleService;
@Service("roleService")
@Transactional
public class RoleServiceimpl implements RoleService {
 @Autowired
 RoleMapper rolemapper;
	@Override
	public int deleteByPrimaryKey(String roleId) {
		// TODO Auto-generated method stub
		return rolemapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int insert(Role record) {
		// TODO Auto-generated method stub
		return rolemapper.insert(record);
	}

	@Override
	public int insertSelective(Role record) {
		// TODO Auto-generated method stub
		return rolemapper.insertSelective(record);
	}

	@Override
	public Role selectByPrimaryKey(String roleId) {
		// TODO Auto-generated method stub
		return rolemapper.selectByPrimaryKey(roleId);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		// TODO Auto-generated method stub
		return rolemapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		// TODO Auto-generated method stub
		return rolemapper.updateByPrimaryKeySelective(record);
	}
    
}