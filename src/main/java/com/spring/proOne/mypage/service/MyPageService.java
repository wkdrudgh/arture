package com.spring.proOne.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.mypage.vo.FavoriteVO;
import com.spring.proOne.member.vo.MemberVO;
import com.spring.proOne.gallery.vo.GalleryVO;

public interface MyPageService {
	public MemberVO selectMyInfo(String id) throws Exception;//내정보 불러오기
	public void updateMyInfo(Map myinfomap)throws Exception;//내정보 수정
	public void updateProFileImage(Map myinfomap)throws Exception;//프로필 이미지 수정
	public List<GalleryVO> myFavorite(String id)throws Exception;//좋아요누른 게시글 가져오기
	public List<GalleryVO> myGallery(String id)throws Exception;//내 게시글 불러오기
	public boolean confirmPassword(String id,String pwd)throws Exception;//비밀번호 확인
	public void cancelfavorite(FavoriteVO favoriteVO)throws Exception;//좋아요취소 
	public void insertfavorite(FavoriteVO favoriteVO)throws Exception;//좋아요추가 
	public void deletemygallery(FavoriteVO favoriteVO) throws Exception;//내 게시글 삭제
	public int selectOverlappedFavorite(FavoriteVO favoriteVO)throws Exception; // 좋아요 중복확인
	public Map count(String id) throws Exception;
}
