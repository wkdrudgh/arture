package com.spring.proOne.gallery_detail.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.gallery.vo.ImageVO;
import com.spring.proOne.gallery_detail.service.GalleryDetailService;
import com.spring.proOne.member.service.MemberService;
import com.spring.proOne.member.vo.MemberVO;
import com.spring.proOne.mypage.vo.FavoriteVO;

@Controller("galleryDetailController")
public class GalleryDetailControllerImpl implements GalleryDetailController{
	private static final String GALLERY_IMAGE_REPO = "D:\\gallery\\g_image";
	@Autowired
	GalleryDetailService galleryDetailService;
	@Autowired
	GalleryVO galleryVO;
	@Autowired
	MemberService memberService;
	
	@Override
	@RequestMapping(value = "/gallery_detail/galleryDetail.do" , method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView galleryDetail(@RequestParam("galleryNO") int galleryNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		galleryVO = galleryDetailService.viewGalleryDetail(galleryNO);
		String id = galleryVO.getId();
		MemberVO memberVO = memberService.selectMemberById(id);
		Map galleryMap = galleryDetailService.viewGalleryImages(galleryNO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("artist", memberVO);
		mav.addObject("gallery", galleryVO);
		mav.addObject("galleryMap", galleryMap);
		return mav;
	}
}
