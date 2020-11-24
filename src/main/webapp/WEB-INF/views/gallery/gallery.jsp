<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<!-- style.css로 링크 연결 -->
	<link rel="stylesheet" href="${contextPath }/resources/css/gallery.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"> 
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
	/* selectBox에서 선택한 값을 저장하는 변수 */
	var choice;
	
	/* selectBox에 값을 선택할 때 실행 */
	function setValues(){
		
		var target = document.getElementById("selectBox");
		
		/* selectBox에 텍스트 값을 저장 */
	    var select = target.options[target.selectedIndex].text;
	    
	    if(select == "선택"){
	    	choice = null;
	    }else if(select == "제목"){
	    	choice = "g_title";	    	
	    }else if(select == "카테고리"){
	    	choice = "g_category";	    	
	    }else if(select == "해시태그"){
	    	choice = "g_hashtag";
	    }else if(select == "아이디"){
	    	choice = "g_id";
	    }
	}

    /* 검색 버튼을 클릭하면 실행 */
	function filter(){
        var value, name, item, i, ctn;
		
        /* 검색창에 있는 값을 저장 */
        value = document.getElementById("keyword").value.toUpperCase();
        
        /* thumbnail을 저장 */
        item = document.getElementsByClassName("thumbnail");
        
        /* 카테고리 미선택,검색창에 값이 없을 때 */
       	if(choice == null){
       		alert("카테고리를 선택해주세요.")
       	}else if(value == null || value == ""){
       		alert("검색어를 입력해주세요.")
       	}
        //injection 방어
       	var expText = /[<>=%]/;
        if(value.match(expText)){
        	alert("특수문자를 입력할 순 없습니다")
        	return;
        }
        
        /* thumbnail에 갯수만큼 반복을 돌림 */
        for(i=0;i<item.length;i++){
        	
        	/* choice에 저장된 클래스에 값이 있는지 확인 */
        	name = item[i].getElementsByClassName(choice);
        	 	
        	
        	/* 값이 있으면 block 없으면 none으로 화면에 띄워준다 */
          	if(name[0].innerHTML.toUpperCase().indexOf(value) > -1){
           		item[i].style.display = "block";
           		ctn = 1;
          		}else{
            	item[i].style.display = "none";
          		}
      		}       
 
        /* 검색결과가 없을 때 */
	        if(ctn == undefined){
	        	alert("결과가 없습니다.")
	        	document.location.href="${contextPath }/gallery/gallery.do";
	        }   
        	
      	}	 
		
		$(function(){
			$(".thumbnail").addClass("nonActive");
			$(".nonActive").slice(0,6).show();
			$(".nonActive").slice(0,6).removeClass("nonActive");
			
			$("#load").click(function(e){
				if($(".nonActive").length <= 0){
					alert("갤러리가 더 이상 존재하지 않습니다.");
					return;
				}
				e.preventDefault();
				$(".nonActive").slice(0,6).removeClass("nonActive");
			});
			
		});
		
	</script>
	
	
	</head>
<body>

	<section>
		<!-- 검색창 -->
		<div class="search">
			<select id="selectBox" onchange="setValues()">
				<option>선택</option>
				<option>제목</option>
				<option>카테고리</option>
				<option>해시태그</option>
				<option>아이디</option>
			</select>
	   		<input type="text" placeholder="검색어를 입력하세요" id="keyword">
	   		<span class="searchBtn">
   			<i class="fa fa-search fa-2x" aria-hidden="true" onclick="filter()"></i>		  
   			</span> 			
		</div><!-- end search -->
		
			
		<div class="thumbnail-form">
			<!-- 갤러리 반복문 실행 -->
			<c:forEach var="galleryList" items="${galleryList }">	
					
				<!-- 좋아요 리스트 증감 변수 -->
				<c:set var="likeNum" value="${ likeNum + 1}"/>
						 				
				<!-- 갤러리 폼 -->
	     	 	<div class="thumbnail">
					<a class="gallery-form" href="${contextPath }/gallery_detail/galleryDetail.do?galleryNO=${galleryList.galleryNO}"> 
	      				<img class="gallery-img" src="${contextPath }/download.do?fileName=${galleryList.imageFileName}&id=${galleryList.galleryNO }" alt="이미지" >
					</a><!-- end gallery-form -->
					
	         		<div class="gallery-caption">			         		
		            	<p><span class="g_title">${galleryList.title}</span></p>
		            	<p>by. <span class="g_id">${galleryList.id}</span></p>
			        	<p><i class="fas fa-tag"></i><span class="g_category"> ${galleryList.category}</span></p>
			        	<p>#<span class="g_hashtag">${galleryList.hashtag}</span></p>			        				        
			        	<!-- 좋아요 값 출력 -->
				    	<p><span style="color:rgb(255, 87, 121)"><i class="fas fa-heart"></i></span> ${like.get(likeNum-1)}</p>
		        	</div><!-- end caption -->
		      	</div><!-- end thumnail -->
		      	
			</c:forEach>
		<div class="moreBtn">
			<button id="load">MORE</button>
		</div>	
		</div><!-- end thumnail-form -->
	</section><!-- end section -->	
</body>
</html>