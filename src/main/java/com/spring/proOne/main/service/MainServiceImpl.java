package com.spring.proOne.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.proOne.gallery.vo.GalleryVO;
import com.spring.proOne.main.dao.MainDAO;
import com.spring.proOne.main.vo.MainVO;

@Service("mainService")
public class MainServiceImpl implements MainService {
	@Autowired
	MainDAO mainDAO;
	
	@Override
	public List galleryList() throws Exception {
		
		List<GalleryVO> galleryList = mainDAO.selectGalleryInfo();
		System.out.println("service List : " + galleryList); 
		
		List<GalleryVO> selectedGalleries = new ArrayList<GalleryVO>();
		
		int cnt=0;
		for (GalleryVO gvo : galleryList ) {
			System.out.println("gvo : "+gvo);
			System.out.println("galleryList : "+galleryList);
			cnt++;
			selectedGalleries.add(gvo);
			System.out.println("selectedGalleries : "+selectedGalleries);
			if(cnt==3)
				break;
		}
		
		return selectedGalleries;
	}
	
	@Override
	public List galleryLike() throws Exception {
		
		List<MainVO> galleryLike = mainDAO.selectLike();
		
		return galleryLike;
		
	}

	@Override
	public List likegallery() throws Exception {
		return mainDAO.likegallery();
	}

	
}
