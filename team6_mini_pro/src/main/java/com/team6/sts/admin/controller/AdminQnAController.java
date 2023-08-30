package com.team6.sts.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team6.sts.admin.service.AdminQnAService;

@Controller
@RequestMapping("/admin/qnas")
public class AdminQnAController {
	
	@Autowired
	private AdminQnAService adminQnAService;
	
	@RequestMapping(value = "adminQnAList", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminQnAList(Model model) {
		model.addAttribute("qnaList", adminQnAService.adminQnAList());
		return "admin/qna/qnaList";
	}
	
	@RequestMapping(value = "adminQnADetail", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminQnADetail(@RequestParam String qseq, Model model) {
		String _qseq = qseq.trim();
		model.addAttribute("qnaVO", adminQnAService.adminQnADetail(_qseq));
		return "admin/qna/qnaDetail";
	}
	
	@RequestMapping(value = "adminQnARepSave", method = RequestMethod.POST)
	public String adminQnARepSave(@RequestParam String qseq,
								@RequestParam String reply,
								Model model) {
		String _qseq = qseq.trim();
		String _reply = reply.trim();

		adminQnAService.adminQnARepSave(_qseq, _reply);
		return "redirect:/admin/products/adminProductList";
	}

}
