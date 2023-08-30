package com.team6.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.sts.dao.QnaDAO;
import com.team6.sts.vo.QnaVO;

@Service
public class QnaService {

	@Autowired
	private QnaDAO qnaDAO;

	public List<QnaVO> listQna(String id) {
		return qnaDAO.listQna(id);
	}

	public QnaVO detailQna(int seq) {
		return qnaDAO.getQna(seq);
	}

	public void insertQna(QnaVO qnaVO, String id) {
		qnaDAO.insertQna(qnaVO, id);
	}

}
