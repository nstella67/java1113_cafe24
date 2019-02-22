<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="/view/color.jspf" %>

<!-- 본문 시작 bbsForm.jsp-->
<strong>* 글/수/정 *</strong>
<br><br>
<c:choose>
	<c:when test="${article==null }">
		<p>비밀번호 실패<p>
		<p><a href='javascript:history.back();'>[다시시도]</a><a href="bbslist.do?pageNum=${pageNum }">[글목록]</a><p>
	</c:when>
	<c:otherwise>
		<form method="post" name="writeform" action="./bbsupdate.do">
		<input type="hidden" name="num"      value="${num }">
		<input type="hidden" name="pageNum"      value="${pageNum }">
		
		<table width="450" border="1">
		<tr>
		  <td align="right" colspan=2 bgcolor="${value_c }">
		      <a href="/bbs2/bbslist.do">글목록</a></td>
		</tr>
		<tr>
		  <td bgcolor="${value_c }">이름</td>
		  <td><input type="text" name="writer" value="${article.writer }"></td>
		</tr>
		<tr>
		  <td bgcolor="${value_c }">제목</td>
		  <td><input type="text" name="subject" value="${article.subject }"></td>
		</tr>
		<tr>
		  <td bgcolor="${value_c }">이메일</td>
		  <td><input type="text" name="email" value="${article.email }"></td>
		</tr>
		<tr>
		  <td bgcolor="${value_c }">내용</td>
		  <td><textarea name="content" rows="5" cols="40">${article.content }</textarea></td>
		</tr>
		<tr>
		  <td bgcolor="${value_c }">비밀번호</td>
		  <td><input type="password" name="passwd" value="${article.passwd }"></td>
		</tr>
		<tr>
		  <td colspan=2 bgcolor="{$value_c }" align="center">
		  <input type="submit" value="수정">
		  <input type="reset"  value="취소">
		  <input type="button" value="목록보기" onClick="location.href='./bbslist.do'">
		  </td>
		</tr>
		</table>
		</form>
		
	</c:otherwise>
</c:choose>

<!-- 본문 끝 -->			
<%@ include file="../footer.jsp" %>