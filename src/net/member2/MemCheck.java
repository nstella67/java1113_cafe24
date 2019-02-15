package net.member2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.CommandAction;

public class MemCheck implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String page=req.getParameter("page");
		
		req.setAttribute("page", page);
		
		return "memCheck.jsp";
	}
}//class end
