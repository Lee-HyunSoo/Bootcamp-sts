package com.team6.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.sts.dao.CartDAO;
import com.team6.sts.vo.CartVO;

@Service
public class CartService {

	@Autowired
	private CartDAO cartDAO;

	public List<CartVO> listCart(String id) {
		return cartDAO.listCart(id);
	}

	public void deleteCart(int cseq) {
		cartDAO.deleteCart(cseq);
	}

	public void insertCart(CartVO vo) {
		cartDAO.insertCart(vo);
	}
}
