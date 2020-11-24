package com.spring.proOne.admin.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.admin.notice.vo.NoticeVO;

public interface AdminNoticeService {

	List<NoticeVO> listNotieces() throws Exception;

	Map listNotieces(Map pagingMap) throws Exception;

	void addNotice(NoticeVO notice) throws Exception;

	void removeNotice(int no) throws DataAccessException;

	void modNotice(NoticeVO notice);

	NoticeVO viewNotice(int noticeNO);


}
