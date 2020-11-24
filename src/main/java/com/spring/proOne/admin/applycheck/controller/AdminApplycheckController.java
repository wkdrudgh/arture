package com.spring.proOne.admin.applycheck.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.member.vo.MemberVO;

public interface AdminApplycheckController {

	ModelAndView applyList(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView applyView(int applyNO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	ResponseEntity acceptApplyForm(int applyNO, HttpServletRequest request, HttpServletResponse response)	throws Exception;

	ResponseEntity rejectApplyForm(int applyNO, HttpServletRequest request, HttpServletResponse response)	throws Exception;

}
