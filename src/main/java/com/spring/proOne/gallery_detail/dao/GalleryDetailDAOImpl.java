package com.spring.proOne.gallery_detail.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.gallery.vo.ImageVO;

@Repository("galleryDetailDAO")
public class GalleryDetailDAOImpl implements GalleryDetailDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public GalleryVO selectGallery(int galleryNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.gallery.selectGallery",galleryNO);
	}

	@Override
	public List selectImageFileList(int applyNO) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.gallery.selectImageFileList",applyNO);
		return imageFileList;
	}

}
