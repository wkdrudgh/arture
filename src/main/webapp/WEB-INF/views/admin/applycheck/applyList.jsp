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

function fn_goto_applyView(url, applyNO){
	
      var form = document.createElement("form");
      var apply = document.createElement("input");
      
      apply.name="applyNO";
      apply.value= applyNO;
      
      form.appendChild(apply);
      document.body.appendChild(form); 
      
      form.method="post";
      form.action=url;
      form.submit();
	   
}


</script>
<body>
<h1 class="label">신청된 작품 관리</h1>
<table>
		<tr class="list_title">
			<td width="10%">NO.</td>
			<td width="70%">TITLE</td>
			<td width="20%">ID</td>
		</tr>
		<c:choose>
			<c:when test="${applysList == NULL || applysList.size() == 0 }">
				<tr height="10">
					<td colspan="3">
						<p align="center">
							<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${applysList !=null && applysList.size()>0}">
				<c:forEach var="apply" items="${applysList }">
					<tr align="center">
						<td width="10%">${apply.applyNO}</td>
						<td align='left' width="55%">
							<span style="padding-right: 30px"></span> 
							<a class='cls1 gul'	href="#" onclick="fn_goto_applyView('${contextPath}/admin/applycheck/applyView.do', ${apply.applyNO })">${apply.title }</a>
						</td>
						<td width="15%">${apply.id }</td>				
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>

</body>
</html>