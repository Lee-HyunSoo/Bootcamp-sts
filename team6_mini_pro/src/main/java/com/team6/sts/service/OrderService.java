package com.team6.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.sts.dao.OrderDAO;
import com.team6.sts.vo.CartVO;
import com.team6.sts.vo.OrderVO;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;

	public List<Integer> selectSeqOrderIng(String id) {
		return orderDAO.selectSeqOrderIng(id);
	}

	public List<Integer> selectSeqOrderIng2(String id) {
		return orderDAO.selectSeqOrderIng2(id);
	}

	public List<OrderVO> listOrderById(String id, String result, int oseq) {
		return orderDAO.listOrderById(id, result, oseq);
	}

	public int insertOrder(List<CartVO> cartList, String id) {
		return orderDAO.insertOrder(cartList, id);
	}

	public int maxCSEQ() {
		return orderDAO.maxCSEQ();
	}
}
