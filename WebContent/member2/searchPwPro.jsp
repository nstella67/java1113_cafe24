<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ include file="ssi.jsp" %>
<%@ include file="../header.jsp" %>


<!-- 본문 시작 memberProc.jsp -->
<c:choose>
	<c:when test="${MailResult==0 }">
		<script>
			alert("이메일 전송이 실패했습니다");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<p>"${Nemail }"로 임시비밀번호를 전송하였습니다</p>
	</c:otherwise>
</c:choose>   
<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>