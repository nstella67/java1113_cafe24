package net.bbs2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.CommandAction;
import net.utility.Utility;

public class BbsDelete implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		int num=Integer.parseInt(req.getParameter("num"));
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		String passwd=req.getParameter("passwd");
		
		BoardDataBean article=new BoardDataBean();
		article.setPasswd(passwd);
		article.setNum(num);
		
		BoardDBBean dao=new BoardDBBean();
		int res=dao.delete(article);
		
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("res", new Integer(res));
		//System.out.println(res);
		//return "bbsDelProc.jsp";
		String root=Utility.getRoot();
		String msg="";
		//System.out.println(res);
		if(res==0) {
		msg += "	<p>삭제 실패<p>";
		msg += "	<p><a href='javascript:history.back();'>[다시시도]</a><a href='"+root+"/bbs2/bbslist.do?pageNum="+pageNum+"'>[글목록]</a><p>";
		}else {
		msg += "	<script>";
		msg += "	alert('삭제 성공');";
		msg += "	window.location='bbslist.do?pageNum="+pageNum+"';";
		msg += "	</script>";
		}
		req.setAttribute("msg", msg);
		
		return "bbsResult.jsp";
	}//requestPro() end
}//BbsInsert end
