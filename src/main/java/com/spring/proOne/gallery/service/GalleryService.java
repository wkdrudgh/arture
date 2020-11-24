package com.spring.proOne.gallery.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.gallery.vo.GalleryVO;


public interface GalleryService {
	//갤러리 정보를 담을 리스트
	public List listGallerys()throws DataAccessException;


	public int like(int galleryNO)throws DataAccessException;
	
	public GalleryVO selectDetailGallery (int num)throws DataAccessException;

}
