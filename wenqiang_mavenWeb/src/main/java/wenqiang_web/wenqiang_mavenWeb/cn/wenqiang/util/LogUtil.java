package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {
	static{
		  PropertyConfigurator.configure("resources/log4j.properties");  
	 }
	
	 private static Log infoLogger = LogFactory.getLog("info");  
	 
	    
	 private static Log errorLogger = LogFactory.getLog("errorLogger");  
	 private static Log exceptionlog = LogFactory.getLog("exceptionlog");  
	 
	 public static void saveLog(String lev,String message){
			
		 if(lev.equals("1")){
			 infoLogger.info(message);  
		       
		}
		if(lev.equals("2")){
			errorLogger.error(message);  
		       
		}
		if(lev.equals("3")){
			exceptionlog.warn(message);  
		       
		
			
		}
		

	 }
	 
}
