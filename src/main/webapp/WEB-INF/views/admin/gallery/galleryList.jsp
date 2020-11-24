<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath }/resources/css/reset.css">
<link rel="stylesheet" href="${contextPath }/resources/css/admin.css">
 <style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
  </style>
  <meta charset="UTF-8">
  <title>신청서 리스트창</title>
</head>


<script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
<script type="text/javascript">

function fn_goto_galleryView(url, galleryNO){	
    var form = document.createElement("form");
    var gallery = document.createElement("input");
    
    gallery.name="galleryNO";
    gallery.value= galleryNO;
    
    form.appendChild(gallery);
    document.body.appendChild(form); 
    
    form.method="post";
    form.action=url;
    form.submit();
	   
}


</script>
<body>
<h1 class="label">승인된 작품 관리</h1>
<table align="center" >
		<tr class="list_title">
			<td width="10%">NO.</td>
			<td width="70%">TITLE</td>
			<td width="20%">ID</td>
		</tr>
		<c:choose>
			<c:when test="${galleriesList == NULL || galleriesList.size() == 0 }">
				<tr height="10">
					<td colspan="3">
						<p align="center">
							<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${galleriesList !=null && galleriesList.size()>0}">
				<c:forEach var="gallery" items="${galleriesList }">
					<tr align="center">
						<td width="10%">${gallery.galleryNO}</td>
						<td align='left' width="55%">
							<span style="padding-right: 30px"></span>
							<a class='cls1 gul'	href="#" onclick="fn_goto_galleryView('${contextPath}/admin/gallery/galleryView.do', ${gallery.galleryNO})">${gallery.title }</a>
						</td>
						<td width="15%">${gallery.id }</td>				
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>

</body>
</html>