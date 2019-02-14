package net.bbs2;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.CommandAction;
import net.utility.Utility;

public class BbsInsert implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		BoardDataBean article=new BoardDataBean();
		article.setNum(Integer.parseInt(req.getParameter("num")));
		article.setWriter(req.getParameter("writer"));
		article.setEmail(req.getParameter("email"));
		article.setSubject(req.getParameter("subject"));
		article.setPasswd(req.getParameter("passwd"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setRef(Integer.parseInt(req.getParameter("ref")));
		article.setRe_step(Integer.parseInt(req.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(req.getParameter("re_level")));
		article.setContent(req.getParameter("content"));
		article.setIp(req.getRemoteAddr());
		
		//System.out.println(article.toString());
		
		BoardDBBean dao=new BoardDBBean();
		dao.insertArticle(article);
		
		//System.out.println("dao빠져나왔나요");
		
		//1)
		//return "insertProc.jsp";
		
		//2) View 페이지 재구성
		String root=Utility.getRoot();
		String msg="<meta http-equiv='refresh' content='0; url="+root+"/bbs2/bbslist.do'>";
		req.setAttribute("msg", msg);
		
		return "bbsResult.jsp";
		
	}//requestPro() end
	
}//BbsInsert end
