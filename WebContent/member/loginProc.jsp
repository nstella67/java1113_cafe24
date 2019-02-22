<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="ssi.jsp" %>
<%@ include file="../header.jsp" %>

<!-- 본문 시작 loginForm.jsp -->
	<h3>* 로그인 결과 *</h3>
	<% 
	String id  =request.getParameter("id").trim();
	String passwd=request.getParameter("passwd").trim();
	
	dto.setId(id);
	dto.setPasswd(passwd);
	
	String mlevel=dao.loginProc(dto);
	if(mlevel==null){
	out.println("<p>아이디/비번 다시 한번 확인해주세요!</p>");
	out.println("<p><a href='javascript:history.back()'>[다시시도]</a></p>");
	}else{
		//out.println("<p>로그인성공!!</p>");
		//out.println(mlevel);
		
		//다른 페이지에서 로그인 상태를 공유할 수 있도록 session 변수에 올리기
		session.setAttribute("s_id", id);
		session.setAttribute("s_passwd", passwd);
		session.setAttribute("s_mlevel", mlevel);
		
		//쿠키(아이디저장)------------------------------------------------------------
		String c_id=Utility.checkNull(request.getParameter("c_id"));
		Cookie cookie=null;
		if(c_id.equals("SAVE")){
			//new Cookie("쿠키변수", 값)
			cookie=new Cookie("c_id", id);
			//쿠키 생존기간, 1개월
			cookie.setMaxAge(60*60*24*30);
		}else{
			cookie=new Cookie("c_id", "");
		}//if end
		
		//요청한 사용자 PC에 쿠키값을 저장
		response.addCookie(cookie);
		//------------------------------------------------------------------------------
		
		//첫 페이지로 이동
		//String root=request.getContextPath();
		String root=Utility.getRoot();	//같은 표현
		
		response.sendRedirect(root+"/index.jsp");
	}
	%>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>