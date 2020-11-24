package com.spring.proOne.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.main.service.MainService;
import com.spring.proOne.main.vo.MainVO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.admin.notice.service.AdminNoticeService;
import com.spring.proOne.admin.notice.vo.NoticeVO;
import com.spring.proOne.gallery.service.GalleryService;
import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.gallery.vo.ImageVO;
import com.spring.proOne.main.service.MainService;


@Controller("mainController")
public class MainControllerImpl implements MainController{
   private static final String GALLERY_IMAGE_REPO = "C:\\o_image\\galleryimage";
   
   
   @Autowired
   GalleryVO galleryVO;
   @Autowired
   MainService mainService;
   @Autowired
   GalleryService galleryService;
   @Autowired
   AdminNoticeService noticeService;
   
   
   @RequestMapping(value="/main/main.do", method=RequestMethod.GET)
   public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      //뷰네임 받아오기
      String viewName = (String)request.getAttribute("viewName");
      ModelAndView mav = new ModelAndView();
      
      try {
    	  //공지사항 받기
    	  List<NoticeVO> noticeList = noticeService.listNotieces();
    	  mav.addObject("noticeList", noticeList);
    	  System.out.println("메인에서 noticeLIST size : " + noticeList.size());
    	  
      }catch (Exception e) {
    	  // TODO: handle exception
    	  mav.addObject("noticeList", null);
      }
      
      try {
    	  // 갤러리 리스트 받아오기
    	  List<GalleryVO> galleryList = galleryService.listGallerys();
    	  mav.addObject("galleryList", galleryList);
    	  System.out.println("메인에서 galleryList size : " + galleryList.size());
      }catch (Exception e) {
    	  // TODO: handle exception
    	  mav.addObject("galleryList", null);
      }
      try {                 
         // 갤러리 좋아요 값 받아오기
         List<MainVO> galleryLike = mainService.likegallery();
         int like1 = galleryLike.get(0).getGalleryLike();
         int kingNum1 = galleryLike.get(0).getGalleryNO();
         int like2 = galleryLike.get(1).getGalleryLike();
         int kingNum2 = galleryLike.get(1).getGalleryNO();
         //좋아요값으로 겔러리 가져오기
         GalleryVO kingallery1 = galleryService.selectDetailGallery(kingNum1);
         GalleryVO kingallery2 = galleryService.selectDetailGallery(kingNum2);

         mav.addObject("like1",like1);
         mav.addObject("galleryLike1",kingallery1);
         mav.addObject("like2",like2);
         mav.addObject("galleryLike2",kingallery2);         
      }catch(Exception e){
         
         mav.addObject("like1",null);
         mav.addObject("galleryLike1",null);
         mav.addObject("like2",null);
         mav.addObject("galleryLike2",null);   
         mav.setViewName(viewName);
         return mav;
         
      }
      

      mav.setViewName(viewName);
      return mav;
   }
   
   
   
}