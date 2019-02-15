<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ include file="../header.jsp" %>

<c:choose>
	<c:when test="${res==0 }">
		<p>비밀번호 다시 확인<p>
		<p><a href='javascript:history.back();'>[다시시도]</a><p>
	</c:when>
	<c:otherwise>
		<script>
		alert("회원 탈퇴 성공");
		window.location="loginForm.jsp";
		</script>
		<c:remove var="memid" scope="session"></c:remove>
		<c:remove var="s_id" scope="session"></c:remove>
		<c:remove var="s_passwd" scope="session"></c:remove>
	</c:otherwise>
</c:choose>

<%@ include file="../footer.jsp" %>