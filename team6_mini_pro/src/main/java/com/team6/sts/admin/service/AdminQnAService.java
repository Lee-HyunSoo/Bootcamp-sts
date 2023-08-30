package com.team6.sts.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.sts.dao.QnaDAO;
import com.team6.sts.vo.QnaVO;

@Service
public class AdminQnAService {

	@Autowired
	private QnaDAO qnaDAO;
	
	public List<QnaVO> adminQnAList() {
		return qnaDAO.listAllQna();
	}

	public QnaVO adminQnADetail(String qseq) {
		return qnaDAO.getQna(Integer.parseInt(qseq));
	}

	public void adminQnARepSave(String qseq, String reply) {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setQseq(Integer.parseInt(qseq));
		qnaVO.setReply(reply);

		qnaDAO.updateQna(qnaVO);
	}

}
