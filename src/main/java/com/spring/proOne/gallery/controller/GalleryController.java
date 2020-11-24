package com.spring.proOne.gallery.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.gallery.vo.GalleryVO;


public interface GalleryController {
	//갤러리 페이지로 이동
	public ModelAndView GalleryList(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
