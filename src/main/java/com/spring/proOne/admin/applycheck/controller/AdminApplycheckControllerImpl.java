package com.spring.proOne.admin.applycheck.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.admin.applycheck.service.AdminApplycheckService;
import com.spring.proOne.admin.member.service.AdminMemberService;
import com.spring.proOne.member.vo.MemberVO;
import com.spring.proOne.applyForm.service.applyFormService;
import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.ImageVO;

@Controller("adminApplycheckController")
@RequestMapping(value="/admin/applycheck")
public class AdminApplycheckControllerImpl implements AdminApplycheckController{
	@Autowired
	private AdminApplycheckService adminApplycheckService;
	
	@Autowired
	ArticleVO articleVO;
	@Autowired
	ImageVO imageVO;
	
	@Override
	@RequestMapping(value="/applyList.do" ,method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView applyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		// 글 가져오기
		List<ArticleVO> applysList = adminApplycheckService.listApplyForms();
		mav.addObject("applysList", applysList);

		return mav;
	}
	
	@Override
	@RequestMapping(value="/applyView.do" ,method = RequestMethod.POST)
	public ModelAndView applyView(@RequestParam("applyNO") int applyNO, HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		// applyForm에 저장된 값 가져오기
		ArticleVO application = adminApplycheckService.getApplyForm(applyNO);
		mav.addObject("application", application);
		// applyForm의 applyNO에 따른 이미지 파일정보 가져오기
		List<ImageVO> imagesList = adminApplycheckService.getImages(applyNO);
		mav.addObject("imagesList", imagesList);
		System.out.println("imagesList regDate : " + imagesList.get(0).getRegDate());
		
		return mav;
	}
	
	@Override
	@RequestMapping(value="/acceptApplyForm.do" ,method = RequestMethod.POST)
	public ResponseEntity acceptApplyForm(@RequestParam("applyNO") int applyNO, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/admin/applycheck/applyList.do");
		
		response.setContentType("text/html;charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type",	"text/html;charset=utf-8");
		
		
		// 데이터 이동 및 apply삭제
		adminApplycheckService.acceptApplyForm(applyNO);
		
		message = "<script>";
		message += " alert('해당 신청서를 승인하였습니다.');";
		message += " location.href='" + request.getContextPath()+"/admin/applycheck/applyList.do';";
		message += " </script>";
		resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		
		return resEnt;
	}
	
	@Override
	@RequestMapping(value="/rejectApplyForm.do" ,method = RequestMethod.POST)
	public ResponseEntity rejectApplyForm(@RequestParam("applyNO") int applyNO, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/admin/applycheck/applyList.do");
		// apply삭제
		adminApplycheckService.rejectApplyForm(applyNO);
		
		response.setContentType("text/html;charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type",	"text/html;charset=utf-8");
		try {
			File destDir = new File("C:\\o_image\\galleryimage\\"+applyNO);
			FileUtils.deleteDirectory(destDir);
			
			message = "<script>";
			message += " alert('해당 신청서를 거절하였습니다.');";
			message += " location.href='" + request.getContextPath()+"/admin/applycheck/applyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch(Exception e) {
			message = "<script>";
			message += " alert('취소되었습니다.');";
			message += " location.href='"+request.getContextPath()+"/admin/applycheck/applyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}	
}
