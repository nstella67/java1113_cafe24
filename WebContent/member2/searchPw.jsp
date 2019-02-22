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
		<form method="get" action="searchPw.do">
			<input type="hidden" name="email" value="${article.email }">
			<table>
			<tr>
				<th align="center">임시 비밀번호 발급 이메일</th>
				<td><input type="text" name="Nemail" size="50" value="${article.email }"></td>
				<td><input type="submit" value="확인"></td>
			</tr>
			</table>
		</form>
	</c:otherwise>
</c:choose>
<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>