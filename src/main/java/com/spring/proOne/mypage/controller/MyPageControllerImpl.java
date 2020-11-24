package com.spring.proOne.mypage.controller;

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

import org.apache.commons.io.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proOne.mypage.service.MyPageService;
import com.spring.proOne.mypage.vo.FavoriteVO;
import com.spring.proOne.member.vo.MemberVO;
import com.spring.proOne.gallery.service.GalleryService;
import com.spring.proOne.gallery.vo.GalleryVO;

 

@Controller("MyPageController")
public class MyPageControllerImpl implements MyPageController {
	private static final String PROFILE_IMAGE_REPO = "C:\\o_image\\profileimage";

	@Autowired
	MyPageService mypageservice;
	@Autowired
	GalleryService galleryservice;
	@Autowired
	MemberVO memberVO;

	@Autowired
	HttpSession session;

	

	@Override
	@RequestMapping(value="/mypage/myPageMain.do" ,method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView myPageMain(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(session.getAttribute("isLogOn") != null) {
		
		String viewName=(String)request.getAttribute("viewName");
		
		memberVO = (MemberVO) session.getAttribute("member"); //세션에있는 memberVO 객체를 불러온다.
		memberVO = mypageservice.selectMyInfo(memberVO.getId()); //세션에있던 memberVO 객체를 이용해 DB에 저장된 memberVO 객체로 초기화 한다.
		
		session.setAttribute("member",memberVO);//세션 초기화
		
		String id = memberVO.getId();
		
	    List<GalleryVO> favoritelist = mypageservice.myFavorite(id); //내가 좋아요를 누른 겔러리들
		List<GalleryVO> myGallery = mypageservice.myGallery(id);  // 내가 작성한 갤러리들
		Map count = mypageservice.count(id);//내가 받은 좋아요 갯수와 작성한 게시글 수
		
			List<Integer> likeList = new ArrayList<Integer>();
			if(myGallery.size()!=0) {
			for(GalleryVO g : myGallery) {
				int like =  galleryservice.like(g.getGalleryNO());
				likeList.add(like);
				}
			}
			
		mav.setViewName(viewName);
		mav.addObject("like", likeList);
		mav.addObject("favoritelist", favoritelist);
		mav.addObject("myGallery", myGallery);
		mav.addObject("count", count);
		} else {
			String viewName = "redirect:/member/loginForm.do";
			mav.setViewName(viewName);
		}

		return mav;
		
	}

	

	@Override //좋아요 취소
	@RequestMapping(value="mypage/cancelFavorite.do" ,method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView cancelFavorite(@RequestParam("id")String id, @RequestParam("galleryNO")int galleryNO,HttpServletRequest request, HttpServletResponse response)throws Exception{

		ModelAndView mav = new ModelAndView();

		FavoriteVO favoriteVO = new FavoriteVO();
		favoriteVO.setId(id);
		favoriteVO.setGalleryNO(galleryNO);
		mypageservice.cancelfavorite(favoriteVO);

		mav.setViewName("redirect:/mypage/myPageMain.do");

		return mav;

	}

	@Override//좋아요 추가
	@RequestMapping(value="/mypage/addFavorite.do" ,method = {RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity addFavorite(@RequestParam("id")String id, @RequestParam("galleryNO") int galleryNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResponseEntity resEntity = null;
		FavoriteVO favoriteVO = new FavoriteVO();
		favoriteVO.setId(id);
		favoriteVO.setGalleryNO(galleryNO);
		int result = mypageservice.selectOverlappedFavorite(favoriteVO);
		if(result==0) {
			mypageservice.insertfavorite(favoriteVO);
			resEntity = new ResponseEntity("true", HttpStatus.OK);
			System.out.println("result : " + result);
		}else {
			mypageservice.cancelfavorite(favoriteVO);
			resEntity = new ResponseEntity("false", HttpStatus.OK);
			System.out.println("result : " + result);
		}
		return resEntity;
	}
	
	@RequestMapping(value="/mypage/selectOverlappedFavorite.do" ,method = {RequestMethod.POST,RequestMethod.GET})//해당 게시글 좋아요를 눌렀는지 확인
	public ResponseEntity selectOverlappedFavorite(@RequestParam("id")String id, @RequestParam("galleryNO") int galleryNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResponseEntity resEntity = null;
		FavoriteVO favoriteVO = new FavoriteVO();
		favoriteVO.setId(id);
		favoriteVO.setGalleryNO(galleryNO);
		int result = mypageservice.selectOverlappedFavorite(favoriteVO);

		if(result==1) {
			resEntity = new ResponseEntity("true", HttpStatus.OK);
		}else {
			resEntity = new ResponseEntity("false", HttpStatus.OK);
		}
		return resEntity;
	}

	@Override//내 게시글 삭제
	@RequestMapping(value="/mypage/deletemygallery.do" ,method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView deleteMyGallery(String id, int galleryNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		FavoriteVO favoriteVO = new FavoriteVO();
		favoriteVO.setId(id);
		favoriteVO.setGalleryNO(galleryNO);
		mypageservice.deletemygallery(favoriteVO);
		mav.setViewName("redirect:/mypage/myPageMain.do");
		return mav;

	}

	@Override//프로필 이미지 수정
	@RequestMapping(value="/mypage/modprofileimage.do" ,method = {RequestMethod.POST,RequestMethod.GET})//프로필 이미지 수정
	public ResponseEntity modprofileimage(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		ResponseEntity resEnt=null;
		String message;
		
		Map<String,Object> usermap = new HashMap<String, Object>();
		
		String originalFileName =(String)memberVO.getProfileImage(); //vo에서 바뀌기전 이미지 파일네임을 가져온다
		String id = memberVO.getId();
		
		String imageFileName= upload(multipartRequest);//사용자가 올린 파일의 이름 업로드
		
		usermap.put("profileImage", imageFileName);//db로 보낼 값 맵에 저장
		usermap.put("id", id);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");		
		try {
			mypageservice.updateProFileImage(usermap);
			if(imageFileName!=null && imageFileName.length()!=0) {
				File originalFile = new File(PROFILE_IMAGE_REPO+"\\"+id+"\\"+originalFileName); //이전에 사용했던 프로필사진을 찾아온다 if있다면 삭제 
				if(originalFile.exists()) {
					if(originalFile.delete()) {
						System.out.println("이전 프로필 사진파일 삭제 완료");
					}else {
						System.out.println("이전 프로필 사진파일삭제 실패");
					}
				}
					File srcFile = new File(PROFILE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
					File destDir = new File(PROFILE_IMAGE_REPO+"\\"+id);
					FileUtils.moveFileToDirectory(srcFile, destDir,true);					
			}
			message = "<script>";
			message += " alert('프로필 사진 수정완료했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/mypage/myPageMain.do'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			File srcFile = new File(PROFILE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			srcFile.delete();
			System.out.println("이미지 업로드 실패");
			resEnt = new ResponseEntity(responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();

		}
		return resEnt;
	}
	
	@Override//내정보 수정
	@RequestMapping(value="/mypage/modUserInfo.do" ,method = RequestMethod.POST)
	public ResponseEntity modUserInfo(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		ResponseEntity resEnt=null;
		Map<String,Object> usermap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();//form 에있는 name을 가져온다
		session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");	
		
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			//sql injection 방어
			String match = "[< > ( ) ' \" ; = + | & - # ]";
			String filvalue =  value.replaceAll(match, " ");
	
			usermap.put(name,filvalue); //여기서 usermap에 값들을 저장해준다. id 값과 image 값은 따로 가져오지않기떄문에 put으로 넣어준다
		}
		
		String id = (String) usermap.get("id");
		
		if(usermap.get("profileImage").equals("duke.png")) {//듀크로 바꿧을경우
			String originalFileName = memberVO.getProfileImage();
			File originalFile = new File(PROFILE_IMAGE_REPO+"\\"+id+"\\"+originalFileName); //이전에 사용했던 프로필사진을 찾아온다 if있다면 삭제 
			if(originalFile.exists()) {
				if(originalFile.delete()) {
					System.out.println("이전 프로필 사진파일 삭제 완료");
				}else {
					System.out.println("이전 프로필 사진파일삭제 실패");
				}
			}
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			mypageservice.updateMyInfo(usermap);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String message;
		message = "<script>";
		message += " alert('수정완료했습니다.');";
		message += " location.href='"+multipartRequest.getContextPath()+"/mypage/myPageMain.do'; ";
		message +=" </script>";
	    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		
		return resEnt;
	}

	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{

		String imageFileName= null;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while(fileNames.hasNext()){
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			imageFileName=mFile.getOriginalFilename();
			File file = new File(PROFILE_IMAGE_REPO +"\\"+ fileName);
			if(mFile.getSize()!=0){ //File Null Check
				if(! file.exists()){ //경로상에 파일이 존재하지 않을 경우
					if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
							file.createNewFile(); //이후 파일 생성
					}
				}

				mFile.transferTo(new File(PROFILE_IMAGE_REPO +"\\"+"temp"+ "\\"+imageFileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
			}
		}
		return imageFileName;

	}




}