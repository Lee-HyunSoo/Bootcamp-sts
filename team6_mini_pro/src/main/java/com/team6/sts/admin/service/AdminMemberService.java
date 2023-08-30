package com.team6.sts.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.sts.dao.MemberDAO;
import com.team6.sts.dao.WorkerDAO;
import com.team6.sts.vo.MemberVO;

@Service
public class AdminMemberService {

	@Autowired
	private WorkerDAO workerDAO;
	@Autowired
	private MemberDAO memberDAO;
	

	public int adminLoginForm(String workerId, String workerPwd) {
		return workerDAO.workerCheck(workerId, workerPwd);
	}

	public List<MemberVO> adminMemberList(String key) {
		return memberDAO.listMember(key);
	}

	
}
