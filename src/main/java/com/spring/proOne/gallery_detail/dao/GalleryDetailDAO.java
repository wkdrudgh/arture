package com.spring.proOne.gallery_detail.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.gallery.vo.GalleryVO;

public interface GalleryDetailDAO {
	public GalleryVO selectGallery(int galleryNO) throws DataAccessException;
	public List selectImageFileList(int applyNO) throws DataAccessException;
}
