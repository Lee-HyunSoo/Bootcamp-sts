package com.team6.sts.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team6.sts.vo.CartVO;

@Repository
public class CartDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CartVO> listCart(String id) {
		List<CartVO> cartList = sqlSession.selectList("mapper.cart.listCart", id);
		return cartList;
	}

	public void insertCart(CartVO cartVO) {
		sqlSession.insert("mapper.cart.insertCart", cartVO);
	}
	
	public void deleteCart(int cseq) {
		sqlSession.delete("mapper.cart.deleteCart", cseq);
	}

}
