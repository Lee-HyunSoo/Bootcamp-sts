package com.team6.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.sts.dao.ProductDAO;
import com.team6.sts.vo.ProductVO;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	public List<ProductVO> listNewProduct() {
		return productDAO.listNewProduct();
	}
	public List<ProductVO> listBestProduct() {
		return productDAO.listBestProduct();
	}
	public ProductVO getProduct(String pseq) {
		return productDAO.getProduct(pseq);
	}
	public List<ProductVO> listKindProduct(String kind) {
		return productDAO.listKindProduct(kind);
	}

}
