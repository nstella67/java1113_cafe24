<%@page import="net.bbs2.BoardDataBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="ssi.jsp" %>
<%@ include file="../header.jsp" %>

<!-- 본문 시작 memberProc.jsp -->
<c:choose>
	<c:when test="${res==0 }">
		<p>회원가입 실패<p>
		<p><a href='javascript:history.back();'>[다시시도]</a><p>
	</c:when>
	<c:otherwise>
		<script>
		alert("회원가입 성공");
		window.location="loginForm.jsp";
		</script>
	</c:otherwise>
</c:choose>
<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>