<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="./ssi.jsp" %>
<%@ include file="../header.jsp" %>

<!-- 본문 시작 memberProc.jsp -->
<h3>* 수정 결과 *</h3>
<% 
	String id  =request.getParameter("id").trim();
	String passwd=request.getParameter("passwd").trim();
	String mname=request.getParameter("mname").trim();
	String email =request.getParameter("email").trim();
	String tel =request.getParameter("tel").trim();
	String zipcode =request.getParameter("zipcode").trim();
	String address1 =request.getParameter("address1").trim();
	String address2 =request.getParameter("address2").trim();
	String job =request.getParameter("job").trim();

	dto.setId(id);
	dto.setPasswd(passwd);
	dto.setMname(mname);
	dto.setEmail(email);
	dto.setTel(tel);
	dto.setZipcode(zipcode);
	dto.setAddress1(address1);
	dto.setAddress2(address2);
	dto.setJob(job);

	int res=dao.insert(dto);

	if(res==0){
		  out.print("회원가입 실패<br/>");
		  out.print("<a href='javascript:history.back();'>[다시시도]</a>");
		}
		else {
		  out.print("<script>");
		  out.print("  alert('회원가입 성공');");
		  out.print("  window.location='loginForm.jsp';");
		  out.print("</script>");
		}
%>
<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>