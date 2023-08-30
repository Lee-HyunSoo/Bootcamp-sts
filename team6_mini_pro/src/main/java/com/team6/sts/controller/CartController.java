package com.team6.sts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team6.sts.service.CartService;
import com.team6.sts.vo.CartVO;
import com.team6.sts.vo.MemberVO;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping(value = "/cart_list", method = RequestMethod.GET)
	public String cartList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
	
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser != null) {
			List<CartVO> cart  = cartService.listCart(loginUser.getId());
			model.addAttribute("cartList", cart);
			return "mypage/cartList";
		
		}
		return "redirect:/members/loginForm";
	}
	
	@RequestMapping(value = "/cart_insert", method = RequestMethod.POST)
	public String cartInsert(@RequestParam String pseq,
							 @RequestParam String quantity,
							 HttpServletRequest request,
							 Model model) {
		HttpSession session = request.getSession();

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/members/loginForm";
		} else {
			CartVO cartVO = new CartVO();
			cartVO.setId(loginUser.getId());
			cartVO.setPseq(Integer.parseInt(pseq));
			cartVO.setQuantity(Integer.parseInt(quantity));

			
			cartService.insertCart(cartVO);
			List<CartVO> cart  = cartService.listCart(loginUser.getId());
			model.addAttribute("cartList", cart);
		}
		return "redirect:/cart/cart_list";
	}
	
	@RequestMapping(value = "/cart_delete", method = RequestMethod.POST)
	public String cartDelete(@RequestParam String[] cseq, HttpServletRequest request, Model model)  {
		for (String s : cseq) {
			cartService.deleteCart(Integer.parseInt(s));
		}
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser != null) {
			List<CartVO> cart  = cartService.listCart(loginUser.getId());
			model.addAttribute("cartList", cart);
		}
		return "redirect:/cart/cart_list";
	}
		
}
