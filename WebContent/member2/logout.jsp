<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ include file="../header.jsp" %>
<%@ include file="/view/color.jspf" %>

<c:remove var="memid" scope="session"></c:remove>
<c:remove var="s_id" scope="session"></c:remove>
<c:remove var="s_passwd" scope="session"></c:remove>

<c:choose>
	<c:when test="memid!=null">
		<p>로그아웃 실패<p>
		<p><a href='javascript:history.back();'>[다시시도]</a><p>
	</c:when>
	<c:otherwise>
		<script>
		window.location="loginForm.do?";
		</script>
	</c:otherwise>
</c:choose>

<%@ include file="../footer.jsp" %>