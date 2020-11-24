package com.spring.proOne.admin.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.member.vo.MemberVO;

public interface AdminMemberController {

	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void modPassword(MemberVO temp, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity removeMember(String ID, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
