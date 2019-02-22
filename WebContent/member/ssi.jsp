<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<%@ page import="net.utility.*"%>
<%@ page import="net.member.*"%>

<jsp:useBean id="dao" class="net.member.MemberDAO"></jsp:useBean>
<jsp:useBean id="dto" class="net.member.MemberDTO"></jsp:useBean>

<% request.setCharacterEncoding("UTF-8"); %>


<%//--------------------------------------------------------------------------------
	//검색 목록 페이지
	String col=request.getParameter("col");		//검색칼럼
	String word=request.getParameter("word");	//검색어
	/* if(col==null){
		col="";
	}
		
	if(word==null){
		word="";
	} */

	//Utility Class 활용
	col = Utility.checkNull(col);	//null이면 공백문자열 반환
	word=Utility.checkNull(word);
//------------------------------------------------------------------------------------

//현재페이지
	int nowPage=1;
	if(request.getParameter("nowPage")!=null){
		nowPage=Integer.parseInt(request.getParameter("nowPage"));
	}

//------------------------------------------------------------------------------------
%>
