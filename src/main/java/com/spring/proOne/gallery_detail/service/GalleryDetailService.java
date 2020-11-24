package com.spring.proOne.gallery_detail.service;



import java.util.Map;

import com.spring.proOne.gallery.vo.GalleryVO;

public interface GalleryDetailService {
	public GalleryVO viewGalleryDetail(int galleryNO) throws Exception;
	public Map viewGalleryImages(int applyNO) throws Exception;
}
