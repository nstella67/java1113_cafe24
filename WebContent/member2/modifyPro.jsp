<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="./ssi.jsp" %>
<%@ include file="../header.jsp" %>

<!-- 본문 시작 bbsUpdateProc.jsp -->
<h3>* 수정 결과 *</h3>
<c:choose>
	<c:when test="${res==0 }">
		<p>회원정보 수정 실패<p>
		<p><a href='javascript:history.back();'>[다시시도]</a><p>
	</c:when>
	<c:otherwise>
		<script>
		alert("회원정보 수정 성공");
		window.location="loginForm.jsp";
		</script>
	</c:otherwise>
</c:choose>
<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>