package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.City;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Pager;

public  class MongoDaoImpl implements MongoDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<City> findAll(Pager page) {
		Query query = new Query();
		
		query.skip(page.getPageId()).limit(page.getPageSize());
		return mongoTemplate.find(query, City.class);
	}

	public List<City> findByRegex(String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Criteria criteria = new Criteria("name").regex(pattern.toString());
		return mongoTemplate.find(new Query(criteria), City.class);
	}



}