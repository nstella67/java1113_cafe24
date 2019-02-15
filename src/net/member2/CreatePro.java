package net.member2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.CommandAction;

public class CreatePro implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String id  =req.getParameter("id").trim();
		String passwd=req.getParameter("passwd").trim();
		String mname=req.getParameter("mname").trim();
		String email =req.getParameter("email").trim();
		String tel =req.getParameter("tel").trim();
		String zipcode =req.getParameter("zipcode").trim();
		String address1 =req.getParameter("address1").trim();
		String address2 =req.getParameter("address2").trim();
		String job =req.getParameter("job").trim();

		MemberDataBean article=new MemberDataBean();
		article.setId(id);
		article.setPasswd(passwd);
		article.setMname(mname);
		article.setEmail(email);
		article.setTel(tel);
		article.setZipcode(zipcode);
		article.setAddress1(address1);
		article.setAddress2(address2);
		article.setJob(job);

		MemberDBBean dao=new MemberDBBean();
		int res=dao.insert(article);
		req.setAttribute("res", new Integer(res));

		return "createPro.jsp";
	}
	
}
