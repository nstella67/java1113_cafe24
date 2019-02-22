package net.member2;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.CommandAction;
import net.utility.*;

public class SearchPw implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String email=req.getParameter("email");
		String Nemail=req.getParameter("Nemail");
		
		MemberDataBean article=new MemberDataBean();
		article.setEmail(email);
		
		MemberDBBean dao=new MemberDBBean();
		String passwd=dao.pwsearch(article);	//임시비밀번호 설정
		article.setPasswd(passwd);
		
		int res=dao.newPw(passwd, email);	//임시비밀번호로 update
		
		int MailResult=0;
		if(res==0){
			System.out.println("비밀번호실패");
		}else {
			String mailServer="mw-002.cafe24.com";
			Properties props = new Properties();
			props.put("mail.smtp.host", mailServer);
			props.put("mail.smtp.auth", "true");
			    
			Authenticator myAuth=new MyAuthenticator();
			    
			Session sess = Session.getInstance(props, myAuth);
			    try{
					req.setCharacterEncoding("UTF-8");
					String to=Nemail;
	 		    	String from="admin@admin.kr";
			    	String subject="임시 비밀번호";
			    	String msgText=passwd;
			    	msgText=Utility.convertChar(msgText);
			    	
			    	InternetAddress[] address={ new InternetAddress(to) };
			    	Message msg=new MimeMessage(sess);
			    	msg.setRecipients(Message.RecipientType.TO, address);
			    	msg.setFrom(new InternetAddress(from));
			    	msg.setSubject(subject);
			    	msg.setContent(msgText, "text/html; charset=UTF-8");
			    	Transport.send(msg);
			    	
			    	MailResult=1;
			    }catch(Exception e){
			    	MailResult=0;
			    	e.printStackTrace();
			    }//try end
		}//if end
		
		req.setAttribute("Nemail", Nemail);
		req.setAttribute("MailResult", MailResult);
		
		return "searchPwPro.jsp";
	}//requestPro end

}//class end
