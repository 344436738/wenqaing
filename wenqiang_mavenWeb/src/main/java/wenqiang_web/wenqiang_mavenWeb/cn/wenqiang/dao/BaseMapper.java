package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import java.util.List;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.BaseModel;


public interface BaseMapper<T> {

	public void add(T t);

	public void update(T t);

	public void updateBySelective(T t);

	public void delete(Object id);

	public int queryByCount(BaseModel model);

	public List<T> queryByList(BaseModel model);

	public T queryById(Object id);
	
	public void updateView(T t);
}
