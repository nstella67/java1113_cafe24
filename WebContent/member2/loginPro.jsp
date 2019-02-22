<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp" %>

<c:if test="${res==1 }">
	<c:set var="memid" value="${sessionScope.s_id }" scope="session"></c:set>
	<meta http-equiv="Refresh" content="0; url=/member2/loginForm.do">
</c:if>
<c:if test="${res==0 }">
	아이디 및 비밀번호가 다릅니다<br>
	<a href="javascript:history.back()">[돌아가기]</a>
</c:if>
<%@ include file="../footer.jsp" %>