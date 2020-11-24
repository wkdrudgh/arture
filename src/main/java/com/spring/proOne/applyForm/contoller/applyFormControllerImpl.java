package com.spring.proOne.applyForm.contoller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.multi.MultiLabelUI;

import org.apache.commons.io.FileUtils;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.applyForm.service.applyFormService;
import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.ImageVO;
import com.spring.proOne.member.vo.MemberVO;

@Controller("applyFormController")
public class applyFormControllerImpl implements applyFormController{
	private static final String GALLERY_IMAGE_REPO = "C:\\o_image\\galleryimage";
	@Autowired
	applyFormService applyFormService;
	@Autowired
	ArticleVO articleVO;
	
	private List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		multipartRequest.setCharacterEncoding("utf-8");
		List<MultipartFile> MPfileList = multipartRequest.getFiles("file");
		System.out.println("MultopartFileList : " + MPfileList);
		List<String> fileList = new ArrayList<String>();
		
		
		for (MultipartFile mf : MPfileList) {
			String fileName = mf.getName();
			String originFileName = mf.getOriginalFilename();
			System.out.println("originFileName : " + originFileName);
			System.out.println("fileName : " + fileName);
			fileList.add(originFileName);
			
			File file = new File(GALLERY_IMAGE_REPO +"\\temp"+ "\\");
			

			
			System.out.println("upload FILE : " + file);
			
			if(file.exists()) {
				if(file.getParentFile().mkdirs()) {
					file.createNewFile();
				}
			}
			mf.transferTo(new File(GALLERY_IMAGE_REPO+"\\"+"temp\\"+originFileName));
			
		}
		
		return fileList;
	}
	
	
	@Override
	@RequestMapping(value="/applyForm/addNewArticle.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		multipartRequest.setCharacterEncoding("utf-8");
		String imageFileName = null;
		
		Map<String, Object> articleMap = new HashMap<String, Object>();
		
		// applyForm에서 등록된 정보들 받아오기
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name= (String)enu.nextElement();
			String value= multipartRequest.getParameter(name);
			
			//sql injection 방어
			String match = "[< > ( ) ' \" ; = + | & - ]";
			String filvalue =  value.replaceAll(match, " ");
			
			articleMap.put(name, filvalue);
		}
		
		//id 구해오기
		HttpSession session = multipartRequest.getSession();
		String id =((MemberVO)session.getAttribute("member")).getId();
		articleMap.put("id", id);
		
		// 첨부된 파일 리스트를 upload Method를 이용해서 받아옴
		List<String> fileList = upload(multipartRequest);		
		
		List<ImageVO> imageFileList = new ArrayList<ImageVO>();
		System.out.println("ImageVO의 imageFileList-------->>"+imageFileList);
		
		if(fileList != null && fileList.size() != 0) {
			for (String fileName : fileList) {
				ImageVO imageVO = new ImageVO();
				imageVO.setImageFileName(fileName);
				imageFileList.add(imageVO);
			}
			articleMap.put("imageFileName", imageFileList.get(0).getImageFileName());
			articleMap.put("imageFileList", imageFileList);
		}
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");

		try {
			int i=0;
			int applyNO = applyFormService.addNewArticle(articleMap);
			
			if(imageFileList != null && imageFileList.size() != 0) {
				for(ImageVO imageVO : imageFileList) {
					imageFileName = imageVO.getImageFileName();
					
					File srcFile = new File(GALLERY_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
					
					System.out.println("srcFile : " + srcFile);
					
					File destDir = new File(GALLERY_IMAGE_REPO+"\\"+applyNO);
					System.out.println("destDir : "+destDir);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					i++;
				}
			}
			message = "<script>";
			message += " alert('신청이 완료되었습니다..');";
			message += " location.href='"+multipartRequest.getContextPath()+"/main/main.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
		} catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				for(ImageVO imageVO : imageFileList) {
					imageFileName = imageVO.getImageFileName();
					File srcFile = new File(GALLERY_IMAGE_REPO+"\\"+"temp"+"\\" + imageFileName);
					srcFile.delete();
				}
			}
			message = "<script>";
			message = " alert('오류가 발생했습니다. 다시 시도하세요');";
			message = " location.href='" + multipartRequest.getContextPath()+"/applyForm/articleForm.do';";
			message = " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
				
		}
		return resEnt;
	}
	
	
	
	@RequestMapping(value="/applyForm/*Form.do", method=RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
}