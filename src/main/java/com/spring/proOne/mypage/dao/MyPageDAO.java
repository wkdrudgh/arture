package com.spring.proOne.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.mypage.vo.FavoriteVO;
import com.spring.proOne.member.vo.MemberVO;
import com.spring.proOne.gallery.vo.GalleryVO;


public interface MyPageDAO {
	public MemberVO selectMyInfo(String id) throws DataAccessException;//내정보 불러오기
	public void updateMyInfo(Map myinfomap) throws DataAccessException;//내정보 수정
	public void updateProFileImage(Map myinfomap) throws DataAccessException;//프로필 이미지 수정
	public void deleteMyInfo(String id) throws DataAccessException;//회원탈퇴
	public void insertfavorite(FavoriteVO favoriteVO)throws DataAccessException;//좋아요추가
	public void deletefavorite(String id) throws DataAccessException;//좋아요삭제
	public void cancelfavorite(FavoriteVO favoriteVO);//좋아요취소
	public List<GalleryVO> myFavorite(String id) throws DataAccessException;//좋아요 누른게시글 가져오기
	public List<GalleryVO> myGallery(String id) throws DataAccessException;//내 게시글 불러오기
	public boolean confirmPassword(String id,String pwd)throws DataAccessException;//비밀번호확인
	public void deletemygallery(FavoriteVO favoriteVO)throws DataAccessException;//내 게시글 삭제
	public int selectOverlappedFavorite(FavoriteVO favoriteVO)throws DataAccessException;//좋아요 중복확인
	public Map count(String id)throws DataAccessException;//작성한 게시글과 받은좋아요 수 가져오기
}
