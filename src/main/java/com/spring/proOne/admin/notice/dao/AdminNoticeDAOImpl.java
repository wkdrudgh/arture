package com.spring.proOne.admin.notice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.proOne.admin.notice.vo.NoticeVO;

@Repository("adminNoticeDAO")
public class AdminNoticeDAOImpl implements AdminNoticeDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<NoticeVO> selectAllList() {
		List<NoticeVO> articlesList = sqlSession.selectList("mapper.admin_notice.selectAllNotices");
		return articlesList;
	}
	@Override
	public List<NoticeVO> selectAllList(Map pagingMap) {
		List<NoticeVO> articlesList = new ArrayList();
		articlesList = sqlSession.selectList("mapper.admin_notice.selectSomeNotices", pagingMap);
		
		return articlesList;
	}
	
	@Override
	public int insertNotice(NoticeVO notice) throws DataAccessException {
		int result = sqlSession.insert("mapper.admin_notice.insertNotice", notice);
		return result;
	}
	
	@Override
	public int deleteNotice(int no) throws DataAccessException {
		int result = sqlSession.delete("mapper.admin_notice.deleteNotice", no);
		return result;
	}

	@Override
	public int updateNotice(NoticeVO notice) {
		int result = sqlSession.update("mapper.admin_notice.updateNotice", notice);
		return result;
	}

	@Override
	public NoticeVO selectNotice(int noticeNO) {
		NoticeVO result = sqlSession.selectOne("mapper.admin_notice.selectNotice", noticeNO);
		return result;
	}
	@Override
	public int selectTotNotices() {
		int totNotices = sqlSession.selectOne("mapper.admin_notice.countNotices");
		return totNotices;
	}
}
