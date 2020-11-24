package com.spring.proOne.gallery_detail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.gallery.vo.ImageVO;
import com.spring.proOne.gallery_detail.dao.GalleryDetailDAO;

@Service("galleryDetailService")

public class GalleryDetailServiceImpl implements GalleryDetailService{
	@Autowired
	GalleryDetailDAO detailDAO;

	@Override
	public GalleryVO viewGalleryDetail(int galleryNO) throws Exception {
		GalleryVO galleryVO = detailDAO.selectGallery(galleryNO);
		return galleryVO;
	}

	@Override
	public Map viewGalleryImages(int applyNO) throws Exception {
		Map galleryMap = new HashMap();
		List<ImageVO> imageFileList = detailDAO.selectImageFileList(applyNO);
		galleryMap.put("imageFileList",imageFileList);
		return galleryMap;
	}

}
