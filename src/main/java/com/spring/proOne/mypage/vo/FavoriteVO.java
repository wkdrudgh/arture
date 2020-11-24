package com.spring.proOne.mypage.vo;

import org.springframework.stereotype.Component;

@Component("favoriteVO")
public class FavoriteVO {
	private String id;
	private int galleryNO;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getGalleryNO() {
		return galleryNO;
	}

	public void setGalleryNO(int galleryNO) {
		this.galleryNO = galleryNO;
	}


	
	

}
