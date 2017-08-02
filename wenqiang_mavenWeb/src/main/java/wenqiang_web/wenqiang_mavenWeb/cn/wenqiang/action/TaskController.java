package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.WeixinAccount;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.task;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.TaskService;


@Controller
@RequestMapping("/task") 
public class TaskController extends BaseController {
	@Autowired
	TaskService  taskService;
	
   @RequestMapping("/save")
	public void save(task bean,HttpServletResponse response,HttpServletRequest request) throws Exception{
    
    	taskService.insert(bean);
		
	}  
    
    
   /**
	 * 
	 * @param url  定时任务列表页
	
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView();
		List<task>  varList=	taskService.listAllMenuQxByPage();
	
	       
	    	mv.setViewName("task/taskList");
			mv.addObject("varList",varList);
			return mv;
	}


 
	
	
   

 
}  
