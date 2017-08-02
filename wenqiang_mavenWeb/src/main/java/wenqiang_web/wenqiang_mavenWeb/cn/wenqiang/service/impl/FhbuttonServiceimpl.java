package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.dao.FhbuttonMapper;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.Fhbutton;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.FhbuttonService;
@Service("fhbuttonService")
@Transactional
public class FhbuttonServiceimpl implements  FhbuttonService {
  @Autowired
  FhbuttonMapper fhbuttonmapper;
	
	@Override
	public int deleteByPrimaryKey(String fhbuttonId) {
		// TODO Auto-generated method stub
		return fhbuttonmapper.deleteByPrimaryKey(fhbuttonId);
	}

	@Override
	public int insert(Fhbutton record) {
		// TODO Auto-generated method stub
		return fhbuttonmapper.insert(record);
	}

	@Override
	public int insertSelective(Fhbutton record) {
		// TODO Auto-generated method stub
		return fhbuttonmapper.insertSelective(record);
	}

	@Override
	public Fhbutton selectByPrimaryKey(String fhbuttonId) {
		// TODO Auto-generated method stub
		return fhbuttonmapper.selectByPrimaryKey(fhbuttonId);
	}

	@Override
	public int updateByPrimaryKeySelective(Fhbutton record) {
		// TODO Auto-generated method stub
		return fhbuttonmapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Fhbutton record) {
		// TODO Auto-generated method stub
		return fhbuttonmapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Fhbutton> selectAll(String str) {
		// TODO Auto-generated method stub
		return fhbuttonmapper.selectAll(str);
	}

	@Override
	public List<String> listAllBrAndQxname(String str) {
		// TODO Auto-generated method stub
		return fhbuttonmapper.listAllBrAndQxname(str);
	}
   
}