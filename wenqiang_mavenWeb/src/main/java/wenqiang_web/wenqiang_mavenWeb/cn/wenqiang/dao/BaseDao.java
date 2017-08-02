package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import java.util.List;  
import java.util.regex.Pattern;  

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.City;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.CityBoundary;


@Repository
public class BaseDao {  
	
	@Autowired
      public	MongoTemplate mongoTemplate;
	
	public BaseDao(){}
	
	public BaseDao(MongoTemplate mongoTemplate){
		this.mongoTemplate = mongoTemplate;
	}
	
    public List<City> findAll() {  
//    	Criteria criteria = new Criteria("name").regex("db.caonima.find().skip(0).limit(20)");
        return mongoTemplate.find(new Query(), City.class);  
    }  
  
    public void findAndModify(String id) {   
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), new Update().inc("age", 3), id);  
    }  
  
    public List<City> findByRegex(String regex) {  
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);  
        Criteria criteria = new Criteria("name").regex(pattern.toString());  
        return mongoTemplate.find(new Query(criteria), City.class);  
    }  
  
    public City findOne(String id) {  
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), City.class);  
    }  
  
      
    public void insert(City person) {  
        mongoTemplate.insert(person);  
    }  
  
      
    public void insert1(CityBoundary person) {  
        mongoTemplate.insert(person);  
    }  
  
    
    public void removeAll() {  
        List<City> list = this.findAll();  
        if(list != null){  
            for(City person : list){  
                mongoTemplate.remove(person);  
            }  
        }  
    }  
  
      
    public void removeOne(String id){  
        Criteria criteria = Criteria.where("id").in(id);  
        if(criteria == null){  
             Query query = new Query(criteria);  
             if(query != null && mongoTemplate.findOne(query, City.class) != null)  
                 mongoTemplate.remove(mongoTemplate.findOne(query, City.class));  
        }  
    }  
}
