<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set  var="noticesList"  value="${listsMap.noticesList}" />
<c:set  var="totNotices"  value="${listsMap.totNotices}" />
<c:set  var="section"  value="${listsMap.section}" />
<c:set  var="pageNum"  value="${listsMap.pageNum}" />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${contextPath }/resources/css/reset.css">
	<link rel="stylesheet" href="${contextPath }/resources/css/admin.css">
	<style type="text/css">
		.no-uline {text-decoration:none; color:#b9b9b9;}
   		.sel-page{text-decoration:none; color:black;}
	</style>
	<meta charset="UTF-8">
	<title>공지사항 리스트창</title>
</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
<script type="text/javascript">

function fn_remove_notice(url, noticeNO){
	var answer=confirm("삭제하시겠습니까?");
	   if(answer==true){
		   fn_pass_data(url, noticeNO);
	   }
}

function fn_pass_data(url, noticeNO){
	var form = document.createElement("form");
    var notice = document.createElement("input");
    
    notice.name="noticeNO";
    notice.value= noticeNO;
    
    form.appendChild(notice);
    document.body.appendChild(form); 
    
    form.method="post";
    form.action=url;
    form.submit();
}

function fn_write() {
	var write = document.writeForm;
	write.method = "post";
	write.action = "${contextPath}/admin/notice/noticeForm.do";
	write.submit();
	
}
</script>
<body>
<h1 class="label">공지사항 관리</h1>
<table>
		<tr class="list_title">
			<td width="10%">NO</td>
			<td width="55%">TITLE</td>
			<td width="15%">Date Created</td>
			<td width="10%">Delete</td>
		</tr>
		<c:choose>
			<c:when test="${noticesList == NULL || noticesList.size() == 0 }">
				<tr height="10">
					<td colspan="5">
						<p align="center">
							<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${noticesList !=null && noticesList.size()>0}">
				<c:forEach var="notice" items="${noticesList }">
					<tr align="center">
						<td width="10%">${notice.no}</td>
						<td align='left' width="55%"><span
							style="padding-right: 30px"></span> <a class='cls1 gul'
							href="#" onclick="fn_pass_data('${contextPath}/admin/notice/noticeView.do', ${notice.no})">${notice.title }</a>
						</td>
						<td width="15%">${notice.writeDate }</td>
						<td width="10%"><input type=button class="admin_btn" value="삭제" onclick="fn_remove_notice('${contextPath}/admin/notice/removeNotice.do', ${notice.no})"></td>				
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>

	<div class="cls2">
 		<c:if test="${totNotices != null }" >
      		<c:choose>
        		<c:when test="${totNotices >100 }">  <!-- 글 개수가 100 초과인경우 -->
	      			<c:forEach   var="page" begin="1" end="10" step="1" >
		         		<c:if test="${section >1 && page==1 }">
		          			<a class="no-uline" href="${contextPath }/admin/notice/noticeList.do?section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp; pre </a>
		         		</c:if>
		          		<a class="no-uline" href="${contextPath }/admin/notice/noticeList.do?section=${section}&pageNum=${page}">${(section-1)*10 +page } </a>
		         		<c:if test="${page ==10 }">
		          			<a class="no-uline" href="${contextPath }/admin/notice/noticeList.do?section=${section+1}&pageNum=${section*10+1}">&nbsp; next</a>
		         		</c:if>
	      			</c:forEach>
        		</c:when>
       			<c:when test="${totNotices == 100 }" >  <!--등록된 글 개수가 100개인경우  -->
	      			<c:forEach   var="page" begin="1" end="10" step="1" >
	        			<a class="no-uline"  href="#">${page } </a>
	      			</c:forEach>
        		</c:when>
        
	        	<c:when test="${totNotices< 100 }" >   <!--등록된 글 개수가 100개 미만인 경우  -->
		      		<c:forEach   var="page" begin="1" end="${(totNotices-1)/10+1}" step="1" >
		         		<c:choose>
			           		<c:when test="${page==pageNum }">
			            		<a class="sel-page"  href="${contextPath}/admin/notice/noticeList.do?section=${section}&pageNum=${page}">${page } </a>
			          		</c:when>
			          		<c:otherwise>
			            		<a class="no-uline"  href="${contextPath }/admin/notice/noticeList.do?section=${section}&pageNum=${page}">${page } </a>
			          		</c:otherwise>
		        		</c:choose>
		      		</c:forEach>
	        	</c:when>
	      </c:choose>
    </c:if>
</div>    
	<div class="cls2">
		<form name="writeForm"><input type="button" class="admin_btn" onclick="fn_write()" value="글쓰기"></form>
	</div>
</body>
</html>