package com.spring.proOne.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private String email;
	private String profileImage;
	private String profileText;
	private String sns_i;
	private String sns_f;
	private String sns_b;
	private Date joinDate;
	

	public MemberVO(String id, String pwd, String name, String gender, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.email = email;
	}
	
	public MemberVO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileText() {
		return profileText;
	}

	public void setProfileText(String profileText) {
		this.profileText = profileText;
	}

	public String getSns_i() {
		return sns_i;
	}

	public void setSns_i(String sns_i) {
		this.sns_i = sns_i;
	}

	public String getSns_f() {
		return sns_f;
	}

	public void setSns_f(String sns_f) {
		this.sns_f = sns_f;
	}

	public String getSns_b() {
		return sns_b;
	}

	public void setSns_b(String sns_b) {
		this.sns_b = sns_b;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	
	
}
