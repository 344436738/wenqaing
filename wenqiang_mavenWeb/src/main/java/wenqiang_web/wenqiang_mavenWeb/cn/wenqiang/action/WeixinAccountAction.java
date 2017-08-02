package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;














import com.google.gson.Gson;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.WeixinAccount;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl.WeixinAccountServiceimpl;



@Controller
@RequestMapping("/weixinAccount") 
public class WeixinAccountAction extends BaseController {
	@Autowired
	WeixinAccountServiceimpl weixinAccountService;
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public String  list(HttpServletRequest request) throws Exception{
		 
	        return "weixinAccount/weixinAccountList";
	}
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/datalist") 
	@ResponseBody
	public String  list(HttpServletRequest request,WeixinAccount  mode) throws Exception{
		
			Gson gson=new Gson();
			List<WeixinAccount> dataList = weixinAccountService.queryByList(mode);
			//设置页面数据
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("total",mode.getPager().getRowCount());
			jsonMap.put("rows", dataList);
	        return gson.toJson(jsonMap);
	}
	
	
	 private Map transToMAP(Map parameterMap){
	      // 返回值Map
	      Map returnMap = new HashMap();
	      Iterator entries = parameterMap.entrySet().iterator();
	      Map.Entry entry;
	      String name = "";
	      String value = "";
	      while (entries.hasNext()) {
	          entry = (Map.Entry) entries.next();
	          name = (String) entry.getKey();
	          Object valueObj = entry.getValue();
	          if(null == valueObj){
	              value = "";
	          }else if(valueObj instanceof String[]){
	              String[] values = (String[])valueObj;
	              for(int i=0;i<values.length;i++){
	                  value = values[i] + ",";
	              }
	              value = value.substring(0, value.length()-1);
	          }else{
	              value = valueObj.toString();
	          }
	          returnMap.put(name, value);
	      }
	      return  returnMap;
	  }
	 
	 /**
		 * 添加或修改数据
		 * @param url
		 * @param classifyId
		 * @return
		 * @throws Exception 
		 */
		@RequestMapping("/save")
		public void save(WeixinAccount bean,HttpServletResponse response,HttpServletRequest request) throws Exception{
			Map<String,Object>  context = new HashMap<String,Object>();
			  bean.setAddtoekntime(new Date());		
				weixinAccountService.saveOrUpdate(bean);
			
		}
		@RequestMapping("/getId")
		public String getId(Integer id,HttpServletResponse response,Model model) throws Exception{
			Map<String,Object>  context = new HashMap();
			WeixinAccount bean  = (WeixinAccount) weixinAccountService.queryById(id);
			if(bean  == null){
				return "没有找到对应的记录!";
			}
			model.addAttribute(bean);
	        return "weixinAccount/weixinAccountList";
		}
		
		
		
		@RequestMapping("/delete")
		public void delete(Integer[] ids,HttpServletResponse response) throws Exception{
		
		
		
		   weixinAccountService.delete(ids);
		
		}
	
	
}
