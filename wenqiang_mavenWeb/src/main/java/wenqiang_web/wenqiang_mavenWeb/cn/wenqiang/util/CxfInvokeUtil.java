package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 
 * 使用cxf 调用webservice 接口
 * 
 * @author chenj
 *
 */
public class CxfInvokeUtil {

	static Logger logger = Logger.getLogger(CxfInvokeUtil.class);
	
	/**
	 * 
	 * 调用webservice 接口
	 * 
	 * @param wsdlUrl  wsdl 地址
	 *  
	 * @param method  调用方法名
	 * 
	 * @param params  接口传入参数
	 * 
	 * @return
	 * 
	 */
	public static synchronized Object[] invoke(String wsdlUrl,String method,Object... params){
		
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance(); 
		org.apache.cxf.endpoint.Client client = dcf.createClient(wsdlUrl); 
		
		Object[] objects = null;
		
		if(StringUtils.isEmpty(wsdlUrl)){
			
			logger.error("cxf 调用webservice 参数缺失：wsdl url 未传入");
			return objects;
		}
		
		if(StringUtils.isEmpty(method)){
			
			logger.error("cxf 调用webservice 执行方法名缺失：method 未传入");
			return objects;
		}
		
		try {

		       objects=client.invoke(method,params);

		    } catch (Exception e) {
		    	e.printStackTrace();
		    	logger.error("cxf 调用webservice 执行错误：",e);

		    }
		
		return objects;
	}
}
