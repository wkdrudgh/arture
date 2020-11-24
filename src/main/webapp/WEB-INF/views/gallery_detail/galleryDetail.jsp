<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- 
<c:set var="gallery"  value="${galleryMap.gallery}"  />
 --%>
<c:set var="imageFileList"  value="${galleryMap.imageFileList}"  />


<%
  request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- link -->

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link rel="stylesheet" href="${contextPath}/resources/css/reset.css"/>
<link rel="stylesheet" href="${contextPath}/resources/css/gallery_detail.css"/>
<!-- script -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

     function backToList(){
    	var form = document.createElement("form");
    	document.body.appendChild(form);
    	form.action="${contextPath}/gallery/gallery.do";
    	form.submit();
     }

	 function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 }  
	 

 	function fn_favorite(_id,_galleryNO){
 		
 	    $.ajax({
 	       type:"post",
 	       async:false,  
 	       url:"${contextPath}/mypage/addFavorite.do",
 	       dataType:"text",
 	       data: {id:_id,
 	    	      galleryNO:_galleryNO},
 	       success:function (data,textStatus){
 	          if(data=="true"){
 	       	    alert("좋아요 리스트에 추가하였습니다.");
 	       	    $('#heartBtn').css('color','#ff5779');
 	       	   
 	          }else{
 	        	$('#heartBtn').css('color','#fff');
 	        	  alert("취소완료 하였습니다.");
 	          }
 	       },
 	       error:function(data,textStatus){
 	          alert("에러가 발생했습니다.");
 	       },
 	       complete:function(data,textStatus){
 	          //alert("작업을완료 했습니다");
 	       }
 	    });  //end ajax	 
 	 }	
 	function selectFavorite(_id,_galleryNO){
 		 $.ajax({
 	 	       type:"post",
 	 	       async:false,  
 	 	       url:"${contextPath}/mypage/selectOverlappedFavorite.do",
 	 	       dataType:"text",
 	 	       data: {id:_id,
 	 	    	      galleryNO:_galleryNO},
 	 	       success:function (data,textStatus){
 	 	          if(data=="true"){
 	 	       	    $('#heartBtn').css('color','#ff5779');
 	 	       	    $('#addFavorite').prop("disabled", true);
 	 	          }
 	 	       },
 	 	       error:function(data,textStatus){
 	 	          alert("에러가 발생했습니다.");
 	 	       },
 	 	       complete:function(data,textStatus){
 	 	          //alert("작업을완료 했습니다");
 	 	       }
 	 	    });  //end ajax	 
 	}
 	function push(){
 		var addFavorite = document.getElementById("addFavorite");
 		addFavorite.click();
 	}
 	function back(){
 		var backToListBtn = document.getElementById("backToListBtn");
 		backToListBtn.click();
 	}
 </script>

</head>
<body onload="selectFavorite('${member.id}','${gallery.galleryNO}')">

	<div id="detailWrapper">
		<div class="gallery_info">
			<p class="gd_category">${gallery.category }</p>
			<p class="gd_title">${gallery.title }</p>
			
			<p class="gd_content">${gallery.content }</p>
			
			<p class="gd_hashtag">#${gallery.hashtag }</p>
			 
		</div>
		
		<div id="imageWrapper">
			<c:if test="${not empty imageFileList && imageFileList!='null' }">
			  <c:forEach var="item" items="${imageFileList}" varStatus="status" >
			  	<div>
			  		<input  type= "hidden"   name="originalFileName" value="${item.imageFileName }" />
			  		<img src="${contextPath}/imageList.do?galleryNO=${gallery.galleryNO}&imageFileName=${item.imageFileName}" id="preview"  />
			  	</div>
				</c:forEach>
			 </c:if>
		</div>
		
		<div id="buttonWrapper">
		 <c:if test="${not empty isLogOn && isLogOn !='null'}">
		 
		 <i id="heartBtn" class="fas fa-heart" onClick="fn_favorite('${member.id}','${gallery.galleryNO}')"></i>
		</c:if>
		
		<i class="fas fa-undo" onClick="backToList()"></i>
		</div>
		
	</div>
	
	<div class="card" tabindex="0">
	  <span class="card__infoicon">
	    <i class="fa fa-info"></i>
	  </span>
	  <div class="card_image">
	  	<img src="${contextPath}/downProfile.do?profileImage=${artist.profileImage}&id=${artist.id }"></img>
	  </div>
	  <div class="card_profile">
	  	<h1 class="card__title"><span>Artist </span>${artist.name}</h1>
	  	<p class="card__description">${artist.profileText}</p>
	  	<c:if test="${not empty artist.sns_i && artist.sns_i !='null' }">
		<p><img src="${contextPath}/resources/image/insta_b.png"/><a href="https://www.instagram.com/${artist.sns_i}" target="_blank"><span class="sns_id"> ${artist.sns_i}</span></a></p>
		</c:if>
		<c:if test="${not empty artist.sns_f && artist.sns_f !='null' }">
		<p><img src="${contextPath}/resources/image/facebook_b.png"/><a href="https://www.facebook.com/${artist.sns_f}" target="_blank"><span class="sns_id"> ${artist.sns_f}</span></a></p>
		</c:if>
		<c:if test="${not empty artist.sns_b && artist.sns_b !='null' }">
		<p><img src="${contextPath}/resources/image/blog_b.png"/><a href="https://blog.naver.com/${artist.sns_b}" target="_blank"><span class="sns_id"> ${artist.sns_b}</span></a></p>
		</c:if>
	  </div>
	</div>

</body>
</html>