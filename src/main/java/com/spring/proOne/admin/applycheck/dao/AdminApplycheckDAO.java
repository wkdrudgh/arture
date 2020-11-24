package com.spring.proOne.admin.applycheck.dao;

import java.util.List;

import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.ImageVO;

public interface AdminApplycheckDAO {

	List<ArticleVO> selectAllList();

	ArticleVO selectApplycation(int applyNO);

	List<ImageVO> selectApplyImages(int applyNO);

	int insertToGallery(int applyNO);

	int deleteApplyform(int applyNO);

	int deleteImageFile(int applyNO);

	

}
