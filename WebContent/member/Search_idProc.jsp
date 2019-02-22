<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="ssi.jsp" %>
<%@ include file="../header.jsp" %>

<!-- 본문 시작 Search_idProc.jsp -->
	<h3>* 아이디 찾기 결과 *</h3>
<%
	String mname=request.getParameter("mname").trim();
	String email=request.getParameter("email").trim();

	dto.setMname(mname);
	dto.setEmail(email);

	String id=dao.idsearch(mname, email);

	if(id==null){
		out.print("<script>");
		out.print("  alert('이름/메일 다시 한번 확인해주세요');");
		out.print("	history.back();");
		out.print("</script>");
	}else {
		//조회 성공했다면
		out.print(mname+"님의 아이디 : "+id);
	}
%>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>