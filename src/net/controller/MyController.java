package net.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.CommandAction;

public class MyController extends HttpServlet {
	
	private Map commandMap=new HashMap();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//web.xml에서 propertyConfig에 해당하는 init-param의 값을 읽어옴
		String props=config.getInitParameter("propertyConfig");
		Properties pr=new Properties();	//명령어와 처리클래스의 매핑정보를 저장할 Properties 객체 생성
		FileInputStream f=null;
		try {
			f=new FileInputStream(props);	//command.properties파일 가져오기
			pr.load(f);								//command.properties파일을 Properties객체에 저장
		} catch(IOException e) {
			System.out.println(e);
		}finally {
			if(f!=null)try {f.close();}catch(Exception ex) {}
		}//try end
		
		Iterator keyIter=pr.keySet().iterator();
		while(keyIter.hasNext()) {
			String key=(String)keyIter.next();
			String value=pr.getProperty(key);
			//System.out.println(key);			// /myweb/index.do
			//System.out.println(value);		// net.action.Index
			
			try {
				Class commandClass=Class.forName(value);
				Object commandInstance=commandClass.newInstance();
				commandMap.put(key, commandInstance);
			}catch(Exception e) { System.out.println(e); }
		}
		
	}//init() end

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}//doGet() end

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view=null;
		CommandAction com=null;
		try {
			String command=req.getRequestURI();
			com=(CommandAction)commandMap.get(command);
			//System.out.println("어디서");
			view=com.requestPro(req, resp);
			//System.out.println("오류가난거니");
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher=req.getRequestDispatcher(view);
		dispatcher.forward(req, resp);
		//System.out.println("...");
	}//process() end
	
}//class end
