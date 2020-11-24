package com.spring.proOne.admin.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.proOne.member.vo.MemberVO;

public interface AdminMemberDAO {

	List<MemberVO> selectAllMember();

	int modPwd(MemberVO temp);

	void deleteNotice(String id) throws DataAccessException;

}
