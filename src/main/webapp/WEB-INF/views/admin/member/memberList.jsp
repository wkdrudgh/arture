<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath }/resources/css/reset.css">
<link rel="stylesheet" href="${contextPath }/resources/css/admin.css">
<style type="text/css">
	.td_detail{
	    background: #fafafa;
	}
	
	

</style>
<meta charset="UTF-8">
<title>회원 리스트</title>

</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
<script type="text/javascript" >
$(function () {
		
	$('#contents').find('.tr_detail').hide();
		
	/* 버튼 클릭시 버튼 바꿔주고 상세창 표시, 닫기 */
	$('#contents .btn_detail').click(function(){ 
		let tr = $('#contents tr');
		let rindex = $(this).parent().parent().index()+1;
		
			
		if($(this).val() == '+'){
			$(this).val('-');
			let where;
			if($(this).attr('gender')=='남'){
				where = '.div_' + $(this).attr('id') + ' .male';
			}else{
				where = '.div_' + $(this).attr('id') + ' .female';
			}
			$(where).attr("checked", true);
			
			let where_i = '.div_' + $(this).attr('id')+' .sns_i';
			let where_f = '.div_' + $(this).attr('id')+' .sns_f';
			let where_b = '.div_' + $(this).attr('id')+' .sns_b';
			
			if($(where_i).val() != ''){
				$(where_i).attr("checked", true);
			}
			if($(where_f).val() != ''){
				$(where_f).attr("checked", true);
			}
			if($(where_b).val() != ''){
				$(where_b).attr("checked", true);
			}
			
			$(tr[rindex]).show(); 
				
		}else{
			$(this).val('+');
			
			$(tr[rindex]).hide(); 
		}
	});
});
function fn_modify(ID){
	/* id값 추출 하려면 ID.id로 사용해야함 */
	let where = '.div_' + ID.id;
	let where_mod = where + ' .btn_mod';
	let where_del = where + ' .btn_delete_cancel';
	
	if($(where_mod).val() == '수정'){
		$(where+' .put_pwd').attr("disabled", false);
		$(where_mod).val("수정 완료");
		$(where_del).val("취소");
	}
	else if($(where_mod).val() == '수정 완료'){
		/* 변경된 비밀번호를 ajax로 변경할 것 */
		console.log($(where +' .put_pwd').val())
		var form = {
			id: ID.id,
			pwd : $(where +' .put_pwd').val()
		};
		$.ajax({
			url: "${contextPath}/admin/member/mod_pwd.do",
			type: "POST",
			data: form,
			success: function(){
				alert("수정 완료!!");
			}, error: function(){
				alert("수정하는데 오류가 발생하였습니다.");
			}
		});
		
		$(where+' .put_pwd').attr("disabled", true);
		$(where_mod).val("수정");
		$(where_del).val("삭제");
	}
	console.log($(where_mod).val());
	console.log($(where_del).val());
	
}
function fn_remove(ID){
	console.log(ID.id);
	console.log('.div_',ID.id,' .btn_mod');
	let where = '.div_' + ID.id;
	let where_mod = where + ' .btn_mod';
	let where_del = where + ' .btn_delete_cancel';
	
	
	console.log($(where_mod).val());
	console.log($(where_del).val());
	if($(where_del).val() == '삭제'){
		var answer=confirm("삭제하시겠습니까?");
		   if(answer==true){
		      let url = '${contextPath}/admin/member/removeMember.do';
		      var form = document.createElement("form");
		      var notice = document.createElement("input");
		      
		      notice.name="temp_ID";
		      notice.value= ID.id;
		      
		      form.appendChild(notice);
		      document.body.appendChild(form); 
		      
		      form.method="post";
		      form.action=url;
		      form.submit();
		   }
	}
	else if($(where_del).val() == '취소'){
		$(where+' .put_pwd').attr("disabled", true);
		$(where_mod).val("수정");
		$(where_del).val("삭제");
	}
	
}

</script>
<body>
<h1 class="label">회원 관리</h1>
	<table id="contents" >
		<tr class="list_title">
			<td width="60%" align="left" class="l_name">NAME</td>
			<td width="15%">JOIN DATE</td>
			<td width="25%">MORE</td>
		</tr>
		<c:choose>
			<c:when test="${memberList == null || memberList.size() == 0 }">
				<tr height="10">
					<td colspan="3">
						<p align="center">
							<b><span style="font-size: 9pt;">등록된 회원이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${memberList !=null && memberList.size()>0}">
				<c:forEach var="member" items="${memberList }">
					<tr align="center" class="tr_list">
						<td class="memList">
							<img class="profileImage" src="${contextPath}/downProfile.do?profileImage=${member.profileImage}&id=${member.id}"></img>
							<p class="mem_id">${member.id}<span class="mem_name"> (${member.name })</span></p>
							<p class="mem_email">${member.email }</p>
						</td>
						<td>${member.joinDate }</td>
						<td class="td_show">
							<input class="btn_detail" type="button" value="+" id="${member.id}" gender="${member.gender}">
						</td>				
					</tr>
					<tr align="center" class="tr_detail">
						<td colspan="3" class="td_detail">
							<!-- 자세히 누르면 나오는 div -->
							<div class="div_${member.id}">
								비밀번호 : <input type="password" class="put_pwd" value ="${member.pwd }" disabled>
								소개 : <input type="text" value ="${member.profileText }" readonly>
								성별 : <input type="radio" class="male" value="male" disabled="disabled"> 남성
									 <input type="radio" class="female" value="female" disabled="disabled"> 여성<br>
								SNS : 
								<lable><input type="checkbox" name="sns" class="sns_i" value="${member.sns_i}" disabled="disabled"> Instagram <input type="text" readonly value="${member.sns_i}"></lable>
								<lable><input type="checkbox" name="sns" class="sns_f" value="${member.sns_f}" disabled="disabled"> FaceBook <input type="text" readonly value="${member.sns_f}"></lable>
								<lable><input type="checkbox" name="sns" class="sns_b" value="${member.sns_b}" disabled="disabled"> BLOG <input type="text" readonly value="${member.sns_b}"></lable>
								<br>
								<input type="button" class="btn_mod admin_btn"  value="수정" onclick="fn_modify(${member.id})">
								<input type="button" class="btn_delete_cancel admin_btn" value="삭제" onclick="fn_remove(${member.id})">
							</div>
						</td>				
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
</body>
</html>