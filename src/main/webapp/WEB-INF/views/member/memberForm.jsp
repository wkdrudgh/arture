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
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/member.css"/>
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
   
    
function fn_overlapped(){
   var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
   var id = document.getElementById("_member_id");
    if(!check(re,id,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력해주세요")) {
        return false;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlapped.do",
       dataType:"text",
       data: {id:id.value},
       success:function (data,textStatus){
          if(data=='false'){
              alert("사용가능한 ID입니다.");
              $('#btnOverlapped').css("background-color","#ff5779");
              $('#btnOverlapped').prop("disabled", true);
              $('#_member_id').prop("disabled", true);
              $('#id').val(id.value);
          }else{
             alert("이미 사용중인 ID입니다.");
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
 
function validate() {
       var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
       var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
       // 이메일이 적합한지 검사할 정규식
      var id = document.getElementById("_member_id");
       var idCheck = document.getElementById("id");
       var pwd = document.getElementById("pwd");
       var pwdCheck = document.getElementById("pwdCheck");
       var email = document.getElementById("email");

    // ------------ 이메일 까지 -----------

    if(!check(re,id,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력해주세요")) {
        return false;
    }else if(!check(re,pwd,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력해주세요")) {
        return false;
    }else if(join.id.value=="") {
        alert("아이디 중복확인을 해주세요");
        join.id.focus();
        return false;
    }else if(pwd.value != pwdCheck.value) {
        alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
        join.pwdCheck.value = "";
        join.pwdCheck.focus();
        return false;
    }else if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
        return false;
    }
    alert("회원가입이 완료되었습니다. 로그인 해주세요.");
    return true;
    
}

function check(re, what, message) {
    if(re.test(what.value)) {
        return true;
    }
    alert(message);
    what.value = "";
    what.focus();
    //return false;
}

 
 $(function(){
    
    $("#pwd").keyup(function(){
       if($("#pwd").val() == "" ){
          $("#chk1").css("color","#fff");
       }else {
          $("#chk1").css("color","#8ce257");
       }
       
    });
    $("#pwdCheck").keyup(function(){
      var pwd1 = $("#pwd").val();   
      var pwd2 = $("#pwdCheck").val();   
      if(pwd1 == pwd2){
         $("#chk1").css("color","#8ce257");
         $("#chk2").css("color","#8ce257");
      } else {
         $("#chk2").css("color","#fff");
      }
    });
    
 }); 
</script>

<title>회원 가입창</title>



</head>


<body>
   <div id="joinWrapper">
      <form name="join" method="post" onsubmit="return validate()"  autocomplete="off" action="${contextPath }/member/addMember.do">
      <table>
         <tr>
            <td><p>아이디</p></td>
         </tr>
         <tr>
              <td><input type="text" name="_member_id"  id="_member_id"  size="20" required/></td>
         <input type="hidden" name="id"  id="id"/>
         <td>
         <input type="button" id="btnOverlapped" value="중복확인" onClick="fn_overlapped()"/>
         </td>
         </tr>
         <tr>
            <td><p>비밀번호</p></td>
          </tr>
          <tr>
             <td><input type="password" name="pwd" id="pwd" class="pwd" required/></td>
             <td><i class="fas fa-check-circle" id="chk1"></i></td>
          </tr>
          <tr>
            <td><p>비밀번호 확인</p></td>
          </tr>
          <tr>
             <td><input type="password" name="pwdCheck" id="pwdCheck" class="pwd" required/></td>
             <td><i class="fas fa-check-circle" id="chk2"></i></td>
          </tr>
          <tr>
             <td><p>이름</p></td>
          </tr>
          <tr>
             <td><input type="text" name="name" id="name" required/></td>
          </tr>
          <tr>
             <td><p>성별</p></td>
          </tr>
          <tr>
             <td>
             <input class="radio" type="radio" name="gender" value="남" checked/><span>남</span>
             <input class="radio" type="radio" name="gender" value="여"/><span>여</span>
             </td>
          </tr>
          <tr>
             <td><p>이메일</td>
          </tr>
          <tr>
             <td><input type="text" name="email" id="email" required/></td>
          </tr>
          <tr>
             <td class="td1"><input id="submit" type="submit" value="Join Us"/></td>
          </tr>
      </table>
      </form>
   </div>
</body>
</html>