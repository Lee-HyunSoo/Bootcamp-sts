package com.team6.sts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.team6.sts.vo.CartVO;
import com.team6.sts.vo.OrderVO;

@Repository
public class OrderDAO {

	@Autowired
	private SqlSession sqlSession;

	// 현재 진행 중인 주문 내역만 조회
	public List<Integer> selectSeqOrderIng(String id) {
		List<Integer> oseqList = sqlSession.selectList("mapper.order.selectSeqOrderIng", id);
		return oseqList;
	}

	public List<Integer> selectSeqOrderIng2(String id) {
		List<Integer> oseqList = sqlSession.selectList("mapper.order.selectSeqOrderIng2", id);
		return oseqList;
	}

	// 사용자가 주문 내역 검색
	public List<OrderVO> listOrderById(String id, String result, int oseq) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("result", result);
		params.put("oseq", oseq);
		List<OrderVO> orderList = sqlSession.selectList("mapper.order.listOrderById", params);
		return orderList;
	}

	@Transactional
	public int insertOrder(List<CartVO> cartList, String id) {
		int maxOseq = sqlSession.selectOne("mapper.order.selectMaxOseq");
		sqlSession.insert("mapper.order.insertOrder", id);

		for (CartVO cartVO : cartList) {
			insertOrderDetail(cartVO, maxOseq);
		}

		return maxOseq;
	}

	@Transactional
	public void insertOrderDetail(CartVO cartVO, int maxOseq) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("oseq", maxOseq);
		params.put("pseq", cartVO.getPseq());
		params.put("quantity", cartVO.getQuantity());
		params.put("cseq", cartVO.getCseq());
		
		sqlSession.insert("mapper.order.insertOrderDetail", params);
		sqlSession.update("mapper.order.updateCartResult", params);
	}

	// 어드민쪽에서 쓰는 부분
	public void updateOrderResult(String odseq) {	
		sqlSession.update("mapper.order.updateOrderResult", odseq);
	}

	public int maxCSEQ() {
		return sqlSession.selectOne("mapper.order.maxCSEQ");
	}

	public List<OrderVO> listOrderByName(String mname) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mname", mname);
		List<OrderVO> orderList = sqlSession.selectList("mapper.order.listOrderByName", params); 
		return orderList;
	}

}
