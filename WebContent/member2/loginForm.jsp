<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ include file="../header.jsp" %>
<%@ include file="/view/color.jspf" %>

<!-- 본문 시작 loginForm.jsp -->
<p>* 로/그/인 *</p>

<c:if test="${empty sessionScope.memid }">
<%
	//쿠키값 가져오기-------------------------------------------------------
	Cookie[] cookies=request.getCookies();
	String c_id="";
	if(cookies!=null){	//쿠키 존재하는지?
		for(int idx=0; idx<cookies.length; idx++){
			Cookie cookie=cookies[idx];
			if(cookie.getName().equals("c_id")==true){//쿠키변수 c_id가 있는지?
				c_id=cookie.getValue();
			}
		}//for end
	}//if end
	//------------------------------------------------------------------------
%>
<form name="loginfrm" method="post" action="loginPro.do" onsubmit="return loginCheck(this)">
	<table>
	<tr>
		<td>
			<input type="text" name="id" id="id" value="<%=c_id %>" placeholder="아이디" required>
		</td>
		<td rowspan="2">
			<input type="image" src="../images/bt_login.gif" style="cursor:pointer">
		</td>
	</tr>
	<tr>
		<td>
			<input type="password" name="passwd" id="passwd" placeholder="비밀번호" required>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<p>
				<input type="checkbox" name="c_id" value="SAVE" <%if(!(c_id.isEmpty())){out.println("checked");} %>>아이디저장
				&nbsp;&nbsp;
				<a href = "agreement.jsp">회원가입</a>
				&nbsp;&nbsp;
				<a href = "Search_idpw.jsp">아이디/비번찾기</a>
			</p>
		</td>
	</tr>
	</table>
</form>
</c:if>

<c:if test="${sessionScope.memid!=null }">
	<table>
	<tr>
		<td rowspan="3">${sessionScope.memid } 님이 방문하셨습니다.
			<form method="post" action="logout.do">
				<input type="submit" value="로그아웃">
			</form>
			<form method="post" action="modifyForm.do">
				<input type="hidden" name="id" value="${sessionScope.memid }">
				<input type="submit" value="회원정보변경">
			</form>
			<form method="post" action="withdrawForm.do">
				<input type="submit" value="회원탈퇴">
			</form>
		</td>
	</tr>
	</table>
</c:if>
<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>