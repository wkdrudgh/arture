package com.spring.proOne.admin.notice.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.admin.notice.service.AdminNoticeService;
import com.spring.proOne.admin.notice.vo.NoticeVO;
import com.spring.proOne.member.vo.MemberVO;

@Controller("adminNoticeController")
@RequestMapping(value="/admin/notice")
/* @EnableAspectJAutoProxy */
public class AdminNoticeControllerImpl implements AdminNoticeController{

	@Autowired
	AdminNoticeService noticeService;
	@Autowired
	NoticeVO noticeVO;
	@Autowired
	HttpSession session;
	
	@Override
	@RequestMapping(value="/noticeList.do" ,method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView noticeList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		//	페이징하기 위한 정보들	
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		int section = Integer.parseInt(((_section==null)?"1":_section));
		int pageNum = Integer.parseInt(((_pageNum==null)?"1":_pageNum));
		
		
		Map<String, Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		
		Map listsMap = noticeService.listNotieces(pagingMap);
		
		
		mav.addObject("listsMap", listsMap);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/addNewNotice.do" ,method = RequestMethod.POST)
	public ResponseEntity addNewNotice(@ModelAttribute("notice") NoticeVO notice, HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("utf-8");
		noticeService.addNotice(notice);
		ModelAndView mav = new ModelAndView("redirect:/admin/notice/noticeList.do");
		
		response.setContentType("text/html;charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type",	"text/html;charset=utf-8");
		
		message = "<script>";
		message += " alert('공지사항을 등록하였습니다.');";
		message += " location.href='" + request.getContextPath()+"/admin/notice/noticeList.do';";
		message += " </script>";
		resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		
		return resEnt;
	}
	
	@Override
	@RequestMapping(value="/removeNotice.do" ,method=RequestMethod.POST)
	public ResponseEntity removeNotice(@RequestParam("noticeNO" ) int no, HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("utf-8");
		noticeService.removeNotice(no);
		ModelAndView mav = new ModelAndView("redirect:/admin/notice/noticeList.do");
		response.setContentType("text/html;charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type",	"text/html;charset=utf-8");
		
		message = "<script>";
		message += " alert('해당 공지사항을 삭제하였습니다.');";
		message += " location.href='" + request.getContextPath()+"/admin/notice/noticeList.do';";
		message += " </script>";
		resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		return resEnt;
	}
	
	@Override
	@RequestMapping(value="/modNotice.do" ,method = RequestMethod.POST)
	public ModelAndView modNotice(@ModelAttribute("notice") NoticeVO notice, HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("utf-8");
		System.out.println("----------  mod   ---------------");
		System.out.println("no : " + notice.getNo());
		System.out.println("title : " + notice.getTitle());
		System.out.println("content : " + notice.getContent());
		System.out.println("writeDate : " + notice.getWriteDate());
		
		noticeService.modNotice(notice);
		ModelAndView mav = new ModelAndView("redirect:/admin/notice/noticeList.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/noticeView.do" ,method = RequestMethod.POST)
	public ModelAndView noticeView(@RequestParam("noticeNO") int noticeNO, HttpServletRequest request, HttpServletResponse response) throws Exception{

		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		noticeVO = noticeService.viewNotice(noticeNO);
		mav.addObject("notice", noticeVO);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/*Form.do", method =  RequestMethod.POST)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

}
