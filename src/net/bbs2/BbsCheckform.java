package net.bbs2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.CommandAction;

public class BbsCheckform implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		int num=Integer.parseInt(req.getParameter("num"));
		int pageNum= Integer.parseInt(req.getParameter("pageNum"));
		String passwd=req.getParameter("passwd");
		String page=req.getParameter("page");
		
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("passwd", passwd);
		req.setAttribute("page", page);
		
		return "bbsCheck.jsp";
	}
	
}//class end
