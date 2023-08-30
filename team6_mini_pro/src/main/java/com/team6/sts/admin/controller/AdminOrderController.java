package com.team6.sts.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team6.sts.admin.service.AdminOrderService;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {
	
	@Autowired
	private AdminOrderService adminOrderService;

	@RequestMapping(value = "/adminOrderList", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminOrderList(@RequestParam(defaultValue = "") String key, Model model) {
		model.addAttribute("orderList", adminOrderService.adminOrderList(key));
		return "admin/order/orderList";
	}
	
	@RequestMapping(value = "/adminOrderSave", method = RequestMethod.POST)
	public String adminOrderSave(@RequestParam String[] result) {
		adminOrderService.adminOrderSave(result);	
		return "redirect:/admin/orders/adminOrderList";
	}

}
