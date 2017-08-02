package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.BaseMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.WeixinAccountMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.WeixinAccount;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.BaseService;
@Service
@Transactional
public class WeixinAccountServiceimpl<T>  extends BaseService<T>{
	private final static Logger log= Logger.getLogger(WeixinAccountServiceimpl.class);
	@Autowired
	WeixinAccountMapper    weixinAccountMapper;

	public WeixinAccountMapper<T> getWeixinAccountMapper() {
		return weixinAccountMapper;
	}
	
	

public  void saveOrUpdate(WeixinAccount bean){
	weixinAccountMapper.update(bean);
	
}

public WeixinAccount findByToUsername(String toUserName) {
	//WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
	//model.setAccountid(StringUtil.ObjectToInteger(weixinaccount));
	return weixinAccountMapper.findUniqueByProperty(toUserName);

}



@Override
public BaseMapper<T> getMapper() {
	// TODO Auto-generated method stub
	return null;
}
	

	
}
