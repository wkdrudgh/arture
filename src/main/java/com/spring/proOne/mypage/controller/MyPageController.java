package com.spring.proOne.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface MyPageController {
	public ModelAndView myPageMain(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ResponseEntity addFavorite(@RequestParam("id")String id,@RequestParam("galleryNO")int galleryNO,HttpServletRequest request, HttpServletResponse response)throws Exception; 
	public ModelAndView cancelFavorite(@RequestParam("id")String id, @RequestParam("galleryNO")int galleryNO,HttpServletRequest request, HttpServletResponse response)throws Exception; 
	public ModelAndView deleteMyGallery(@RequestParam("id")String id, @RequestParam("galleryNO")int galleryNO,HttpServletRequest request, HttpServletResponse response)throws Exception; 
	public ResponseEntity modUserInfo(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)throws Exception;
	public ResponseEntity modprofileimage(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)throws Exception;
}