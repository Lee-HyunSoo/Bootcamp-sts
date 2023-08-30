package com.team6.sts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team6.sts.service.CartService;
import com.team6.sts.service.OrderService;
import com.team6.sts.vo.CartVO;
import com.team6.sts.vo.MemberVO;
import com.team6.sts.vo.OrderVO;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;

	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(HttpServletRequest request, Model model) throws ServletException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "redirect:/members/loginForm";
		} else {
			List<Integer> oseqList = orderService.selectSeqOrderIng(loginUser.getId());
			List<OrderVO> orderList = new ArrayList<OrderVO>();

			for (int oseq : oseqList) {
				List<OrderVO> orderListIng = orderService.listOrderById(loginUser.getId(), "1", oseq);

				OrderVO orderVO = orderListIng.get(0);
				orderVO.setPname(orderVO.getPname() + " 외 " + (orderListIng.size() - 1) + "건");

				int totalPrice = 0;
				for (OrderVO ovo : orderListIng) {
					totalPrice += ovo.getPrice2() * ovo.getQuantity();
				}
				orderVO.setPrice2(totalPrice);
				orderList.add(orderVO);
			}
			model.addAttribute("title", "진행 중인 주문 내역");
			model.addAttribute("orderList", orderList);
		}
		return "mypage/mypage";
	}

	@RequestMapping(value = "/orderAll", method = RequestMethod.GET)
	private String OrderAll(@RequestParam String tpage, HttpServletRequest request, Model model) throws ServletException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/members/loginForm";
		} else {

			List<Integer> oseqList = orderService.selectSeqOrderIng(loginUser.getId());
			List<Integer> oseqList2 = orderService.selectSeqOrderIng2(loginUser.getId());

			List<OrderVO> orderList = new ArrayList<OrderVO>();
			List<OrderVO> orderList2 = new ArrayList<OrderVO>();

			for (int oseq : oseqList) {
				List<OrderVO> orderListIng = orderService.listOrderById(loginUser.getId(), "%", oseq);

				OrderVO orderVO = orderListIng.get(0);
				orderVO.setPname(orderVO.getPname() + " 외 " + (orderListIng.size() - 1) + "건");

				int totalPrice = 0;
				for (OrderVO ovo : orderListIng) {
					totalPrice += ovo.getPrice2() * ovo.getQuantity();
				}
				orderVO.setPrice2(totalPrice);

				orderList.add(orderVO);
			}

			for (int oseq : oseqList2) {
				List<OrderVO> orderListIng = orderService.listOrderById(loginUser.getId(), "%", oseq);

				OrderVO orderVO = orderListIng.get(0);
				orderVO.setPname(orderVO.getPname() + " 외 " + (orderListIng.size() - 1) + "건");

				int totalPrice = 0;
				for (OrderVO ovo : orderListIng) {
					totalPrice += ovo.getPrice2() * ovo.getQuantity();
				}
				orderVO.setPrice2(totalPrice);
				orderVO.setGubun(1);
				orderList2.add(orderVO);
			}

			List<OrderVO> orderall = new ArrayList<OrderVO>();
			orderall.addAll(orderList);
			orderall.addAll(orderList2);

			int itemsPerPage = 10; // 페이지당 아이템 수
			int totalItems = orderall.size(); // 전체 주문 아이템 수

			int _tpage = Integer.parseInt(tpage);

			if (_tpage == 0) {
				_tpage = 1;
			}

			int startIdx = (_tpage - 1) * itemsPerPage;
			int endIdx = Math.min(startIdx + itemsPerPage, totalItems);

			List<OrderVO> paginatedOrderList = orderall.subList(startIdx, endIdx);

			model.addAttribute("title", "총 주문 내역");
			model.addAttribute("currentPage", _tpage);
			model.addAttribute("itemsPerPage", itemsPerPage);
			model.addAttribute("totalItems", totalItems);
			model.addAttribute("totalPages", (int) Math.ceil((double) totalItems / itemsPerPage));
			model.addAttribute("orderList", paginatedOrderList);
		}
		return "redirect:/order/mypage";
	}

	@RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
	public String orderDetail(@RequestParam String oseq, HttpServletRequest request, Model model) throws ServletException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/members/loginForm";
		} else {
			int _oseq = Integer.parseInt(oseq);
			List<OrderVO> orderList = orderService.listOrderById(loginUser.getId(), "%", _oseq);

			int totalPrice = 0;
			for (OrderVO ovo : orderList) {
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			model.addAttribute("orderDetail", orderList.get(0));
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalPrice);

		}
		return "mypage/orderDetail";
	}

	@RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
	public String insertOrder(@RequestParam String pseq, @RequestParam String quantity,
							  HttpServletRequest request, RedirectAttributes redirectAttributes) throws ServletException {
		HttpSession session = request.getSession();

		int maxOseq = 0;
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/members/loginForm";
		} else {
			if(request.getParameterValues("cseq") != null) {
				String[] values = request.getParameterValues("cseq");
				List<CartVO> cartList = cartService.listCart(loginUser.getId());
				List<CartVO> orderin = new ArrayList<CartVO>();
							
				for (CartVO cart : cartList) {
					for (String value : values) {
						if (cart.getCseq() == Integer.parseInt(value)) {
							orderin.add(cart);
						}
					}
				}
				maxOseq = orderService.insertOrder(orderin, loginUser.getId());
			} else {
				CartVO cartVO = new CartVO();
				cartVO.setId(loginUser.getId());
				cartVO.setPseq(Integer.parseInt(pseq));
				cartVO.setQuantity(Integer.parseInt(quantity));
				cartService.insertCart(cartVO);
				
				List<CartVO> cartList = cartService.listCart(loginUser.getId());
				List<CartVO> orderin = new ArrayList<CartVO>();
				
				int _cseq = orderService.maxCSEQ();
				for (CartVO cart : cartList) {
					if (cart.getCseq() == _cseq) {
						orderin.add(cart);
					}
				}
				maxOseq = orderService.insertOrder(orderin, loginUser.getId());
			}
		}

		redirectAttributes.addAttribute("oseq", maxOseq);
		return "redirect:/order/orderList";
	}

	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public String orderList(@RequestParam int oseq, HttpServletRequest request, Model model) throws ServletException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "redirect:/members/loginForm";
		} else {
			List<OrderVO> orderList = orderService.listOrderById(loginUser.getId(), "1", oseq);

			int totalPrice = 0;
			for (OrderVO orderVO : orderList) {
				totalPrice += orderVO.getPrice2() * orderVO.getQuantity();
			}

			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalPrice);
		}
		return "mypage/orderList";
	}

}
