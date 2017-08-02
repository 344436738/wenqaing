package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.quartz;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.UserMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.User;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.model.MsgaeEnty;

public class Task {
	
	@Autowired
    UserMapper  userService;
	public void generate() {
	      // 这里面 写你的方法
	User uu=null;
	try {
		uu = userService.selectByPrimaryKey("1");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		System.out.print("/////////////////////////////////////////////////"+uu.getName());
		
	    }

	
	public  void  getAccess_token(){
		String	urlstr=MsgaeEnty.urlstr.replace("APPID", MsgaeEnty.appid).replace("APPSECRET", MsgaeEnty.secret);
		URL url;
		String access_token="";
		try {
			url = new URL(urlstr);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();		
			http.setRequestMethod("GET");		
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");	
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
			InputStream is =http.getInputStream();
			int size =is.available();
			byte[] buf=new byte[size];
			is.read(buf);
			String resp =new String(buf,"UTF-8");
		    
		    System.out.println("getAccess_token resp:"+resp);
			JSONObject jsonObject =JSONObject.fromObject(resp);
			Object object =jsonObject.get("access_token");
			if(object !=null){
				access_token= String.valueOf(object);
				MsgaeEnty.access_token=access_token;
			}else{
				System.out.println("获取 access_token 失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
}
