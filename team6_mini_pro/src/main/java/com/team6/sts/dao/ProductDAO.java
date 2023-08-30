package com.team6.sts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.team6.sts.util.DBManager;
import com.team6.sts.vo.ProductVO;

@Repository
public class ProductDAO {

	@Autowired
	private SqlSession sqlSession;

	// 신상품
	public List<ProductVO> listNewProduct() {
		List<ProductVO> productList = sqlSession.selectList("mapper.product.listNewProduct");
		return productList;
	}

	// 베스트 상품
	public List<ProductVO> listBestProduct() {
		List<ProductVO> productList = sqlSession.selectList("mapper.product.listBestProduct");
		return productList;
	}
		
	public ProductVO getProduct(String pseq) {
		ProductVO product = sqlSession.selectOne("mapper.product.getProduct", pseq);
		return product;
	}
	
		
	public List<ProductVO> listKindProduct(String kind) {
		List<ProductVO> productList = sqlSession.selectList("mapper.product.listKindProduct", kind);
		return productList;
	}
		
	public int insertProduct(ProductVO product) {
		return sqlSession.insert("mapper.product.insertProduct", product);
	}

	public int updateProduct(ProductVO product) {
		return sqlSession.update("mapper.product.updateProduct", product);
	}
		
	public int totalRecord(String name) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		int total_pages = sqlSession.selectOne("mapper.product.totalRecord", params);
		return total_pages;
	}

	static int view_rows = 5; // 페이지의 개수
	static int counts = 5; // 한 페이지에 나타낼 상품의 개수

	// 페이지 이동을 위한 메소드
	@Transactional
	public String pageNumber(int tpage, String name, String path) {
		String str = "";

		int total_pages = totalRecord(name);
		int page_count = total_pages / counts + 1;

		if (total_pages % counts == 0) {
			page_count--;
		}
		if (tpage < 1) {
			tpage = 1;
		}

		int start_page = tpage - (tpage % view_rows) + 1;
		int end_page = start_page + (counts - 1);

		if (end_page > page_count) {
			end_page = page_count;
		}
		if (start_page > view_rows) {
			str += "<a href=" + path + "/admin/products/adminProductList?tpage=1&key=" + name
					+ ">&lt;&lt;</a>&nbsp;&nbsp;";
			str += "<a href="+ path +"/admin/products/adminProductList?tpage=" + (start_page - 1);
			str += "&key=<%=product_name%>>&lt;</a>&nbsp;&nbsp;";
		}

		for (int i = start_page; i <= end_page; i++) {
			if (i == tpage) {
				str += "<font color=red>[" + i + "]&nbsp;&nbsp;</font>";
			} else {
				str += "<a href=" + path + "/admin/products/adminProductList?tpage=" + i + "&key=" + name + ">[" + i
						+ "]</a>&nbsp;&nbsp;";
			}
		}

		if (page_count > end_page) {
			str += "<a href=" + path + "/admin/products/adminProductList?tpage=" + (end_page + 1) + "&key=" + name
					+ "> &gt; </a>&nbsp;&nbsp;";
			str += "<a href=" + path + "/admin/products/adminProductList?tpage=" + page_count + "&key=" + name
					+ "> &gt; &gt; </a>&nbsp;&nbsp;";
		}
		return str;
	}
				
	public List<ProductVO> listProduct(int tpage, String product_name) {
		List<ProductVO> productList = new ArrayList<ProductVO>();
		String str = "select pseq, indate, name, price1, price2, useyn, bestyn  from product where name like '%'||?||'%' order by pseq desc";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int absolutepage = 1;

		try {
			con = DBManager.getConnection();
			absolutepage = (tpage - 1) * counts + 1;
			pstmt = con.prepareStatement(str, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			if (product_name.equals("")) {
				pstmt.setString(1, "%");
			} else {
				pstmt.setString(1, product_name);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rs.absolute(absolutepage);
				int count = 0;

				while (count < counts) {
					ProductVO product = new ProductVO();
					product.setPseq(rs.getInt(1));
					product.setIndate(rs.getTimestamp(2));
					product.setName(rs.getString(3));
					product.setPrice1(rs.getInt(4));
					product.setPrice2(rs.getInt(5));
					product.setUseyn(rs.getString(6));
					product.setBestyn(rs.getString(7));
					productList.add(product);
					if (rs.isLast()) {
						break;
					}
					rs.next();
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return productList;
	}
		
	public void deleteProduct(int pseq) {
		sqlSession.delete("mapper.product.deleteProduct", pseq);
	}
	
}
