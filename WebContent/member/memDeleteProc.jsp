<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="./ssi.jsp" %>
<%@ include file="../header.jsp" %>

<!-- 본문 시작 memDeleteProc.jsp -->
<h3>* 탈퇴 결과 *</h3>
<% 
	String id=(String)session.getAttribute("s_id");
	String passwd=request.getParameter("passwd").trim();

	dto.setId(id);
	dto.setPasswd(passwd);

	int res=dao.memDelete(dto);

	if(res==0){
		out.print("<p>탈퇴 실패<p>");
		out.print("<p><a href='javascript:history.back();'>[다시시도]</a><p>");
	}else {
		out.print("<script>");
		out.print("  alert('탈퇴 성공');");
		out.print("</script>");
		session.removeAttribute("s_id");
		session.removeAttribute("s_passwd");
		session.removeAttribute("s_mlevel");
		String root=Utility.getRoot();
		response.sendRedirect(root+"/index.jsp");
	}
%>
<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>