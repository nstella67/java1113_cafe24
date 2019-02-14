package net.bbs2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.action.CommandAction;

public class BbsContent implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//�ش�۹�ȣ
		int num=Integer.parseInt(req.getParameter("num"));
		//�ش���������ȣ
		String pageNum=req.getParameter("pageNum");

		//DBó��
		BoardDBBean dao=new BoardDBBean();
		//�ش� �۹�ȣ�� ���� �ش� ���ڵ�
		BoardDataBean article =dao.getArticle(num);
		
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("article", article);
		return "bbsContent.jsp";
	}//requestPro() end
}//BbsList end
