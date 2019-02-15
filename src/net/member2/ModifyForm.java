package net.member2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.action.CommandAction;

public class ModifyForm implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String id=req.getParameter("id");
		String passwd=req.getParameter("passwd");	//
		//System.out.println(passwd+"      ");
		
		MemberDataBean article=new MemberDataBean();
		article.setId(id);
		article.setPasswd(passwd);

		MemberDBBean dao=new MemberDBBean();
		article=dao.modifyForm(article);
		
		req.setAttribute("article", article);

		return "modifyForm.jsp";
	}

}
