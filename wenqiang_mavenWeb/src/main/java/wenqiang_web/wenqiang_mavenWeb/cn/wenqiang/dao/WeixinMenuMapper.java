package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.WeixinMenu;

public interface WeixinMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeixinMenu record);

    int insertSelective(WeixinMenu record);

    WeixinMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeixinMenu record);

    int updateByPrimaryKey(WeixinMenu record);
}