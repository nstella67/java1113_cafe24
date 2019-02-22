<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="ssi.jsp" %>
<%@ include file="../header.jsp"%>
<%@ include file="auth.jsp" %>

<!-- 본문 시작 bbsDel.jsp-->
<h3>* 회원탈퇴 *</h3>
<form method="get" action="memDeleteProc.jsp" onsubmit="return pwCheck(this)">
<%
	String id=(String)session.getAttribute("s_id");
%>
<input type="hidden" name="id" value="<%=request.getParameter("id") %>">
<table>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="passwd" size="10"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="탈퇴"></td>
		</tr>
</table>

</form>
<!-- 본문 끝 -->

<%@ include file="../footer.jsp"%>