package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import java.util.List;
import java.util.Map;



import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.WeixinAccount;

public interface WeixinAccountMapper<T> extends BaseMapper<T>{
	List<WeixinAccount> findByUsername(String username);

	WeixinAccount queryByUserid(Integer userid);
	void update(WeixinAccount bean);

	WeixinAccount findUniqueByProperty(String toUserName);
    
}