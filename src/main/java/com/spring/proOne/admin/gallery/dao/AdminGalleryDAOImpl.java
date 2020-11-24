package com.spring.proOne.admin.gallery.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.gallery.vo.ImageVO;

@Repository("adminGalleryDAO")
public class AdminGalleryDAOImpl implements AdminGalleryDAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<GalleryVO> selectAllList() {
		List<GalleryVO> galleriesList = sqlSession.selectList("mapper.admin_gallery.selectAllLists");
		return galleriesList;
	}

	@Override
	public GalleryVO selectGallery(int galleyNO) {
		// TODO Auto-generated method stub
		GalleryVO getApply = sqlSession.selectOne("mapper.admin_gallery.selectGallery", galleyNO);
		return getApply;
	}

	@Override
	public int deleteGallery(int galleyNO) {
		int result = sqlSession.delete("mapper.admin_gallery.deleteGallery", galleyNO);
		System.out.println("gallery DAO deleteApplyForm 확인");
		return result;
	}

	
}
