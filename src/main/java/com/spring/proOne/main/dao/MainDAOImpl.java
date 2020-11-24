package com.spring.proOne.main.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.gallery.vo.ImageVO;
import com.spring.proOne.main.vo.MainVO;

@Repository("mainDAO")
public class MainDAOImpl implements MainDAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List selectGalleryInfo() throws Exception {
		List galleryList = sqlSession.selectList("mapper.main.selectGalleryInfo");
		return galleryList;
	}
	
	@Override
	public List selectLike() throws Exception {
		List galleryLike = sqlSession.selectList("mapper.main.selectLike");
		return galleryLike;
	}

	@Override
	public List<MainVO> likegallery() throws Exception {
		/*List<MainVO> likegallery = sqlSession.selectList("mapper.main.likegallery");
		
		 * int v = likegallery.get(0).getGalleryNO(); int l =
		 * likegallery.get(0).getGalleryLike();
		 * System.out.println(v+"asdasdasdasdasdasdasd"+l); //가장 좋아요를 많이받은 게시물 가져오기
		 * List<GalleryVO> a = sqlSession.selectList("mapper.gallery.likegallery",v);
		 * 
		 * List all = new ArrayList(); all.add(a); all.add(l);
		 */

		return sqlSession.selectList("mapper.main.likegallery");
	}
	
	
}
