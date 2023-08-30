package com.team6.sts.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkerDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public int workerCheck(String userid, String userpw) {	
		String dbpwd = sqlSession.selectOne("mapper.worker.workerCheck", userid);
		
		if (dbpwd == null)
			return -1; // 아이디 틀림
		else if (dbpwd.equals(userpw))
			return 1; // 로그인 성공
		else
			return 0; // 비번 틀림
	}

}
