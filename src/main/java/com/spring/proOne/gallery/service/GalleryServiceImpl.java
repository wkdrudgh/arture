package com.spring.proOne.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.proOne.gallery.dao.GalleryDAO;
import com.spring.proOne.gallery.vo.GalleryVO;

@Service("galleryService")
@Transactional(propagation = Propagation.REQUIRED)
public class GalleryServiceImpl implements GalleryService {
	@Autowired
	private GalleryDAO galleryDAO;
	
	//galleryDAO에서 값을 받아와서 리스트에 저정
	@Override
	public List listGallerys() throws DataAccessException {
		List galleryList = null;
		galleryList = galleryDAO.selectGallery();
		return galleryList;
	}
	
	public int like(int galleryNO) throws DataAccessException {
		return galleryDAO.selectlike(galleryNO);
	}

	@Override
	public GalleryVO selectDetailGallery(int num) throws DataAccessException {
		// TODO Auto-generated method stub
		return galleryDAO.selectDetailGallery(num);
	}

}
