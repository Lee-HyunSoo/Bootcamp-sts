package com.team6.sts.controller;

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

import com.team6.sts.service.QnaService;
import com.team6.sts.vo.MemberVO;
import com.team6.sts.vo.QnaVO;

@Controller
@RequestMapping("/qnaPage")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value = "/qnaList", method = RequestMethod.GET)
    private String listQna(HttpServletRequest request, Model model) throws ServletException {
        HttpSession session = request.getSession();
    	MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser == null) {
			return "redirect:/members/loginForm";
        } else {
            List<QnaVO> qnaList = qnaService.listQna(loginUser.getId());
            model.addAttribute("qnaList", qnaList);
        }
        return "qna/qnaList";
        
    }

	@RequestMapping(value = "/qnaWrite", method = RequestMethod.GET)
    private String writeQna()  {
        return "qna/qnaWrite";
    }
	
	@RequestMapping(value = "/qnaDetail", method = RequestMethod.GET)
    private String detailQna(@RequestParam String qseq, Model model) throws ServletException {
        QnaVO qnaVO = qnaService.detailQna(Integer.parseInt(qseq));
        model.addAttribute("qnaVO", qnaVO);
        return "qna/qnaView";
    }

	@RequestMapping(value = "/qnaInsert", method = RequestMethod.POST)
    public String insertQna(@RequestParam String content, @RequestParam String subject, HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession();
    	MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser == null) {
			return "redirect:/members/loginForm";
        } else {

            QnaVO qnaVO = new QnaVO();
            qnaVO.setContent(content);
            qnaVO.setSubject(subject);

            qnaService.insertQna(qnaVO, loginUser.getId());
            return "redirect:/qnaPage/qnaList";
        }

    }

}
