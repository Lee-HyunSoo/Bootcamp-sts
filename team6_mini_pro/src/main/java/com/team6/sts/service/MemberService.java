package com.team6.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.sts.dao.AddressDAO;
import com.team6.sts.dao.MemberDAO;
import com.team6.sts.vo.AddressVO;
import com.team6.sts.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private AddressDAO addressDAO;
	
	public MemberService() {
	}

	public MemberVO login(String id, String pwd) {	
		MemberVO memberVO = memberDAO.getMember(id);
		if (memberDAO.getMember(id) != null) {
			if (memberVO.getPwd().equals(pwd))
				return memberVO;
		}
		return null;
	}

	public void join(MemberVO memberVO) {
		memberDAO.insertMember(memberVO);	
	}
	
	public int idCheckForm(String id) {
		return memberDAO.confirmID(id);
	}

	public List<AddressVO> findZipNum(String dong) {
		if (dong != null && dong.trim().equals("") == false) {
			return addressDAO.selectAddressByDong(dong.trim());
		}
		return null;		
	}
	
	public String findId(String name, String email) {
        return memberDAO.findId(name, email);
    }
    
    public String findPwd(String id, String name, String email) {
        return memberDAO.findPwd(id, name, email);
    }
	
}
