package com.team6.sts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team6.sts.vo.AddressVO;

@Repository
public class AddressDAO {

	@Autowired
	SqlSession sqlSession;
	
	public List<AddressVO> selectAddressByDong(String dong) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("dong", dong);
		
		List<AddressVO> list = sqlSession.selectList("mapper.address.selectAddressByDong", params);
		System.out.println(list);
		return list;
	}

}
