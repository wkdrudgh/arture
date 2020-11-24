package com.spring.proOne.member.service;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.member.vo.MemberVO;

public interface MemberService {
	 public int addMember(MemberVO memberVO) throws DataAccessException;
	 public MemberVO login(MemberVO memberVO) throws Exception;
	 public String overlapped(String id) throws Exception;
	 public MemberVO selectMemberById(String id) throws Exception;
}
