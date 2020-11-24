<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
 
<%
   request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <link rel="stylesheet" href="${contextPath}/resources/css/applyform.css"/>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
   <title>applyForm 페이지</title>
   <script src="http://code.jquery.com/jquery-latest.js"></script>
   <title>applyForm 페이지</title>
</head>
<body>
   <div class="applyContent">
   	<p class="a_title">Apply Form</p>
   	<ul>
	   	<li><i class="fas fa-plus-circle"></i> 갤러리에 등록할 정보를 입력해주세요.</li>
	   	<li><i class="fas fa-check-circle"></i> 관리자의 승인을 거쳐 작품이 홈페이지에 등록됩니다.</li>
	   	<li><i class="fas fa-info-circle"></i> 마이페이지에서 sns,자기소개 등록하여 본인을 홍보할 수 있습니다.</li>
   	</ul>
   </div>
   <div class="applyForm">
   <form name="apply_form" method="post" action="${contextPath}/applyForm/addNewArticle.do" enctype="multipart/form-data" accept-charset="UTF-8">
      <table>
         <tr>
            <td class="td1">작성자</td>
            <td class="td2"><input  name="id" type="text"maxlength="100" value="${member.name }" readonly /></td>
         </tr>
         <tr>
            <td class="td1">제목</td>
            <td class="td2"><input  name="title" type="text" maxlength="150" required/></td>
         </tr>
         <tr>
            <td class="td1">컨텐츠</td>
            <td class="td2"><textarea name="content" maxlength="4000" required></textarea></td>
         </tr>
         <tr>
            <td class="td1">HashTag</td>
            <td class="td2"><input name="hashtag" type="text" maxlength="150" required/></td>
         </tr>
         <tr>
         	<td class="td1">Category</td>
            <td class="td2">
            <select id="category" name="category" required>
               <option value="">카테고리 선택</option>
                  <option value="순수 미술">순수 미술</option>
                  <option value="디자인">디자인</option>
                  <option value="공예">공예</option>
                  <option value="사진">사진</option>
                  <option value="패션">패션</option>
                  <option value="기타">기타</option>
            </select>
            </td>
         </tr>
         <tr>
         	<td class="td1">파일 업로드</td>
            <td class="td2"><input multiple="multiple" type="file" name="file" accept="image/png,image/jpg,image/jpeg" required/></td>
         </tr>
         <tr>
         	<td colspan="2">
            <input class="submitBtn" type="submit" value="APPLY" />
            </td>
         </tr>

      </table>
   
   </form>
   </div>
   
</body>
</html>