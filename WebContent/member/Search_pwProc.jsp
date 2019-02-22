<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.internet.InternetAddress"%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="ssi.jsp" %>
<%@ include file="../header.jsp" %>

<%@ page import="java.util.*" %>
<%@ page import="net.utility.*" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>

<!-- 본문 시작 Search_pwProc.jsp -->
	<h3>* 비번 찾기 결과 *</h3>
<%
	String id=request.getParameter("id").trim();
	String mname=request.getParameter("mname").trim();
	String email=request.getParameter("email").trim();

	dto.setMname(mname);
	dto.setEmail(email);

	String passwd=dao.pwsearch(mname, email);

	if(passwd==null){
		out.print("<script>");
		out.print("  alert('이름/메일 다시 한번 확인해주세요');");
		out.print("	history.back();");
		out.print("</script>");
	}else {
		//조회 성공했다면
		int res=dao.newpwSend(passwd, email);
		if(res==0){
			out.print("비밀번호실패");
		}else{
			//1) 메일 서버(POP3/SMTP서버) 지정하기
			String mailServer="mw-002.cafe24.com";
		    Properties props = new Properties();
		    props.put("mail.smtp.host", mailServer);
		    props.put("mail.smtp.auth", "true");
		    
		    //2) 메일서버에서 인증받은 나의 계정.비번
			Authenticator myAuth=new MyAuthenticator();
		    
		    //3) 1)과 2)를 검증
			Session sess = Session.getInstance(props, myAuth);
		    out.print("메일 서버 인증 성공!");
		    
		    try{
		    	//4) 메일보내기
				//		받는사람, 보내는사람, 참조, 숨은참조, 제목, 내용, 날짜, ...
				request.setCharacterEncoding("UTF-8");
				String to=email;
 		    	String from="admin@admin.admin";
		    	String subject="임시 비밀번호";
		    	String msgText=passwd;
		    	
		    	//엔터 및 특수문자 변경
		    	msgText=Utility.convertChar(msgText);
		    	
		    	//받는 사람
		    	InternetAddress[] address={ new InternetAddress(to) };
		    	Message msg=new MimeMessage(sess);
		    	
		    	//받는 사람
		    	msg.setRecipients(Message.RecipientType.TO, address);
	    	
		    	//보내는 사람
		    	msg.setFrom(new InternetAddress(from));
		    	
		    	//메일 제목
		    	msg.setSubject(subject);
		    	
		    	//메일 내용
		    	msg.setContent(msgText, "text/html; charset=UTF-8");
		    	
		    	//메일 전송
		    	Transport.send(msg);
				out.print("<p>"+email+"로 임시비밀번호를 전송하였습니다"+"</p>");
		    }catch(Exception e){
		    	out.println("<p>메일 전송 실패!"+e+"</p>");
		    	out.println("<p><a href='javascript:history.back();'</a></p>");
		    }//try end
			//out.print("<p> 다른 이메일로 받으려면 아래에 입력해주세요 </p>");
		}//if end
	}//if end
%>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>