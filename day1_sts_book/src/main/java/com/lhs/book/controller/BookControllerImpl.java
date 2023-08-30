package com.lhs.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lhs.book.service.BookServiceImpl;
import com.lhs.book.vo.BookVO;

@Controller("bookController")
public class BookControllerImpl implements BookController {

	@Autowired
	private BookServiceImpl bookService;
	
	@Override
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listBook(Model model) {		
		model.addAttribute("books", bookService.selectBooks());
		return "/book/bookList";
	}
	
	@Override
	@RequestMapping(value="/insert", method = RequestMethod.GET)
	public String insertForm(Model model) {
		model.addAttribute("bookNo", bookService.findMaxBookNo() + 1);
		return "/book/insertForm";
	}
	
	@Override
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String insertBook(@ModelAttribute BookVO bookVO) {
		bookService.insertBook(bookVO);
		return "redirect:/list";
	}
	
	@Override
	@RequestMapping(value="/detail/{bookNo}", method = RequestMethod.GET)
	public String detailBook(@PathVariable int bookNo, Model model) {
		model.addAttribute("bookVO", bookService.detailBook(bookNo));
		return "/book/bookDetail";
	}
	
	@Override
	@RequestMapping(value="/delete/{bookNo}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable int bookNo) {
		bookService.deleteBook(bookNo);
		return "redirect:/list";
	}
	
	@Override
	@RequestMapping(value="/modify/{bookNo}", method = RequestMethod.GET)
	public String modifyForm(@PathVariable int bookNo, Model model) {
		model.addAttribute("bookNo", bookNo);
		return "/book/modifyForm";
	}
	
	@Override
	@RequestMapping(value="/modify/{bookNo}", method = RequestMethod.POST)
	public String modifyBook(@ModelAttribute BookVO bookVO) {
		bookService.modifyBook(bookVO);
		return "redirect:/list";
	}
	
}
