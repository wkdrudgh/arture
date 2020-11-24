package com.spring.proOne.applyForm.dao;

import java.util.List;
import java.util.Map;

import com.spring.proOne.applyForm.vo.ArticleVO;

public interface applyFormDAO {
	public int insertNewArticle(Map<String, Object> articleMap);
	public void insertNewImage(Map<String, Object> articleMap);
}
