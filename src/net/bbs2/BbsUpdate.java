package net.bbs2;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.CommandAction;
import net.utility.Utility;

public class BbsUpdate implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		int num=Integer.parseInt(req.getParameter("num"));
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		String writer=req.getParameter("writer").trim();
		String subject=req.getParameter("subject").trim();
		String email=req.getParameter("email");
		String content=req.getParameter("content").trim();
		String passwd =req.getParameter("passwd").trim();
		String ip=req.getRemoteAddr();	//요청PC의 IP
		
		//System.out.println(num+" "+pageNum+" "+subject+" "+email+" "+content+" "+passwd+" "+ip);
		
		BoardDataBean article=new BoardDataBean();
		article.setWriter(writer);
		article.setSubject(subject);
		article.setEmail(email);
		article.setContent(content);
		article.setPasswd(passwd);
		article.setIp(ip);
		article.setNum(num);
		
		BoardDBBean dao=new BoardDBBean();
		int res=dao.update(article);
		
		//System.out.println(res);
		
		req.setAttribute("writer", writer);
		req.setAttribute("subject", subject);
		req.setAttribute("email", email);
		req.setAttribute("content", content);
		req.setAttribute("passwd", passwd);
		req.setAttribute("ip", ip);
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("res", new Integer(res));
		
		//return "bbsUpdateProc.jsp";
		String root=Utility.getRoot();
		String msg="";
		if(res==0) {
		msg += "	<p>수정 실패<p>";
		msg += "	<p><a href='javascript:history.back();'>[다시시도]</a><a href='"+root+"/bbs2/bbslist.do?pageNum="+pageNum+"'>[글목록]</a><p>";
		}else {
		msg += "	<script>";
		msg += "	alert('수정 성공');";
		msg += "	window.location='bbslist.do?pageNum="+pageNum+"';";
		msg += "	</script>";
		}
		req.setAttribute("msg", msg);
		
		return "bbsResult.jsp";
	}//requestPro() end
}//BbsInsert end
