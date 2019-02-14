<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="/view/color.jspf" %>

<!-- 본문 시작 bbsDeleteform.jsp-->
<strong>* 비밀번호 확인 *</strong>
<br><br>
<form method="post" name="writeform" action="${page }.do">
<input type="hidden" name="num" value="${num }">
<input type="hidden" name="pageNum" value="${pageNum  }">
<table border="1">
<tr>
  <td bgcolor="${value_c }">비밀번호</td>
  <td><input type="password" name="passwd"></td>
</tr>
<tr>
  <td  colspan=2 bgcolor="{$value_c }" align="center">
  <input type="submit" value="확인">
  <input type="reset"  value="취소">
  </td>
</tr>
</table>
</form>
<!-- 
<script>
	function checkPasswd(){
		inputForm=eval("document.writeform");
	}
</script>
 -->
<!-- 본문 끝 -->			
<%@ include file="../footer.jsp" %>