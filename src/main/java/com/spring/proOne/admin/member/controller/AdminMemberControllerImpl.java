package com.spring.proOne.admin.member.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.admin.member.service.AdminMemberService;
import com.spring.proOne.member.vo.MemberVO;

@Controller("adminMemberController")
@RequestMapping(value="/admin/member")
public class AdminMemberControllerImpl implements AdminMemberController{
	@Autowired
	private AdminMemberService adminMemberService;
	@Autowired
	MemberVO memberVO;
	
	@Override
	@RequestMapping(value="/memberList.do" ,method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		List<MemberVO> memberList = adminMemberService.listMembers();
		mav.addObject("memberList", memberList);

		return mav;
	}
	
	@Override
	@RequestMapping(value="/mod_pwd.do", method=RequestMethod.POST)
	@ResponseBody
	public void modPassword(MemberVO temp, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		adminMemberService.modInfo(temp);
	}
	
	@Override
	@RequestMapping(value="/removeMember.do" ,method=RequestMethod.POST)
	public ResponseEntity removeMember(@RequestParam("temp_ID" ) String ID, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		adminMemberService.removeMember(ID);
		ModelAndView mav = new ModelAndView("redirect:/admin/member/memberList.do");
		
		response.setContentType("text/html;charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type",	"text/html;charset=utf-8");
		
		message = "<script>";
		message += " alert('해당 사용자를 삭제하였습니다.');";
		message += " location.href='" + request.getContextPath()+"/admin/member/memberList.do';";
		message += " </script>";
		resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		
		return resEnt;
	}
}
