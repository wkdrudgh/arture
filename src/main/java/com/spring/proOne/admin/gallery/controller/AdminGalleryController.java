package com.spring.proOne.admin.gallery.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.member.vo.MemberVO;

public interface AdminGalleryController {

	ModelAndView galleryList(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView galleryView(int galleyNO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	ResponseEntity removeGallery(int applyNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}
