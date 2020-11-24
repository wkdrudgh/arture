package com.spring.proOne.admin.applycheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.proOne.admin.applycheck.dao.AdminApplycheckDAO;
import com.spring.proOne.admin.member.dao.AdminMemberDAO;
import com.spring.proOne.applyForm.vo.ArticleVO;
import com.spring.proOne.gallery.vo.ImageVO;
import com.spring.proOne.member.vo.MemberVO;

@Service("adminApplycheckService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminApplycheckServiceImpl implements AdminApplycheckService{
	@Autowired
	private AdminApplycheckDAO adminApplycheckDAO;
	
	@Override
	public List<ArticleVO> listApplyForms() throws Exception{
		List<ArticleVO> applysList = adminApplycheckDAO.selectAllList();
		return applysList;
	}

	@Override
	public ArticleVO getApplyForm(int applyNO) {
		ArticleVO getApply = adminApplycheckDAO.selectApplycation(applyNO);
		return getApply;
	}

	@Override
	public List<ImageVO> getImages(int applyNO) {
		List<ImageVO> getApplyImages = adminApplycheckDAO.selectApplyImages(applyNO);
		return getApplyImages;
	}
	
	@Override
	public int acceptApplyForm(int applyNO) {
		int result = adminApplycheckDAO.insertToGallery(applyNO);
		int result2 = adminApplycheckDAO.deleteApplyform(applyNO);
		return result;
	}
	
	@Override
	public int rejectApplyForm(int applyNO) {
		int result=0, result2=0;
		try {
			result = adminApplycheckDAO.deleteApplyform(applyNO);
		}catch (Exception e) {
			System.out.println("adminApplycheckService /// deleteApplyform 실패!!!!");
		}
		try {
			result2 = adminApplycheckDAO.deleteImageFile(applyNO);
		}catch (Exception e) {
			System.out.println("adminApplycheckService /// deleteImageFile 실패!!!!");
		}
		return result;
	}
}
