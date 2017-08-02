package wenqiang_web.wenqiang_mavenWeb;



import java.net.URLEncoder;
import java.util.Date;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;



import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;



public class MyTestClient {
	public static void main(String[] args) throws Exception {
  
    String wsdlUrl="http://localhost:8081/wenqiang_mavenWeb/services/lMSWebService?wsdl";
    String id="1";
  /*  Object[] obs = CxfInvokeUtil.invoke(wsdlUrl,"selectByPrimaryKey",id);
    if(obs != null && obs.length > 0){
		String result = (String)obs[0];
		System.out.print(result);
		
		
		
	}
    
*/
	

	}
}
