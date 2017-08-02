package wenqiang_web.wenqiang_mavenWeb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.util.Hash;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.MenuMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Menu;

public class TestMybatis {
	
	
    public static void main(String[] args) {
    	 ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
    	    //从配置文件中取出id为udao的UserDao的对象
    	 MenuMapper userDao=(MenuMapper)context.getBean("menuMapper");
    	 Map map=new HashMap();
    	 map.put("pageSize",7);
    	 map.put("pageNo", 1);
    	 Menu mm=new Menu();
    	 mm.setParentId("0");
    	 map.put("meun",mm );
    	 List<Menu>  men=userDao.listAllMenuQxByPage(map);
    	 System.out.println("////////////////////////////////"+men.toString()+"+++++"+men.size());
	}

	
}
