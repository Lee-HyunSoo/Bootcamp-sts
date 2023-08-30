package com.lhs.book.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.lhs.book.vo.BookVO;

public interface BookController {

	public String listBook(Model model);
	public String insertForm(Model model);
	public String insertBook(@ModelAttribute BookVO bookVO);
	public String detailBook(@PathVariable int bookNo, Model model);
	public String deleteBook(@PathVariable int bookNo);
	public String modifyForm(@PathVariable int bookNo, Model model);
	public String modifyBook(@ModelAttribute BookVO bookVO);
	
}
