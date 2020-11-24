<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath }/resources/css/reset.css">
<link rel="stylesheet" href="${contextPath }/resources/css/admin.css">
<meta charset="UTF-8">
<title>신청서 검토</title>
<style>
	#tr_btn_modify{
       display:none;
     }
     
     #preview{
     	width: 70%;
     }
</style>
</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
<script type="text/javascript" >
function backToList(obj){
	obj.action="${contextPath}/admin/applycheck/applyList.do";
	obj.submit();
  }

function fn_reject_application(url, applyNO){
	var answer=confirm("거절하시겠습니까?");
	   if(answer==true){
	      var form = document.createElement("form");
	      var application = document.createElement("input");
	      
	      application.name="applyNO";
	      application.value= applyNO;
	      
	      form.appendChild(application);
	      document.body.appendChild(form); 
	      
	      form.method="post";
	      form.action=url;
	      form.submit();
	   }
}

function fn_accept_application(url, applyNO){
	var answer=confirm("승인하시겠습니까?");
	   if(answer==true){
	      var form = document.createElement("form");
	      var application = document.createElement("input");
	      
	      application.name="applyNO";
	      application.value= applyNO;
	      
	      form.appendChild(application);
	      document.body.appendChild(form); 
	      
	      form.method="post";
	      form.action=url;
	      form.submit();
	   }
}
</script>
<body>
	<h1 class="label">신청된 작품 관리</h1>
	<form name="frmArticle" method="post"  >
		<table  border=0  align="center">
  			<tr>
   				<td width=150 align="center">
      			신청 번호
   				</td>
   				<td >
    				<input class="put" type="text"  value="${application.applyNO }"  disabled />
   				</td>
  			</tr>
			<tr>
				<td width="150" align="center">
     			 제목 
   				</td>
   				<td>
    				<input class="put" type=text value="${application.title }"  name="title"  id="i_title" disabled />
   				</td>   
  			</tr>
  			<tr>
				<td width="150" align="center">
     			 작성자 
   				</td>
   				<td>
    				<input class="put" type=text value="${application.id }"  name="title"  id="i_title" disabled />
   				</td>   
  			</tr>
  			<tr>
   				<td width=150 align="center">
      			카테고리
   				</td>
   				<td >
    				<input class="put" type="text"  value="${application.category }"  disabled />
   				</td>
  			</tr>
  			<tr>
   				<td width=150 align="center">
      			해시태그
   				</td>
   				<td >
    				<input class="put" type="text"  value="#${application.hashtag }"  disabled />
   				</td>
  			</tr>
  			<tr>
    			<td width="150" align="center">
     			 내용
   				</td>
   				<td>
    				<textarea class="put" rows="20" cols="60"  name="content"  id="i_content"  disabled />${application.content }</textarea>
    				
   				</td>  
  			</tr>
 

  			<tr>
	   			<td width="150" align="center">
	      		등록일자
			   	</td>
			   	<td>
			   		<input class="put" type=text name="writeDate" value="${imagesList.get(0).regDate}" disabled />
			   	</td>   
  			</tr>
			<c:if test="${not empty imagesList && imagesList!='null' }">
				<c:forEach var="item" items="${imagesList}" varStatus="status">
					<tr>
						<td width="150" align="center">
							이미지${status.count }
						</td>
						<td>
							<input type="hidden" name="originalFileName" value="${item.imageFileName }" /> 
							<img class="put" src="${contextPath}/imageList.do?galleryNO=${application.applyNO}&imageFileName=${item.imageFileName}" id="preview" /><br>
						</td>
					</tr>
					
				</c:forEach>
			</c:if>

			<tr  id="tr_btn">
		   		<td colspan="2" align="center">
			      	<input type=button class="admin_btn" value="승인" onClick="fn_accept_application('${contextPath}/admin/applycheck/acceptApplyForm.do', ${application.applyNO })">
			      	<input type=button class="admin_btn" value="거절" onClick="fn_reject_application('${contextPath}/admin/applycheck/rejectApplyForm.do', ${application.applyNO })">
			    	<input type=button class="admin_btn" value="목록" onClick="backToList(this.form)">
		   		</td>
		  	</tr>
		</table>
	</form>
</body>
</html>