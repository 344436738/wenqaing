package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.FhbuttonMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.LWebService;
@WebService(endpointInterface="wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.LWebService",serviceName="LWebService")  
public class LWebServiceimpl implements  LWebService {
  @Autowired
  FhbuttonMapper fhbuttonmapper;
	

	@Override
	public List<String> listAllBrAndQxname(@WebParam(name = "text")String userName) {
		System.out.print(userName);
		// TODO Auto-generated method stub
		return fhbuttonmapper.listAllBrAndQxname(userName);
	}
   
}