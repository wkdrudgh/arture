package com.spring.proOne.main.service;

import java.util.List;

import com.spring.proOne.gallery.vo.GalleryVO;

public interface MainService {

	public List galleryList() throws Exception;

	List galleryLike() throws Exception;
	
	public List likegallery() throws Exception;
}
