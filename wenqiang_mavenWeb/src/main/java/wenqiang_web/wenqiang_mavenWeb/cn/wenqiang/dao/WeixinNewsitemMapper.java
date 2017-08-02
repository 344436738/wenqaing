package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.WeixinNewsitem;

public interface WeixinNewsitemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeixinNewsitem record);

    int insertSelective(WeixinNewsitem record);

    WeixinNewsitem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeixinNewsitem record);

    int updateByPrimaryKeyWithBLOBs(WeixinNewsitem record);

    int updateByPrimaryKey(WeixinNewsitem record);
}