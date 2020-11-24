package com.spring.proOne.mypage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.proOne.mypage.dao.MyPageDAO;
import com.spring.proOne.mypage.vo.FavoriteVO;
import com.spring.proOne.member.vo.MemberVO;
import com.spring.proOne.gallery.vo.GalleryVO;


@Service("myPageService")
public class MyPageServiceImpl implements MyPageService{
	@Autowired
	MyPageDAO myPageDAO;

	@Override
	public MemberVO selectMyInfo(String id) throws Exception {//내정보 불러오기
		return myPageDAO.selectMyInfo(id);
	}

	@Override
	public void updateMyInfo(Map myinfomap) throws Exception {//내정보 수정
		myPageDAO.updateMyInfo(myinfomap);
	}
	
	@Override
	public void updateProFileImage(Map myinfomap) throws Exception {
		myPageDAO.updateProFileImage(myinfomap);
	}

	@Override
	public List<GalleryVO> myFavorite(String id) throws Exception {//좋아요누른게시글 불러오기
		List<GalleryVO> myFavoritelist =myPageDAO.myFavorite(id);
		return myFavoritelist;
	}

	@Override
	public List<GalleryVO> myGallery(String id) throws Exception {//내 게시글 불러오기
		List<GalleryVO> myGallerylist =  myPageDAO.myGallery(id);
		return myGallerylist;
	}

	@Override
	public boolean confirmPassword(String id, String pwd) throws Exception {//비밀번호확인
		return myPageDAO.confirmPassword(id, pwd);
	}

	@Override
	public void cancelfavorite(FavoriteVO favoriteVO) throws Exception {//좋아요 취소
		myPageDAO.cancelfavorite(favoriteVO);
	}

	@Override
	public void insertfavorite(FavoriteVO favoriteVO) throws Exception {//좋아요 추가
		myPageDAO.insertfavorite(favoriteVO);
	}

	@Override
	public void deletemygallery(FavoriteVO favoriteVO) throws Exception {//내 게시글 삭제
		myPageDAO.deletemygallery(favoriteVO);
	}

	@Override
	public int selectOverlappedFavorite(FavoriteVO favoriteVO) throws Exception {//좋아요 확인
		return myPageDAO.selectOverlappedFavorite(favoriteVO);
	}

	@Override
	public Map count(String id) throws Exception {//받은좋아요와 작성한게시글수
		return myPageDAO.count(id);
	}
	
}
