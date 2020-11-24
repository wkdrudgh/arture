package com.spring.proOne.admin.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.proOne.admin.notice.dao.AdminNoticeDAO;
import com.spring.proOne.admin.notice.vo.NoticeVO;

@Service("adminNoticeService")
public class AdminNoticeServiceImpl implements AdminNoticeService{
	@Autowired
	AdminNoticeDAO noticeDAO;

	@Override
	public List<NoticeVO> listNotieces() throws Exception{
		List<NoticeVO> noticesList = noticeDAO.selectAllList();
		return noticesList;
	}
	
	@Override
	public Map listNotieces(Map pagingMap) throws Exception{
		// TODO Auto-generated method stub
		Map<String, Object> listsMap = new HashMap<String, Object>();
		// 게시글 10개에 대한 정보들을 가져옴
		List<NoticeVO> noticesList = noticeDAO.selectAllList(pagingMap);
		// 게시글의 총 개수를 가져옴
		int totNotices = noticeDAO.selectTotNotices();
		// 가져온 정보들을 put
		listsMap.put("noticesList", noticesList);
		listsMap.put("totNotices", totNotices);
		listsMap.put("section", pagingMap.get("section"));
		listsMap.put("pageNum", pagingMap.get("pageNum"));
		return listsMap;
	}
	
	@Override
	public void addNotice(NoticeVO notice) throws Exception{
		noticeDAO.insertNotice(notice);
	}
	
	@Override
	public void removeNotice(int no) throws DataAccessException {
		noticeDAO.deleteNotice(no);
	}

	@Override
	public void modNotice(NoticeVO notice) {
		noticeDAO.updateNotice(notice);
	}

	@Override
	public NoticeVO viewNotice(int noticeNO) {
		NoticeVO noticeVO = noticeDAO.selectNotice(noticeNO);
		return noticeVO;
	}
}
