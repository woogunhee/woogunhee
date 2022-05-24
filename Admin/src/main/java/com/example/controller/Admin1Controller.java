package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Normalid;
import com.example.domain.Qna;
import com.example.domain.Qnacomment;
import com.example.service.CustomerService;
import com.example.service.QnaService;
import com.example.service.QnacommentService;

@Controller
//@RequestMapping("/admin")
public class Admin1Controller {

	static final Logger logger = LoggerFactory.getLogger(Admin1Controller.class);
	
	@Autowired
	private QnaService qnaService;
	@Autowired
	private QnacommentService qnacommentService;
	@Autowired
	private CustomerService customerService;
	//다음페이지
//	@RequestMapping("/{step}")
//	public void viewPage(@PathVariable String step) {
//		//return "/board/" + step;	
//		logger.info("다음페이지");
//	}
	
	//질문응답 리스트 전체조회
	@RequestMapping("/getQnaList")
	public void getQnaList(Model m) {
		logger.info("전체 QNA");
		Qna vo = new Qna();
		List<Qna> list = qnaService.getQnaList(vo);
		m.addAttribute("qnaList", list);
	}
	
	//질문응답 삭제버튼 삭제
	@RequestMapping("/deleteQna")
	public String deleteQna(Qna q) {
		logger.info("삭제");
		qnaService.deleteQna(q);
		
		return "redirect:/getQnaList";
		
	}
	
	//답글 제목 가져오기
	@RequestMapping("/qnareply")
	public String replyList(Qna q,Model m) {
		
		Qna qc = qnaService.replyList(q);
		logger.info("내용 가져오기:"+qc.getNcontent());
		m.addAttribute("Qna",qc);
		return "/qnareply";
	}
	
	
	//답글 추가
	@RequestMapping("/insertReply")
	public String insertReply(Qnacomment qc) {
		logger.info("답글등록");
		qnacommentService.insertReply(qc);
		
		return "redirect:getQnaList";
	}
	
	//고객 전체 리스트
	@RequestMapping("/customer")
	public void customerList(Model m) {
		logger.info("고객 전체 리스트");
		Normalid id = new Normalid();
		List<Normalid> list = customerService.customerList(id);
		m.addAttribute("normalId", list);
		
	}
	
	//고객 상세 보기
	@RequestMapping("customerdetail")
	public void customerdetail(Normalid id,Model m) {
		logger.info("고객 상세보기");
		Normalid id1 = customerService.customerdetail(id);
		m.addAttribute("cdetail", id1);

	}
	
}

