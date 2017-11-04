package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.PictureService;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.JsonUtils;



@Controller
@RequestMapping("/file")
public class KmFileContronller extends  BaseController{
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/uploadFile")
	@ResponseBody
	public Object uploadFile(@RequestBody String file,HttpServletRequest request,HttpServletResponse response){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("Filedata");
			Map result = pictureService.uploadPicture(multipartFile);
			//为了保证功能的兼容性，需要把Result转换成json格式的字符串。
			String json = JsonUtils.objectToJson(result);
			return json;
		}
	
	/**
	 * pic 页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/pic")
	public ModelAndView defaultPage() throws Exception{
		ModelAndView mv = this.getModelAndView();
		
	
		mv.setViewName("system/pic/pic");
		return mv;
	}
	
	/**
	 * pic 页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/webSoketPage")
	public ModelAndView webSoketPage() throws Exception{
		ModelAndView mv = this.getModelAndView();
		
	
		mv.setViewName("system/webSoket/webSoket");
		return mv;
	}
}
