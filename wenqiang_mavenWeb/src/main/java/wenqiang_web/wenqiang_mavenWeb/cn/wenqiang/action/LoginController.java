package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.UserMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Fhbutton;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Menu;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Role;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.User;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.FhbuttonService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.MenuService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.RoleService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.UserService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl.UserServiceimpl;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.Const;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.PageData;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.RightsHelper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.utilToll;


/**
 * 总入口
 * @author fh QQ 3 1 3 5 9 6 7 9 0[青苔]
 * 修改日期：2015/11/2
 */
@Controller
public class LoginController extends BaseController {

	
	@Autowired
      UserService  userService;
	@Autowired
	MenuService menuService;
	@Autowired
	RoleService roleService;
	
	@Autowired
	FhbuttonService fhbuttonService;
	
	/**访问登录页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login_toLogin")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", utilToll.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.setViewName("system/index/login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**请求登录，验证用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login_login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object login()throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qq313596790fh", "").replaceAll("QQ978336446fh", "").split(",fh,");
		if(null != KEYDATA && KEYDATA.length == 3){
			Session session = SecurityUtils.getSubject().getSession();
			String sessionCode = (String)session.getAttribute(Const.SESSION_SECURITY_CODE);		//获取session中的验证码
				String USERNAME = KEYDATA[0];	//登录过来的用户名
				String PASSWORD  = KEYDATA[1];	//登录过来的密码
				pd.put("USERNAME", USERNAME);
				
				
					User	user = userService.getUserByNameAndPwd(pd);//根据用户名和密码去读取用户信息
					if(user != null){
						user.setLastLogin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
						userService.updateLastLogin(user);
						session.setAttribute(Const.SESSION_USER, user);			//把用户信息放session中
						//shiro加入身份验证
						Subject subject = SecurityUtils.getSubject(); 
					    UsernamePasswordToken token = new UsernamePasswordToken(USERNAME, PASSWORD); 
					    try { 
					        subject.login(token); 
					    } catch (AuthenticationException e) {
					    	e.printStackTrace();
					    	errInfo = "身份验证失败！";
					    }
					}else{
							errInfo = "usererror"; 				//用户名或密码有误
				}
			}else{
				errInfo = "codeerror";				 	//验证码输入有误
			}
			if(utilToll.isEmpty(errInfo)){
				errInfo = "success";					//验证成功
				
			}

		map.put("result", errInfo);
		return map;
	}
	
//访问系统首页
	/* * @param changeMenu：切换菜单参数
	 * @return
	 */
	@RequestMapping(value="/main/{changeMenu}")
	@SuppressWarnings("unchecked")
	public ModelAndView login_index(@PathVariable("changeMenu") String changeMenu){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Session session = SecurityUtils.getSubject().getSession();
			User user = (User)session.getAttribute(Const.SESSION_USER);				//读取session中的用户信息(单独用户信息)
			if (user != null) {
				User userr = (User)session.getAttribute(Const.SESSION_USERROL);		//读取session中的用户信息(含角色信息)
				if(null == userr){
					user = userService.getUserAndRoleById(user.getUserId());		//通过用户ID读取用户信息和角色信息
					session.setAttribute(Const.SESSION_USERROL, user);				//存入session	
				}else{
					user = userr;
				}
				String USERNAME = user.getUsername();
				Role role = user.getRole();											//获取用户角色
				String roleRights = role!=null ? role.getRights() : "";				//角色权限(菜单权限)
				session.setAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS, roleRights); //将角色权限存入session
				session.setAttribute(Const.SESSION_USERNAME, USERNAME);				//放入用户名到session
				List<Menu> allmenuList = new ArrayList<Menu>();
				if(null == session.getAttribute(USERNAME + Const.SESSION_allmenuList)){	
					
					allmenuList = menuService.listAllMenuQx("0");					//获取所有菜单
					if(utilToll.notEmpty(roleRights)){
						allmenuList = this.readMenu(allmenuList, roleRights);		//根据角色权限获取本权限的菜单列表
					}
					session.setAttribute(USERNAME + Const.SESSION_allmenuList, allmenuList);//菜单权限放入session中
				}else{
					allmenuList = (List<Menu>)session.getAttribute(USERNAME + Const.SESSION_allmenuList);
				}
				//切换菜单处理=====start
				List<Menu> menuList = new ArrayList<Menu>();
				if(null == session.getAttribute(USERNAME + Const.SESSION_menuList) || ("yes".equals(changeMenu))){
					List<Menu> menuList1 = new ArrayList<Menu>();
					List<Menu> menuList2 = new ArrayList<Menu>();
					//拆分菜单
					for(int i=0;i<allmenuList.size();i++){
						Menu menu = allmenuList.get(i);
						if("1".equals(menu.getMenuType())){
							menuList1.add(menu);
						}else{
							menuList2.add(menu);
						}
					}
					session.removeAttribute(USERNAME + Const.SESSION_menuList);
					if("2".equals(session.getAttribute("changeMenu"))){
						session.setAttribute(USERNAME + Const.SESSION_menuList, menuList1);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "1");
						menuList = menuList1;
					}else{
						session.setAttribute(USERNAME + Const.SESSION_menuList, menuList2);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "2");
						menuList = menuList2;
					}
				}else{
					menuList = (List<Menu>)session.getAttribute(USERNAME + Const.SESSION_menuList);
				}
				//切换菜单处理=====end
				if(null == session.getAttribute(USERNAME + Const.SESSION_QX)){
					session.setAttribute(USERNAME + Const.SESSION_QX, this.getUQX(USERNAME));	//按钮权限放到session中
				}
				this.getRemortIP(USERNAME);	//更新登录IP
				mv.setViewName("system/index/main");
				mv.addObject("user", user);
				mv.addObject("menuList", menuList);
			}else {
				mv.setViewName("system/index/login");//session失效后跳转登录页面
			}
		} catch(Exception e){
			e.printStackTrace();
			mv.setViewName("system/index/login");
			
		}
		pd.put("SYSNAME", utilToll.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		return mv;
	}
	
