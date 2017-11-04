package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;























import com.google.gson.Gson;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.WeixinAccount;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl.WeixinAccountServiceimpl;



@Controller
@RequestMapping("/weixinInit") 
public class WeixinInitAction extends BaseController {
	 private static final  String Token="test123";
	 private   String echostr;
	@RequestMapping(value="/doGet",method=RequestMethod.GET) 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String signature = request.getParameter("signature");
         String timestamp = request.getParameter("timestamp");
         String nonce = request.getParameter("nonce");
         String echostr = request.getParameter("echostr");
         if( isEmpty(signature)){
             return ;
	     }
	     if(isEmpty(timestamp)){
	             return ;
	     }
	     if(isEmpty(nonce)){
	             return ;
	     }
	     if(isEmpty(echostr)){
	             return ;
	     }
	     String[] ArrTmp = { Token, timestamp, nonce };
         Arrays.sort(ArrTmp);
         StringBuffer sb = new StringBuffer();
         for (int i = 0; i < ArrTmp.length; i++) {
                 sb.append(ArrTmp[i]);
         }
         String pwd = Encrypt(sb.toString());
         
         if(trim(pwd).equals(trim(signature))){
             this.echostr =echostr;
             if(echostr!=null && !"".equals(echostr)){
            	 response.getWriter().print(echostr);
             }
         }else{
             return ;
         }
	}
		
	private boolean isEmpty(String str){
	        return null ==str || "".equals(str) ? true :false;
	}
	private String trim(String str){
	        return null !=str  ? str.trim() : str;
	}
		
		 private String Encrypt(String strSrc) {
	         MessageDigest md = null;
	         String strDes = null;
	
	         byte[] bt = strSrc.getBytes();
	         try {
	                 md = MessageDigest.getInstance("SHA-1");
	                 md.update(bt);
	                 strDes = bytes2Hex(md.digest()); //to HexString
	         } catch (NoSuchAlgorithmException e) {
	                 System.out.println("Invalid algorithm.");
	                 return null;
	         }
	         return strDes;
	 }
	
	 public String bytes2Hex(byte[] bts) {
	         String des = "";
	         String tmp = null;
	         for (int i = 0; i < bts.length; i++) {
	                 tmp = (Integer.toHexString(bts[i] & 0xFF));
	                 if (tmp.length() == 1) {
	                         des += "0";
	                 }
	                 des += tmp;
	         }
	         return des;
	 }
	
	
	 @RequestMapping(value="/doGet",method=RequestMethod.POST)
		public void doPOST(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 InputStream is = request.getInputStream();
         // 取HTTP请求流长庿
         int size = request.getContentLength();
         // 用于缓存每次读取的数捿
         byte[] buffer = new byte[size];
         // 用于存放结果的数绿
         byte[] xmldataByte = new byte[size];
         int count = 0;
         int rbyte = 0;
         // 循环读取
         while (count < size) { 
                 // 每次实际读取长度存于rbyte丿
                 rbyte = is.read(buffer); 
                 for(int i=0;i<rbyte;i++) {
                         xmldataByte[count + i] = buffer[i];
                 }
                 count += rbyte;
         }
	         is.close();
	         String requestStr = new String(xmldataByte, "UTF-8");
	         
	         
	         try{
	        	  XMLSerializer xmlSerializer=new XMLSerializer();
                  JSONObject jsonObject =(JSONObject) xmlSerializer.read(requestStr);
                  String event =jsonObject.getString("Event");
                  String msgtype =jsonObject.getString("MsgType");
                  if("CLICK".equals(event) && "event".equals(msgtype)){ //菜单click事件
                          String eventkey =jsonObject.getString("EventKey");
                          if("11".equals(eventkey)){ // hytd_001 这是好友团队按钮的标志忍
                                  jsonObject.put("Content", "欢迎使用好友团队菜单click按钮.");
                          }
                         
                  }
               
                  OutputStream os =response.getOutputStream();
                  os.write(creatRevertText(jsonObject).getBytes("UTF-8"));
	         }catch(Exception e){
	                 e.printStackTrace();
	         }
			}
	     private String creatRevertText(JSONObject jsonObject){
	         StringBuffer revert =new StringBuffer();
	         revert.append("<xml>");
	         revert.append("<ToUserName><![CDATA["+jsonObject.get("ToUserName")+"]]></ToUserName>");
	         revert.append("<FromUserName><![CDATA["+jsonObject.get("FromUserName")+"]]></FromUserName>");
	         revert.append("<CreateTime>"+jsonObject.get("CreateTime")+"</CreateTime>");
	         revert.append("<MsgType><![CDATA[text]]></MsgType>");
	         revert.append("<Content><![CDATA["+jsonObject.get("Content")+"]]></Content>");
	         revert.append("<FuncFlag>0</FuncFlag>");
	         revert.append("</xml>");
	         return revert.toString();
	     }
}
