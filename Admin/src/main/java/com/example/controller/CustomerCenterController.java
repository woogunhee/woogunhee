package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Normalid;
import com.example.domain.Notice;
import com.example.domain.Qna;
import com.example.service.NoticeService;
import com.example.service.QnaService;

@Controller
public class CustomerCenterController {
	@Autowired
	private NoticeService notiservice;
	@Autowired
	private QnaService qnaservice;
	
	static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	
	//notice 전체리스트
	@RequestMapping("noticePage")   
	public void getNoticeList(Model m) {
		Notice vo = new Notice();
		List<Notice> list = notiservice.getNoticeList(vo);
		m.addAttribute("noticeList", list);
	}

	//notice 상세보기
	@RequestMapping("noticePageDetail")
	public void getNotice(Notice vo, Model m) {
		Notice vo1 = notiservice.getNotice(vo);
		m.addAttribute("notice",vo1);
	}
	
	//qna 전체리스트
	@RequestMapping("mypageQna")
	public void getQnaList(HttpServletRequest request,Qna q,Model m) {
		HttpSession session = request.getSession();
		String nid = (String) session.getAttribute("nid");
		List<Qna> list = qnaservice.getQnaList(q);
		
		m.addAttribute("nid", nid);
		m.addAttribute("qList", list);
	}
	
	//qna 상세보기
	@RequestMapping("qnaPageDetail")
	public void getQnaDetail(HttpServletRequest request,Qna q,Model m) {
		HttpSession session = request.getSession();
		String nid = (String) session.getAttribute("nid");
		Qna qna = qnaservice.getQanDetail(q);
		
		m.addAttribute("nid", nid);
		m.addAttribute("qnaDetail", qna);
	}
	
	//qna 수정값 가져오기
	@RequestMapping("qnaPageUpdate")
	public void getQnaUpdate(HttpServletRequest request, Qna q,Model m) {
		HttpSession session = request.getSession();
		String nid = (String) session.getAttribute("nid");
		Qna qna1 = qnaservice.getQnaUpdate(q);
		
		m.addAttribute("nid",nid);
		m.addAttribute("qnaPageUpdate", qna1);
	}
	
	//qna 수정하기
	@RequestMapping("qnaUpdate")
	public String qnaUpdate(Qna q) {
		qnaservice.qnaUpdate(q);
		
		return "redirect:qnaPage";
	}
	
	//qna 삭제하기
	@RequestMapping("qnaDelete")
	public String qnaDelete(Qna q) {
		qnaservice.qnaDelete(q);
		
		return "redirect:mypageQna";
	}
	
	//qna insert 로그인 세션
	@RequestMapping("qnaInsert")
	public void loginname(HttpServletRequest request, Model m) {
		logger.info("loginidcheck");
		HttpSession session=request.getSession();
		String nid = (String)session.getAttribute("nid");
		
		m.addAttribute("nid", nid);
	}
	
	//qna 등록하기
	@RequestMapping("insertQna")
	public String insertQna(HttpServletRequest request, Qna q) {
		HttpSession session = request.getSession();
		String nid = (String) session.getAttribute("nid");
		
		Normalid vo = new Normalid();
		vo.setNid(nid);
		
		q.setNid(vo);
		
		qnaservice.insertQna(q);
		
		
		return "redirect:mypageQna";
	}
	
	
}