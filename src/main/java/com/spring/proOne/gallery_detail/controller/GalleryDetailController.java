package com.spring.proOne.gallery_detail.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface GalleryDetailController {
	public ModelAndView galleryDetail( @RequestParam("galleryNO") int galleryNO,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
