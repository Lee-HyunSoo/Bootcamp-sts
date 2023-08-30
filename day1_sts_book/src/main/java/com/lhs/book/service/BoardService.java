package com.lhs.book.service;

import java.util.List;

import com.lhs.book.vo.BookVO;

public interface BoardService {
	
	public List<BookVO> selectBooks();
	public int findMaxBookNo();
	public void insertBook(BookVO bookVO);
	public BookVO detailBook(int bookNo);
	public void deleteBook(int bookNo);
	public void modifyBook(BookVO bookVO);

}
