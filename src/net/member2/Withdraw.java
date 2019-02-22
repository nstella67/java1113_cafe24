package net.member2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.action.CommandAction;

public class Withdraw implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("s_id");
		String passwd=req.getParameter("passwd").trim();

		MemberDataBean article=new MemberDataBean();
		article.setId(id);
		article.setPasswd(passwd);

		MemberDBBean dao=new MemberDBBean();
		int res=dao.withdraw(id, passwd);
		
		req.setAttribute("res", res);
		return "withdrawPro.jsp";
	}

}
