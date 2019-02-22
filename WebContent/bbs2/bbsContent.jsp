<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ include file="../header.jsp" %>

<!-- 본문 시작 bbsList.jsp-->
<strong>* 글내용보기 *</strong>
<br><br>

<table border="1">
<tr>
	<td bgcolor="${value_c }">글번호</td>
	<td>${article.num }</td>
	<td bgcolor="${value_c }">조회수</td>
	<td>${article.readcount }</td>
</tr>
<tr>
	<td bgcolor="${value_c }">작성자</td>
	<td>${article.writer }</td>
	<td bgcolor="${value_c }">작성일</td>
	<td>${article.reg_date }</td>
</tr>
<tr>
	<td bgcolor="${value_c }">글제목</td>
	<td colspan=3>${article.subject }</td>
</tr>
<tr>
	<td bgcolor="${value_c }">글내용</td>
	<td colspan=3>
	<%	//치환 변수 선언
		pageContext.setAttribute("cn", "\r");
		pageContext.setAttribute("br", "<br>");
	%>
		<!-- ${article.content } -->
		<!-- \n값이 <br>값으로 바꿔서 출력하게함 -->
		${fn:replace(article.content, cn, '<br>') }
	</td>
</tr>
<tr>
	<td colspan=4 bgcolor="${value_c }">
		<input type="button" value="글수정" onclick="location.href='./bbscheckform.do?num=${article.num}&pageNum=${pageNum }&page=bbsupdateform' ">
		<input type="button" value="글삭제" onclick="location.href='./bbscheckform.do?num=${article.num}&pageNum=${pageNum }&page=bbsdelete' ">
		<input type="button" value="답변" onclick="location.href='./bbsform.do?num=${article.num}&ref=${article.ref }&re_step=${article.re_step }&re_level=${article.re_level }' ">
		<input type="button" value="목록" onclick="location.href='./bbslist.do?pageNum=${pageNum }' ">
	</td>
</tr>
</table>
<!-- 본문 끝 -->			
<%@ include file="../footer.jsp" %>