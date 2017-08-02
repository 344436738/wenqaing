package wenqiang_web.wenqiang_mavenWeb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.BaseDao;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.MenuMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.City;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.CityBoundary;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.DocxToHtml;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.ReadHtml;

import org.apache.commons.lang3.StringEscapeUtils;






public class MapbarTest {


   @Test
   public void getHtml() throws TransformerException, IOException, ParserConfigurationException{
	   String str=   DocxToHtml.getHtml("docx", "D:/微信企业号使用文档.docx");
	 String ss=  StringEscapeUtils.unescapeHtml4(ReadHtml.getHtml(str));
	 
	 System.out.println(ss);
   }
	
	
	
	@Test
	public void getCityBoundLatLonByKeyword() throws Exception{
		
		 ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
 	    //从配置文件中取出id为udao的UserDao的对象
 	    BaseDao userDao=(BaseDao)context.getBean("baseDao");
  	  
		String result = null;
		String filePath = "D:\\city(1).txt";
		
		//String url = "http://navgo.vicp.net:8/v1/district/center?region=";
		String url="http://navgo.vicp.net:8/v1/district/boundary?region=";
        File file=new File(filePath);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "gbk");
        BufferedReader br = new BufferedReader(isr);
        
        StringBuilder builder = new StringBuilder();
        while(br.readLine() != null){
        	builder.append(br.readLine());
        }
        String lineTxt = builder.toString();
        System.out.println(lineTxt);
        br.close();
		
		String[] len = lineTxt.split(",");
        
     
        for(int i=0;i<len.length;i++){
//    		result = mapbarServiceManager.getCityBoundLatLonByKeyword(len[i].trim());
        	result = HttpUtil.sendGet(url, len[i]);
        	JSONObject jsStr = JSONObject.fromObject(result);
        	if(jsStr.getString("status").equals("0")){
        		String str1=jsStr.getString("results");
            	JSONObject jsStr1 = JSONObject.fromObject(str1);
            	String str2=jsStr1.getString("geometry");
            	JSONObject jsStr2 = JSONObject.fromObject(str2);
            	String a=jsStr1.getString("properties");
            	JSONObject jsStr3 = JSONObject.fromObject(a);
            	CityBoundary city=new CityBoundary();
            	city.setAd_code(jsStr3.getString("ad_code"));
            	city.setCoordinates(jsStr2.getString("coordinates"));
            	city.setName_chn(jsStr3.getString("name_chn"));
            	userDao.insert1(city);
        	}
        
        	
        	
       
        	
             
        }
        
      

		
		
		
	}
	
	
	
}
