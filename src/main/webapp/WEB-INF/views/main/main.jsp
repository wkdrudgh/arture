<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
   request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main 페이지</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="${contextPath }/resources/js/main.js"></script>
<script src="${contextPath }/resources/js/jquery.scrollify.js"></script>

<link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">

<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"> 
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link rel="stylesheet" href="${contextPath}/resources/css/reset.css" />

</head>
<script>

$('#myModal').on('shown.bs.modal', function () {
	  $('#myInput').trigger('focus')
})

function btn1(){
	var b1 = document.getElementById("mbtn1");
	b1.click();
}
function btn2(){
	var b2 = document.getElementById("mbtn2");
	b2.click();
}
function auto_grow(element) {
    element.style.height = "5px";
    element.style.height = (element.scrollHeight)+"px";
}

</script>
<style>


</style>
<script>
   
</script>
<!-- overflow:hidden. 이미지 크기가 최대를 넘어서면 스크롤바를 자동으로 생성하지 않는다 -->
<body style="overflow: hidden;">
	<c:if test="${galleryList.size()==0 }">
	<section class="panel home" data-section-name="home">
		<div class="inner">
			<div class="vertical-center">
				<div>
					<p>승인된 갤러리가 존재하지 않습니다.</p>
				</div>
			</div>
		</div>
	</section>
	</c:if>
	<c:if test="${galleryList.size()>=1 }">
	<section class="panel home" data-section-name="home">
		<div class="inner">
			<img
				src="${contextPath }/imageList.do?galleryNO=${galleryList.get(0).galleryNO }&imageFileName=${galleryList.get(0).imageFileName }">
			<div class="vertical-center">
				<div>
					<p>${galleryList.get(0).title }</p>
					<!-- 여기에 갤러리로 이동할 경로 잡아야함. -->
					<a href="${contextPath }/gallery_detail/galleryDetail.do?galleryNO=${galleryList.get(0).galleryNO }" class="arrow">VIEW</a>
				</div>
			</div>
		</div>
	</section>
	</c:if>
	<c:if test="${galleryList.size()>=2 }">
	<section class="panel panel1" data-section-name="second">
		<div class="inner">
			<img
				src="${contextPath }/imageList.do?galleryNO=${galleryList.get(1).galleryNO }&imageFileName=${galleryList.get(1).imageFileName }">
			<div class="vertical-center">
				<div>
				<p>${galleryList.get(1).title }</p>
					<!-- 여기에 갤러리로 이동할 경로 잡아야함. -->
					<a href="${contextPath }/gallery_detail/galleryDetail.do?galleryNO=${galleryList.get(1).galleryNO }" class="arrow">VIEW</a>
				</div>
			</div>
		</div>
	</section>
	</c:if>
	<c:if test="${galleryList.size()>=3 }">
	<section class="panel panel2" data-section-name="third">
		<div class="inner">
			<img src="${contextPath }/imageList.do?galleryNO=${galleryList.get(2).galleryNO }&imageFileName=${galleryList.get(2).imageFileName }">

			<div class="vertical-center">
				<div>
				<p>${galleryList.get(2).title }</p>
					<!-- 여기에 갤러리로 이동할 경로 잡아야함. -->
					<a href="${contextPath }/gallery_detail/galleryDetail.do?galleryNO=${galleryList.get(2).galleryNO }" class="arrow">VIEW</a>
				</div>
			</div>
		</div>
	</section>
	</c:if>
	
	<section class="panel panel3" data-section-name="fourth">
		<div class="inner">
				<div class="left-box">
					<p class="word">TOP-RATED</p>
					
						<!-- 갤러리 폼 -->
			     	 	<div class="thumbnail">
							<a class="gallery-form" href="${contextPath }/gallery_detail/galleryDetail.do?galleryNO=${galleryLike1.galleryNO }"> 
			      				<img class="gallery-img" src="${contextPath }/imageList.do?galleryNO=${galleryLike1.galleryNO }&imageFileName=${galleryLike1.imageFileName }" alt="이미지" >
							</a><!-- end gallery-form -->
							
			         		<div class="gallery-caption">			         		
				            	<p class="g_title">${galleryLike1.title}</p>
				            	<p>by. <span>${galleryLike1.id}</span></p>
					        	<%-- <p>카테고리:<span>${galleryLike.category}</span></p>
					        	<p>해시태그:<span>${galleryLike.hashtag}</span></p>		 --%>	        				        
					        	<!-- 좋아요 값 출력 -->
						    	<p class="like_icon"><span style="color:red"><i class="fas fa-heart"></i></span> ${like1}</p>
				        	</div><!-- end caption -->
				      	</div><!-- end thumnail -->
				      	
				      	<div class="thumbnail">
							<a class="gallery-form" href="${contextPath }/gallery_detail/galleryDetail.do?galleryNO=${galleryLike2.galleryNO }"> 
			      				<img class="gallery-img" src="${contextPath }/imageList.do?galleryNO=${galleryLike2.galleryNO }&imageFileName=${galleryLike2.imageFileName }" alt="이미지" >
							</a><!-- end gallery-form -->
							
			         		<div class="gallery-caption">			         		
				            	<p class="g_title">${galleryLike2.title}</p>
				            	<p>by. <span>${galleryLike2.id}</span></p>		        				        
					        	<!-- 좋아요 값 출력 -->
						    	<p class="like_icon"><span style="color:red"><i class="fas fa-heart"></i></span> ${like2}</p>
				        	</div><!-- end caption -->
				      	</div><!-- end thumnail -->
	 
				</div>
				
				
					

	
	
				<div class="right-box">
					<p class="word">NOTICE</p>
					<!-- 공지사항에 글 없을시 공지사항이 없음 텍스트 출력 -->
					<!-- c tag 안에 주석달면 오류오류!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
					
					<c:choose>
					<c:when test="${noticeList == NULL || noticeList.size() == 0 }">
					<ul class="notice_list">
						<li>
								<button id="mbtn1" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter1" style="display:none"></button>
								<div class="noticeDiv1" onclick="btn1()">
									<p onclick="btn1()" class="n_title">-</p>
									<p onclick="btn1()" class="n_content">최근 공지사항이 존재하지 않습니다.</p>
								</div>
								
						</li>
						<li>
								<button id="mbtn2" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter2" style="display:none"></button>
								<div class="noticeDiv2" onclick="btn2()">
									<p class="n_title">-</p>
									<p class="n_content">최근 공지사항이 존재하지 않습니다.</p>
								</div>
								
							</li>
					</ul>		
					</c:when>

					<c:when test="${noticeList != null && noticeList.size() > 0 }">
						<!-- Modal 1 -->
						<div class="modal fade" id="exampleModalCenter1" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
						  <div class="modal-dialog modal-dialog-centered" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLongTitle">${noticeList.get(0).title}</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						      	<textarea class="autosize" onkeydown="resize(this)" onkeyup="resize(this)" readonly disabled >${noticeList.get(0).content}</textarea>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						      </div>
						    </div>
						  </div>
						</div>
						<ul class="notice_list">
							<li>
								<button id="mbtn1" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter1" style="display:none"></button>
								<div class="noticeDiv1" onclick="btn1()">
									<p onclick="btn1()" class="n_title">${noticeList.get(0).title}</p>
									<p onclick="btn1()" class="n_content">${noticeList.get(0).content}</p>
								</div>
								<p class="n_date">${noticeList.get(0).writeDate }</p>
							</li>
							<c:if test="${noticeList != null && noticeList.size() == 1 }">
							<li>
								<button id="mbtn2" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter2" style="display:none"></button>
								<div class="noticeDiv2" onclick="btn2()">
									<p class="n_title">-</p>
									<p class="n_content">최근 공지사항이 존재하지 않습니다.</p>
								</div>
								
							</li>
							</c:if>
							<c:if test="${noticeList != null && noticeList.size() > 1 }">
								<!-- Modal 2 -->
						<div class="modal fade" id="exampleModalCenter2" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
						  <div class="modal-dialog modal-dialog-centered" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLongTitle">${noticeList.get(1).title}</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						      	<textarea class="autosize" onkeydown="resize(this)" onkeyup="resize(this)" readonly disabled >${noticeList.get(1).content}</textarea>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						      </div>
						    </div>
						  </div>
						</div>
							<li>
								<button id="mbtn2" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter2" style="display:none"></button>
								<div class="noticeDiv2" onclick="btn2()">
									<p class="n_title">${noticeList.get(1).title}</p>
									<p class="n_content">${noticeList.get(1).content}</p>
								</div>
								<p class="n_date">${noticeList.get(1).writeDate }</p>
							</li>
							</c:if>
						</ul>
					</c:when>
					</c:choose>
				</div><!-- 공지사항 -->
				<div id="mainFooter">
				<ul class="f_sitemap">
					<li><a href="${contextPath }/main/main.do">MAIN</a></li>
					<li><a href="${contextPath }/gallery/gallery.do">GALLERY</a></li>
					<li><a href="${contextPath }/applyForm/applyForm.do">CONTACT US</a></li>
				</ul>
				<p class="f_copy">COPYRIGHT © 2020 ARTURE. ALL RIGHTS RESERVED.</p>
				
			</div><!-- 공지사항, comming soon wrap -->
			
		</div><!-- inner 박스 -->
	
	</section>
	
</body>
</html>