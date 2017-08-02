package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service;

import java.util.List;

import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.WebParam;
import javax.jws.WebService;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Fhbutton;

@WebService
@SOAPBinding(style = Style.RPC)
public interface LWebService {
  
    List<String> listAllBrAndQxname(@WebParam(name = "text",targetNamespace="http://impl.service.wenqiang.cn.wenqiang_mavenWeb.wenqiang_web/")String userName);

}