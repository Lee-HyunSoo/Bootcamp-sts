package com.lhs.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.lhs.book.vo.BookVO;

@Repository("boardDAO")
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BookVO> selectBooks() throws DataAccessException {
		return sqlSession.selectList("mapper.book.selectBooks");
	}

	@Override
	public int findMaxBookNo() throws DataAccessException {
		return sqlSession.selectOne("mapper.book.findMaxBookNo");
	}

	@Override
	public BookVO detailBook(int bookNo) throws DataAccessException {
		return sqlSession.selectOne("mapper.book.detailBook", bookNo);
	}

	@Override
	public void insertBook(BookVO bookVO) throws DataAccessException {
		sqlSession.insert("mapper.book.insertBook", bookVO);	
	}
	
	@Override
	public void modifyBook(BookVO bookVO) throws DataAccessException {
		sqlSession.update("mapper.book.modifyBook", bookVO);
	}

	@Override
	public void deleteBook(int bookNo) throws DataAccessException {
		sqlSession.delete("mapper.book.deleteBook", bookNo);
	}

}
