package com.spring.proOne.applyForm.vo;

import org.springframework.stereotype.Component;

@Component("articleVO")
public class ArticleVO {
	private int applyNO;
	private String title;
	private String content;
	private String imageFileName;
	private String category;
	private String hashtag;
	private String id;

	public int getApplyNO() {
		return applyNO;
	}

	public void setApplyNO(int applyNO) {
		this.applyNO = applyNO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
