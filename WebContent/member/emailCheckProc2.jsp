<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="ssi.jsp" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>emailCheckProc2.jsp</title>
	</head>
	
	<body>
		<div style="text-align: center;">
			<h3>* 이메일 중복확인 결과 *</h3>
		<%
			String email=request.getParameter("email").trim();
			int cnt=dao.duplecateEmail(email);
			out.println("입력 EMAIL : <strong>"+email+"</strong>");
			if(cnt==0){
				out.println("<p>사용 가능한 이메일입니다</p>");
				out.println("<a href='javascript:apply(\""+email+"\")'>[적용]</a>");
				%>
					<script>
						function apply(email){
							//부모창 opener
							opener.document.regForm.email.value = email;
							window.close();
						}//apply() end
					</script>
				<%
			}else{
				out.println("<p style='color:red'>해당 이메일는 사용 불가</p>");
			}//if end
		%>
			<hr>
			<a href="javascript:history.back()">[다시검색]</a> 
			&nbsp;&nbsp;
			<a href="javascript:window.close()">[창닫기]</a>
	</body>
</html>