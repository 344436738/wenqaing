package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service;

import java.util.List;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Fhbutton;

public interface FhbuttonService {
    int deleteByPrimaryKey(String fhbuttonId);

    int insert(Fhbutton record);

    int insertSelective(Fhbutton record);

    Fhbutton selectByPrimaryKey(String fhbuttonId);

    int updateByPrimaryKeySelective(Fhbutton record);

    int updateByPrimaryKey(Fhbutton record);
    
    
    List<Fhbutton> selectAll(String str);
    
    List<String> listAllBrAndQxname(String str);
}