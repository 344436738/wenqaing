package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.security;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.UserService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.PageData;


/** 
* @ClassName: SysUserFilter 
* @Description: 把用户的信息绑定到 user中去 
* @author com_emep_mpc
* @date 2016年8月29日 下午2:50:46 
*  
*/
public class SysUserFilter extends PathMatchingFilter {
	@Resource
    UserService  userFacade;

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		   PageData pd = new PageData();
		     pd.put("USERNAME", username);
		request.setAttribute(Constants.CURRENT_USER,
				userFacade.getUserByNameAndPwd(pd));
		return true;
	}
}
