package com.team6.sts.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team6.sts.admin.service.AdminMemberService;

@Controller
@RequestMapping("/admin/members")
public class AdminMemberController {

	@Autowired
	private AdminMemberService adminMemberService;

	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	public String adminLogin() {
		return "admin/main";
	}
	
	@RequestMapping(value = "/adminLoginForm", method = RequestMethod.POST)
	public String adminLoginForm(@RequestParam String workerId,
								 @RequestParam String workerPwd,
								 HttpServletRequest request,
								 Model model) {	
		int result = adminMemberService.adminLoginForm(workerId, workerPwd);
		
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("workerId", workerId);
			return "redirect:/admin/products/adminProductList";
		} 
		else if (result == 0) 
			model.addAttribute("message", "비밀번호를 확인하세요.");
		else if (result == -1)
			model.addAttribute("message", "아이디를 확인하세요.");

		return "redirect:/admin/members/adminLogin";
	}
	
	@RequestMapping(value = "/adminLogout", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			redirectAttributes.addAttribute("message", "");
		}
		return "redirect:/admin/members/adminLogin";
	}
	
	@RequestMapping(value = "/adminMemberList", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminMemberList(@RequestParam(defaultValue = "") String key, Model model) {
		model.addAttribute("memberList", adminMemberService.adminMemberList(key));
		return "admin/member/memberList";
	}

}
