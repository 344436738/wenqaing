package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.City;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Pager;


public interface MongoDao {
	
	//全部查询,并且降序排列
	public List<City> findAll(Pager page);
	
	//模糊查询
	public List<City> findByRegex(String regex);
	
}
