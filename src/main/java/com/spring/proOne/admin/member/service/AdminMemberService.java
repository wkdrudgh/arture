package com.spring.proOne.admin.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.member.vo.MemberVO;

public interface AdminMemberService {

	List<MemberVO> listMembers() throws Exception;

	void modInfo(MemberVO temp) throws Exception;

	void removeMember(String id) throws DataAccessException;

}
