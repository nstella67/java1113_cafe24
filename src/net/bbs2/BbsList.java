package net.bbs2;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.CommandAction;

public class BbsList implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		//�� �Խñ� ��
		int total_cnt=0;
		BoardDBBean dao=new BoardDBBean();
		total_cnt=dao.getArticleCount();
		
		//����¡
		int numPerPage=10;	//�� �������� ���ڵ� ����
		int pagePerBlock=10;	//������ ����Ʈ
		
		String pageNum=req.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*numPerPage+1;
		int endRow=currentPage*numPerPage;
		
		//��������
		double totcnt=(double)total_cnt/numPerPage;
		int totalPage=(int)Math.ceil(totcnt);
		
		double d_page=(double)currentPage/pagePerBlock;
		int Pages=(int)Math.ceil(d_page)-1;
		int startPage=Pages*pagePerBlock;
		int endPage=startPage+pagePerBlock+1;
		
		List articleList=null;
		if(total_cnt>0) {
			articleList=dao.getArticles(startRow, endRow);
		}else {
			articleList=Collections.EMPTY_LIST;
		}//if end
		
		int number=0;
		number=total_cnt-(currentPage-1)*numPerPage;
		
		req.setAttribute("number", new Integer(number));
		req.setAttribute("pageNum", new Integer(currentPage));
		req.setAttribute("startRow", new Integer(startRow));
		req.setAttribute("endRow", new Integer(endRow));
		req.setAttribute("count", new Integer(total_cnt));
		req.setAttribute("pageSize", new Integer(pagePerBlock));
		req.setAttribute("totalPage", new Integer(totalPage));
		req.setAttribute("startPage", new Integer(startPage));
		req.setAttribute("endPage", new Integer(endPage));
		req.setAttribute("articleList", articleList);
		
		return "bbsList.jsp";
	}//returnPro() end
	
}//BbsList end
