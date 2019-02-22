<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>idCheckForm.jsp</title>
	</head>
	
	<body>
		<div style="text-align: center;">
			<h3>* 아이디 중복확인 *</h3>
			<form method="post" action="idCheckProc.jsp" onsubmit="return blankCheck(this)">
				아이디 :	<input type="text" name="id" maxlength="10" autofocus>
							<input type="submit" value="중복확인">
			</form>
		</div>
		
		<script>
		function blankCheck(f){
			var id=f.id.value;
			id=id.trim();
			var reid= /^[a-zA-Z0-9]{5,10}$/g;
			if(id.length==0){
				alert("아이디 입력하세요");
				return false;
			}else if(id.length<5||id.length>10){
				alert("아이디 5~10 글자 입력하세요");
				return false;
			}//if end
			if(reid.test(id)==false){
				alert("아이디에 한글이나 특수문자가 올 수 없습니다.");
				return false;
			}
			return true;
		}//blankCheck() end
		</script>
	</body>
</html>