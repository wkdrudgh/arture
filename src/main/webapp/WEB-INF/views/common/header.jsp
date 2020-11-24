<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/common.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<title>헤더</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(document).ready(function () {
	$(".menu-btn").click(function () {
		$(".menu-btn").toggleClass("active");
		$(".overlay").toggleClass("active");
		$(".menu-container").toggleClass("active");
	});
	
	/* const cursor = curDot();
	
	cursor.over(".line-1", {
		borderColor: "rgba(255,255,255,.38)",
		broderWidth: 2
	}); */
});
</script>
</head>
<body>
	<div id="headerWrapper">
 		<div class="menu">
			<section class="landing-page">
				<div class="menu-btn"><i class="fas fa-bars"></i></div>
				<div class="overlay"></div>
				<div class="menu-container">
					<ul class="menu-wrapper">
						<li><a href="${contextPath }/main/main.do">Main</a></li>
						<li><a href="${contextPath }/gallery/gallery.do">Gallery</a></li>
						<li><a href="${contextPath }/mypage/myPageMain.do">Profile</a></li>
						<li><a href="${contextPath }/applyForm/applyForm.do">Contact</a></li>
					</ul>
					<div class="menu-underlay"></div>
				</div>
			
			</section>
		</div>
		<div class="logo">
			<a href="${contextPath }/main/main.do">
				<img src="${contextPath}/resources/image/logo.png"/>
			</a>
		</div>
		<div class="member">
			<c:choose>
					<c:when test="${isLogOn == true && member != null }">
						<img id="profile" src="${contextPath}/downProfile.do?profileImage=${member.profileImage}&id=${member.id}"></img>
						<a href="${contextPath }/mypage/myPageMain.do"><span>${member.name}'s Profile</span></a>
						<a href="${contextPath }/member/logout.do">Log Out</a>
						<c:if test="${member.id == 'admin'}">
							<a href="${contextPath }/admin/notice/noticeList.do" class="admin">Admin</a>
						</c:if>
						<p class="profile"><p>
					</c:when>
					<c:otherwise>
					<a href="${contextPath }/member/loginForm.do">Log In</a>
					<a href="${contextPath }/member/memberForm.do">Join Us</a>
					</c:otherwise>
				</c:choose>
		</div>
	</div>
	<%-- <table border="0" width="100%">
		<tr>
			<td>
				<!-- 메뉴아이콘 들어갈 자리 -->
			</td>
			<td>
				<a href="${contextPath }/main/main.do">
					<img src="${contextPath}/resources/image/logo.png"/>
				</a>
			</td>
			<td>
				<c:choose>
					<c:when test="${isLogOn == true && member != null }">
						<h3>환영합니다!! ${member.name }님!</h3>
						<a href="${contextPath }/mypage/myPageMain.do?id=${member.id}"><h3>마이페이지</h3></a>
						<a href="${contextPath }/member/logout.do"><h3>로그아웃</h3></a>
						<c:if test="${member.id == 'admin'}">
							<a href="${contextPath }/admin/notice/noticeList.do"><h3>admin</h3></a>
						</c:if>
					</c:when>
					
					<c:otherwise>
						
						<a href="${contextPath }/member/loginForm.do"><h3>로그인</h3></a>
						<a href="${contextPath }/member/memberForm.do"><h3>회원가입</h3></a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table> --%>
</body>
</html>