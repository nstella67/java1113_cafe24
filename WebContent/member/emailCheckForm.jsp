<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>emailCheckForm.jsp</title>
	</head>
	
	<body>

		<div style="text-align: center;">
			<h3>* 이메일 중복확인 *</h3>
			<form method="post" action="emailCheckProc.jsp" onsubmit="return eCheck(this)">
				이메일 :	<input type="text" name="email" maxlength="30" autofocus>
							<select name="email2"  id="email2">
					          <option value="0" selected>@이메일</option>
					          <option value="@naver.com">네이버</option>
					          <option value="@hanmail.net">다음</option>
					          <option value="@gmail.com">구글</option>
					          <option value="@nate.com">네이트</option>
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
			
			if(email.length==0){
				alert("이메일 입력해 주세요!");
				return false;
			}else {
				if(email2=="0"){
					alert("이메일 선택해주세요");
					return false;
				}else if(email2=="1"){
					var remail=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
					if(remail.test(email)==false){
							alert("이메일 형식이 아닙니다");
							f.email.focus();
							return false;
					}
				}else{	
					var retest= /^[a-zA-Z]+[a-zA-Z0-9]{5,10}$/g;
					if(retest.test(email)==false){
						alert("이메일 형식이 아닙니다");
						return false;
					}
				}
			}//if end
			return true;
			
		}//blankCheck() end
		</script>
		
	</body>
</html>