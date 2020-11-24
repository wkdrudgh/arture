package com.spring.proOne.member.dao;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.member.vo.MemberVO;

public interface MemberDAO {
	 // 회원가입을 통해 새 회원을 추가
	 public int insertMember(MemberVO memberVO) throws DataAccessException ;
	 // 아이디로 로그인
	 public MemberVO loginById(MemberVO memberVO);
	 // id중복확인
	 public String selectOverlappedID(String id) throws DataAccessException;
	 //id로 회원정보 가져오기
	 public MemberVO selectMemberById(String id) throws DataAccessException;
}
