package com.spring.proOne.admin.gallery.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.admin.applycheck.service.AdminApplycheckService;
import com.spring.proOne.admin.gallery.service.AdminGalleryService;
import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.gallery.vo.ImageVO;

@Controller("adminGalleryController")
@RequestMapping(value="/admin/gallery")
public class AdminGalleryControllerImpl implements AdminGalleryController{
	@Autowired
	private AdminGalleryService adminGalleryService;
	@Autowired
	private AdminApplycheckService adminApplyService;
	
	@Autowired
	GalleryVO galleryVO;
	@Autowired
	ImageVO imageVO;
	
	@Override
	@RequestMapping(value="/galleryList.do" ,method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView galleryList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		// 승인된 글 가져오기
		List<GalleryVO> galleriesList = adminGalleryService.listGalleries();
		mav.addObject("galleriesList", galleriesList);

		return mav;
	}
	
	@Override
	@RequestMapping(value="/galleryView.do" ,method = RequestMethod.POST)
	public ModelAndView galleryView(@RequestParam("galleryNO") int galleryNO, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		// galleyNO에 따른 galley 에 저장된 값 가져오기
		GalleryVO gallery = adminGalleryService.getGallery(galleryNO);
		mav.addObject("gallery", gallery);
		
		// applyForm의 applyNO에 따른 이미지 파일정보 가져오기
		List<ImageVO> imagesList = adminApplyService.getImages(galleryNO);
		mav.addObject("imagesList", imagesList);
		
		return mav;
	}
	
	@Override
	@RequestMapping(value="/removeGallery.do" ,method = RequestMethod.POST)
	public ResponseEntity removeGallery(@RequestParam("galleryNO") int galleryNO, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/admin/gallery/galleryList.do");
		// apply삭제
		adminGalleryService.removeGallery(galleryNO);
		
		response.setContentType("text/html;charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type",	"text/html;charset=utf-8");
		try {
			File destDir = new File("C:\\o_image\\galleryimage\\"+galleryNO);
			FileUtils.deleteDirectory(destDir);
			
			message = "<script>";
			message += " alert('해당 갤러리를 삭제 하였습니다.');";
			message += " location.href='" + request.getContextPath()+"/admin/gallery/galleryList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch(Exception e) {
			message = "<script>";
			message += " alert('갤러리 삭제가 취소되었습니다.');";
			message += " location.href='"+request.getContextPath()+"/admin/gallery/galleryList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}	
}
