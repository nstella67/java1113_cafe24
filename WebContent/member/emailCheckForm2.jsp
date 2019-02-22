<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>emailCheckForm2.jsp</title>
	</head>
<body> 
<!-- 		<div style="text-align: center;">
			<h3>* 이메일 중복확인 *</h3>
			<form method="post" action="emailCheckProc2.jsp" onsubmit="return eCheck(this)">
				이메일 :	<input type="text" name="email" maxlength="30" autofocus>
							<select name="email2"  id="email2">
					          <option value="0" selected>이메일</option>
					          <option value="@naver.com">@naver.com</option>
					          <option value="@hanmail.net">@hanmail.net</option>
					          <option value="@google.com">@google.com</option>
					          <option value="1">직접입력</option>
					        </select>
							<input type="submit" value="중복확인">
			</form>
		</div>
		
		<script>
		function eCheck(f){
			var email=f.email.value;		//이메일아이디 or 이메일
			email=email.trim();
			var email2=f.email2.value;		//@주소
			email2=email2.trim();
			
			if(email2=="0"){
				alert("이메일 선택해주세요");
				return false;
			}else if(email2=="1"){
				if(email.length==0){
					alert("이메일 입력해 주세요!");
					return false;
				}else if(email.indexOf("@")==-1||email.indexOf(".")==-1||email.indexOf("@")>email.indexOf(".")){
					alert("이메일 형식이 아닙니다");
					return false;
				}
			}else{
				email=email+email2;		//이메일=email1+email2
			}//if end
			return true;
			
		}//blankCheck() end
		</script> -->
		
				</div>
				<div style="text-align: center;">
			<h3>* 이메일 중복확인 *</h3>
			<form method="post" action="emailCheckProc2.jsp" onsubmit="return eCheck(this)">
				이메일 :	<input type="text" name="email" maxlength="30" autofocus>
							<input type="submit" value="중복확인">
			</form>
		</div>
		
		<script>
		function eCheck(f){
			var email=f.email.value;
			email=email.trim();
			if(email1.length==0){
				alert("이메일 입력해 주세요!");
				f.email.focus();
				return false;
			}else if(email.indexOf("@")==-1||email.indexOf(".")==-1||email.indexOf("@")>email.indexOf(".")){
				alert("이메일 형식이 아닙니다");
				f.email.focus();
				return false;;
			}//if end
			return true;
		}//blankCheck() end
		</script>
</body>
</html>