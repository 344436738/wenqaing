package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.UserMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.taskMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.task;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.TaskService;
@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {
	@Autowired
	taskMapper  mapper;
	@Override
	public int insert(task record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(task record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public List<task> listAllMenuQxByPage() {
		// TODO Auto-generated method stub
		return mapper.listAllMenuQxByPage();
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		mapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void updateByPrimaryKeySelective(task ak) {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(ak);
	}

}
