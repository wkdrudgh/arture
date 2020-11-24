package com.spring.proOne.admin.gallery.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.gallery.vo.ImageVO;
import com.spring.proOne.member.vo.MemberVO;

public interface AdminGalleryService {

	List<GalleryVO> listGalleries() throws Exception;

	GalleryVO getGallery(int galleyNO);

	int removeGallery(int galleryNO);

	

}
