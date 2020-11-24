package com.spring.proOne.gallery.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.proOne.gallery.vo.GalleryVO;


public interface GalleryDAO {
	//갤러리 정보를 받을 리스트
	public List selectGallery()throws DataAccessException;
	
	//디테일 정보를 받을 리스트
	public GalleryVO selectDetailGallery(int num)throws DataAccessException;

	public int selectlike(int galleryNO) throws DataAccessException;

}
