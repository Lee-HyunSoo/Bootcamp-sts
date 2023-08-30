package com.team6.sts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team6.sts.service.ProductService;
import com.team6.sts.vo.ProductVO;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		List<ProductVO> newProductList = productService.listNewProduct();
		List<ProductVO> bestProductList = productService.listBestProduct();
		model.addAttribute("newProductList", newProductList);
		model.addAttribute("bestProductList", bestProductList);

		return "index";
	}
	
	@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
	public String productDetail(@RequestParam String pseq, Model model) {
		String _pseq = pseq.trim();
		ProductVO productVO = productService.getProduct(_pseq);

		model.addAttribute("productVO", productVO);

		return "product/productDetail";
	}
	
	@RequestMapping(value = "/catagory", method = RequestMethod.GET)
	public String catagory(@RequestParam String kind, Model model) {
		String _kind = kind.trim();

		List<ProductVO> productKindList = productService.listKindProduct(_kind);

		model.addAttribute("productKindList", productKindList);
		return "product/productKind";
	}
	
	

}
