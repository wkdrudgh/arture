package com.spring.proOne.admin.applycheck.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.ImageVO;
import com.spring.proOne.member.vo.MemberVO;

public interface AdminApplycheckService {

	List<ArticleVO> listApplyForms() throws Exception;

	ArticleVO getApplyForm(int applyNO);

	List<ImageVO> getImages(int applyNO);

	int acceptApplyForm(int applyNO);

	int rejectApplyForm(int applyNO);

	

}
