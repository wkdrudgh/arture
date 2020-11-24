package com.spring.proOne.admin.gallery.dao;

import java.util.List;

import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.gallery.vo.ImageVO;

public interface AdminGalleryDAO {

	List<GalleryVO> selectAllList();

	GalleryVO selectGallery(int galleyNO);

	int deleteGallery(int galleryNO);

}
