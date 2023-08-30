package com.team6.sts.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.sts.dao.OrderDAO;
import com.team6.sts.vo.OrderVO;

@Service
public class AdminOrderService {

	@Autowired
	private OrderDAO orderDAO;

	public List<OrderVO> adminOrderList(String key) {
		return orderDAO.listOrderByName(key);
	}

	public void adminOrderSave(String[] result) {
		for (String oseq : result) {
			System.out.println(oseq);
			orderDAO.updateOrderResult(oseq);
		}
	}
}
