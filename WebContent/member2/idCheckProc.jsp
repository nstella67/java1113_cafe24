<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="ssi.jsp" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>idCheckProc.jsp</title>
	</head>
	
	<body>
		<div style="text-align: center;">
			<h3>* 아이디 중복확인 결과 *</h3>
		<%
			String id=request.getParameter("id").trim();
			int cnt=dao.duplecateID(id);
			out.println("입력 ID : <strong>"+id+"</strong>");
			if(cnt==0){
				out.println("<p>사용 가능한 아이디입니다</p>");
				out.println("<a href='javascript:apply(\""+id+"\")'>[적용]</a>");
		%>
			<script>
				function apply(id){
					//부모창 opener
					opener.document.regForm.id.value = id;
					window.close();
				}//apply() end
			</script>
		<%
			}else{
				out.println("<p style='color:red'>해당 아이디는 사용 불가</p>");
			}//if end
		%>
			<hr>
			<a href="javascript:history.back()">[다시검색]</a> 
			&nbsp;&nbsp;
			<a href="javascript:window.close()">[창닫기]</a>
		
		</div>
	</body>
</html>