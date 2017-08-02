package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import java.util.List;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.task;

public interface taskMapper {
    int insert(task record);

    int insertSelective(task record);
    
    List<task> listAllMenuQxByPage();
    
    void deleteByPrimaryKey(Integer id);
    
    
    void updateByPrimaryKeySelective(task  ak);
    
}