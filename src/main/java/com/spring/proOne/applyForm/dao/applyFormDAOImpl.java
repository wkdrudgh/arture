package com.spring.proOne.applyForm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.ImageVO;

@Repository("applyFormDAO")
public class applyFormDAOImpl implements applyFormDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertNewArticle(Map<String, Object> articleMap) {
		sqlSession.insert("mapper.applyForm.insertNewArticle", articleMap);
		int applyNO = sqlSession.selectOne("mapper.applyForm.selectNewapplyNO");
		return applyNO;
	}

	

	@Override
	public void insertNewImage(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		
		int applyNO = sqlSession.selectOne("mapper.applyForm.selectNewapplyNO",articleMap.get("id"));
		
		for (int i=0; i< imageFileList.size();i++) {
			System.out.println("i : "+i);
			imageFileList.get(i).setApplyNO(applyNO);
		}
		
		for (ImageVO Ivo : imageFileList) {
			sqlSession.insert("mapper.applyForm.insertNewImage", Ivo);
		}
	}

}
