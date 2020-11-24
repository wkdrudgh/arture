<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
 <style>
 	.m_title{
 		padding:20px 0 ;
 		font-size:20px;
 		font-weight:bold;
 	}
  
   a.menu1{
   	  line-height:35px;
   	  font-size:17px;
   	  text-align:left;
      text-decoration:none;
      color:#000;
      margin-left:10px;
   }
   .admin_nav{
   		padding-left: 25%;
   }
   
   .list_nav{
   		text-align:left;
   }
 </style>
  <meta charset="UTF-8">
  <title>사이드 메뉴</title>
</head>
<body>
	<h1 class="m_title">MANAGE</h1>

	<div class="admin_nav">
		<div class="list_nav">
			<i class="fas fa-clipboard-check"></i><a href="${contextPath}/admin/notice/noticeList.do" class="menu1">공지사항</a><br> 	
		</div>
		<div class="list_nav">
			<i class="fas fa-user"></i><a href="${contextPath}/admin/member/memberList.do" class="menu1">회원 관리</a><br> 		
		</div>
		<div class="list_nav">
			<i class="fas fa-palette"></i><a href="${contextPath}/admin/applycheck/applyList.do" class="menu1">신청된 작품</a><br>
		</div>
		<div class="list_nav">
			<i class="fas fa-tasks"></i><a href="${contextPath}/admin/gallery/galleryList.do" class="menu1">승인된 작품</a><br>
		</div>
	</div>
	
	
</body>
</html>