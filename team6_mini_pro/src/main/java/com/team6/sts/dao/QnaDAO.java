package com.team6.sts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team6.sts.vo.QnaVO;

@Repository
public class QnaDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//qna 목록
    public List<QnaVO> listQna(String id) {
        List<QnaVO> qnaList = sqlSession.selectList("mapper.qna.listQna", id);
        return qnaList;
    }

    //qna 상세
    public QnaVO getQna(int seq) {
        QnaVO qnaVO = sqlSession.selectOne("mapper.qna.getQna", seq);
        return qnaVO;
    }

    //qna 작성
    public void insertQna(QnaVO qnaVO, String session_id) {
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("subject", qnaVO.getSubject());
    	params.put("content", qnaVO.getContent());
    	params.put("id", session_id);
    	sqlSession.insert("mapper.qna.insertQna", params);
    }
    
	public List<QnaVO> listAllQna() {
		List<QnaVO> qnaList = sqlSession.selectList("mapper.qna.listAllQna");
		return qnaList;
	}

	public void updateQna(QnaVO qnaVO) {
		sqlSession.update("mapper.qna.updateQna", qnaVO);
	}
	
}
