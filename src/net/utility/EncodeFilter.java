package net.utility;

import java.io.*;
import javax.servlet.*;

public class EncodeFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
			//request 보내기 전에 전처리할 코드가 있으면 여기서 처리
			//또한 response 후의 후처리할 코드가 있다면 여기서 처리
		arg0.setCharacterEncoding("UTF-8");
		arg2.doFilter(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	
	
	
	
	
}//class end
