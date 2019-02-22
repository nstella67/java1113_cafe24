<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="ssi.jsp" %>
<%@ include file="../header.jsp" %>

<!-- 본문 시작 Search_idpw.jsp -->
	<h3>* 아이디/비번 찾기 *</h3>
<!-- 아이디찾기---------------------------------------------------------------------------------------- -->
<form method="get" action="search.do">
<input type="hidden" name="page" value="searchId">
<table>
	<tr> <th colspan="2">*아이디 찾기* </th></tr>
	<tr>
		<th>이름</th>
		<td><input type="text" name="mname" size="10"></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="text" name="email" size="50"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="아이디찾기" >
		</td>
	</tr>
</table>
</form>
<br>

<!-- 비번 찾기--------------------------------------------------------------------------------------- -->
<form method="get" action="search.do">
<input type="hidden" name="page" value="searchPw">
	<table>
		<tr> <th colspan="2">*비밀번호 찾기* </th></tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="mname" size="10"></td>
		</tr>
 		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" size="10"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email" size="50"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="비밀번호찾기"></td>
		</tr>
</table>
</form>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>