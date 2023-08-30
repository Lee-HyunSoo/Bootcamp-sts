package com.lhs.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.book.dao.BookDAOImpl;
import com.lhs.book.vo.BookVO;

@Service("bookService")
public class BookServiceImpl implements BoardService {

	@Autowired
	private BookDAOImpl bookDAO;

	@Override
	public List<BookVO> selectBooks() {
		return bookDAO.selectBooks();
	}

	@Override
	public int findMaxBookNo() {
		return bookDAO.findMaxBookNo();
	}

	@Override
	public void insertBook(BookVO bookVO) {
		bookDAO.insertBook(bookVO);
	}

	@Override
	public BookVO detailBook(int bookNo) {
		return bookDAO.detailBook(bookNo);
	}
	
	@Override
	public void deleteBook(int bookNo) {
		bookDAO.deleteBook(bookNo);
	}

	@Override
	public void modifyBook(BookVO bookVO) {
		bookDAO.modifyBook(bookVO);
	}

}
