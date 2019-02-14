<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ include file="../header.jsp" %>
<%@ include file="/view/color.jspf" %>

<!-- 본문 시작 bbDelProcjsp-->
<c:choose>
	<c:when test="${res==0 }">
		<p>삭제 실패<p>
		<p><a href='javascript:history.back();'>[다시시도]</a><a href="bbslist.do?pageNum=${pageNum }">[글목록]</a><p>
	</c:when>
	<c:otherwise>
		<script>
		alert("삭제 성공");
		window.location="bbslist.do?pageNum="+${pageNum };
		</script>
	</c:otherwise>
</c:choose>
<!-- 본문 끝 -->			
<%@ include file="../footer.jsp" %>