//根据角色权限获取本权限的菜单列表(递归处理)
	/** * @param menuList：传入的总菜单
	 * @param roleRights：加密的权限字符串
	 * @return
	 */
	public List<Menu> readMenu(List<Menu> menuList,String roleRights){
		for(int i=0;i<menuList.size();i++){
			menuList.get(i).setHasMenu(RightsHelper.testRights(roleRights, menuList.get(i).getMenuId()));
			if(menuList.get(i).isHasMenu()){		//判断是否有此菜单权限
				this.readMenu(menuList.get(i).getSubMenu(), roleRights);//是：继续排查其子菜单
			}
		}
		return menuList;
	}
	
	/**
	 * 进入tab标签
	 * @return
	 */
	@RequestMapping(value="/tab")
	public String tab(){
		return "system/index/tab";
	}
	
	/**
	 * 进入首页后的默认页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/login_default")
	public ModelAndView defaultPage() throws Exception{
		ModelAndView mv = this.getModelAndView();
		
	
		mv.setViewName("system/index/default");
		return mv;
	}
	
	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		String USERNAME = (String) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USERNAME);	//当前登录的用户名
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		Session session =  SecurityUtils.getSubject().getSession();	//以下清除session缓存
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(USERNAME + Const.SESSION_allmenuList);
		session.removeAttribute(USERNAME + Const.SESSION_menuList);
		session.removeAttribute(USERNAME + Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute(Const.SESSION_USERROL);
		session.removeAttribute("changeMenu");
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		pd = this.getPageData();
		pd.put("msg", pd.getString("msg"));
		pd.put("SYSNAME", utilToll.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.setViewName("system/index/login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**获取用户权限
	 * @param session
	 * @return
	 */
	public Map<String, String> getUQX(String USERNAME){
		PageData pd = new PageData();
		Map<String, String> map = new HashMap<String, String>();
		try {
			pd.put(Const.SESSION_USERNAME, USERNAME);
			pd.put("ROLE_ID", userService.getUserByNameAndPwd(pd).getRoleId().toString());//获取角色ID
			Role role = roleService.selectByPrimaryKey(pd.getString("ROLE_ID"));										//获取角色信息														
			map.put("adds", pd.getString("ADD_QX"));	//增
			map.put("dels", pd.getString("DEL_QX"));	//删
			map.put("edits", pd.getString("EDIT_QX"));	//改
			map.put("chas", pd.getString("CHA_QX"));	//查
			List<Fhbutton> buttonQXnamelist = new ArrayList<Fhbutton>();
			if("admin".equals(USERNAME)){
				
				buttonQXnamelist =fhbuttonService.selectAll("1=1");	
				for(int i=0;i<buttonQXnamelist.size();i++){
					map.put(buttonQXnamelist.get(i).getQxName(),"1");		//按钮权限
				}//admin用户拥有所有按钮权限
			}else{
				String roid=pd.getString("ROLE_ID");
				List<String> str = fhbuttonService.listAllBrAndQxname(roid);
				for(int i=0;i<str.size();i++){
					map.put(str.get(i),"1");		//按钮权限
				}//此角色拥有的按钮权限标识列表
			}
			
		} catch (Exception e) {
		
		}	
		return map;
	}
	
	
	/** 更新登录用户的IP
	 * @param USERNAME
	 * @throws Exception
	 */
	public void getRemortIP(String USERNAME) throws Exception {  
		PageData pd = new PageData();
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip = request.getRemoteAddr();  
	    }else{
	    	ip = request.getHeader("x-forwarded-for");  
	    }
		
		User user=new User();
		user.setIp(ip);
		user.setUsername(USERNAME);
		userService.updateLastLogin(user);;
	}  
	
}
