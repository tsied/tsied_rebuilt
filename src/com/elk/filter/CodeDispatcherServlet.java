package com.elk.filter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * @date 2016-03-18
 * @author rock
 * @desc 编码过滤器
 */
public class  CodeDispatcherServlet extends DispatcherServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String encoding;
	
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void init(ServletConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");  
		  super.init(config);  
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		super.doService(request, response);
	}  


}
