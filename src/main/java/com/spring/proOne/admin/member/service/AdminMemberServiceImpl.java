package com.spring.proOne.admin.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.proOne.admin.member.dao.AdminMemberDAO;
import com.spring.proOne.member.vo.MemberVO;

@Service("adminMemberService")
public class AdminMemberServiceImpl implements AdminMemberService{
	@Autowired
	private AdminMemberDAO adminMemberDAO;
	
	@Override
	public List<MemberVO> listMembers() throws Exception{
		// TODO Auto-generated method stub
		List<MemberVO> membersList = adminMemberDAO.selectAllMember();
		return membersList;
	}
	
	@Override
	public void modInfo(MemberVO temp) throws Exception{
		// TODO Auto-generated method stub
		adminMemberDAO.modPwd(temp);
	}
	
	@Override
	public void removeMember(String id) throws DataAccessException {
		adminMemberDAO.deleteNotice(id);
	}
}
