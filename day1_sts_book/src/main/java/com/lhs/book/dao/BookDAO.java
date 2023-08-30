package com.lhs.book.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lhs.book.vo.BookVO;

public interface BookDAO {

	public List<BookVO> selectBooks() throws DataAccessException;
	public int findMaxBookNo() throws DataAccessException;
	public BookVO detailBook(int bookNo) throws DataAccessException;
	public void insertBook(BookVO bookVO) throws DataAccessException;
	public void modifyBook(BookVO bookVO) throws DataAccessException;
	public void deleteBook(int bookNo) throws DataAccessException;
}
