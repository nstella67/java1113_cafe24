<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ include file="../header.jsp" %>

<!-- 본문 시작 memberProc.jsp -->
<c:choose>
	<c:when test="${article==null }">
		<script>
			alert("이름 또는 이메일 다시 확인");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<p>' ${article.mname } ' 님의 아이디 : ${article.id }</p>
	</c:otherwise>
</c:choose>
<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>