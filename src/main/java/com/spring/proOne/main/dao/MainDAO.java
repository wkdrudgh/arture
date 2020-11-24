package com.spring.proOne.main.dao;

import java.util.List;

import com.spring.proOne.gallery.vo.GalleryVO;

public interface MainDAO {

	public List selectGalleryInfo() throws Exception;

	public List selectLike() throws Exception;
	
	public List likegallery() throws Exception;
}
