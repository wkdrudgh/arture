package com.spring.proOne.admin.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.proOne.admin.applycheck.dao.AdminApplycheckDAO;
import com.spring.proOne.admin.gallery.dao.AdminGalleryDAO;
import com.spring.proOne.admin.member.dao.AdminMemberDAO;
import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.gallery.vo.ImageVO;
import com.spring.proOne.member.vo.MemberVO;

@Service("adminGalleryService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminGalleryServiceImpl implements AdminGalleryService{
	@Autowired
	private AdminGalleryDAO adminGalleryDAO;
	@Autowired
	private AdminApplycheckDAO adminApplycheckDAO;
	
	
	@Override
	public List<GalleryVO> listGalleries() throws Exception{
		List<GalleryVO> galleriesList = adminGalleryDAO.selectAllList();
		return galleriesList;
	}

	@Override
	public GalleryVO getGallery(int galleyNO) {
		GalleryVO getGallery = adminGalleryDAO.selectGallery(galleyNO);
		return getGallery;
	}
	
	@Override
	public int removeGallery(int galleryNO) {
		int result=0, result2=0;
		try {
			result = adminGalleryDAO.deleteGallery(galleryNO);
		}catch (Exception e) {
			System.out.println("AdminGalleryService /// deleteGallery 실패!!!");
		}
		try {
			result2 = adminApplycheckDAO.deleteImageFile(galleryNO);
		}catch (Exception e) {
			System.out.println("AdminGalleryService /// deleteImageFile 실패!!!");
		}
		return result;
	}
}
