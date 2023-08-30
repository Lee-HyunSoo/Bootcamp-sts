package com.team6.sts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team6.sts.service.MemberService;
import com.team6.sts.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String id, 
						@RequestParam String pwd, 
						HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if (memberService.login(id, pwd) != null) {
			session.removeAttribute("id");
			session.setAttribute("loginUser", memberService.login(id, pwd));
			return "redirect:/products";
		}
		return "redirect:/members/login_fail";
	}
	
	@RequestMapping(value = "/login_fail", method = RequestMethod.GET)
	public String login_fail() {
		return "member/login_fail";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
		
		return "redirect:/products";
	}
	/**
	 * 새로고침 시 다시 회원가입 Form이 보이기 위해 GET, 버튼 누를 시 접근하기 위한 POST
	 * @return
	 */
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		return "member/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(request.getParameter("id"));
		memberVO.setPwd(request.getParameter("pwd"));
		memberVO.setName(request.getParameter("name"));
		memberVO.setEmail(request.getParameter("email"));
		memberVO.setZipNum(request.getParameter("zipNum"));
		memberVO.setAddress(request.getParameter("addr1") + request.getParameter("addr2"));
		memberVO.setPhone(request.getParameter("phone"));
		session.setAttribute("id", memberVO.getId());

		memberService.join(memberVO);
		
		return "redirect:/products";
	}
	
	@RequestMapping(value = "/contract", method = RequestMethod.GET)
	public String contract() {
		return "member/contract";
	}

	/**
	 * 처음 중복확인 버튼을 눌렀을 때 팝업창을 띄워주기 위한 GET
	 * 입력창 입력 시 추가 검색을 위한 POST
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/idCheckForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String idCheckForm(@RequestParam String id, Model model) {
		int message = memberService.idCheckForm(id);
		model.addAttribute("message", message);
		model.addAttribute("id", id);
		
		return "member/idcheck";
	}
	
	@RequestMapping(value = "/findZipNum", method = RequestMethod.POST)
	public String findZipNum(@RequestParam String dong, Model model) {	
		model.addAttribute("addressList", memberService.findZipNum(dong));
		return "member/findZipNum";
	}
	
	@RequestMapping(value = "/findIdForm", method = RequestMethod.POST)
	public String findIdForm(@RequestParam String name, @RequestParam String email, Model model) {
		String memberId = "";
		if (name != null && !name.trim().equals("")
				&& email != null && !email.trim().equals("")) {
			
			memberId = memberService.findId(name, email);
			model.addAttribute("memberId", memberId);
		}
		return "member/findId";  
	}
	
	@RequestMapping(value = "/findPwdForm", method = RequestMethod.POST)
	public String findPwdForm(@RequestParam String id, @RequestParam String name, @RequestParam String email, Model model) {
		String memberPwd = "";
		if (id != null && !id.trim().equals("")
				&& name != null && !name.trim().equals("")
				&& email != null && !email.trim().equals("")) {
			
			memberPwd = memberService.findPwd(id, name, email);
			model.addAttribute("id", id);
			model.addAttribute("email", email);
			model.addAttribute("memberPwd", memberPwd);
		}
		return "member/findPassword";
	}

}
