package com.bory.company.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bory.company.command.common.Command;
import com.bory.company.command.common.CommandFactory;
import com.bory.company.exception.CommandExecutionException;
import com.bory.company.exception.IllegalParameterException;

public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = -291178543531058657L;
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServlet.class);
	
	private CommandFactory commandFactory;
	private DataSource dataSource;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		
		super.init();
		try {
			this.dataSource = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/orcl"); 
		} catch (NamingException e) {
			LOGGER.error("",e);
			throw new ServletException(e);
		}
		
		String commandPropertiesFilePath = servletConfig.getServletContext().getRealPath("/WEB-INF/conf/command.properties");
		commandFactory = new CommandFactory(commandPropertiesFilePath);
		commandFactory.init();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		
		request.setAttribute("context",request.getContextPath());
		request.setAttribute("js",request.getContextPath()+"/resources/js");
		request.setAttribute("css",request.getContextPath()+"/resources/css");
	
		
		String uri = request.getRequestURI();
		String viewName = "/WEB-INF/view/error/404.jsp";
		
		Command command = commandFactory.createCommand(uri);
		if(command != null) {
			try {
				viewName = executeCommand(command, request, response);
				}catch(IllegalParameterException ipe) {
					request.setAttribute("exception", ipe);
					viewName = "/WEB-INF/jsp/error/400.jsp";
				}
		}
		try {
			request.getRequestDispatcher(viewName).forward(request, response);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}
	
	private String executeCommand(Command command, HttpServletRequest request, HttpServletResponse response) {
		
		String viewName = "/WEB-INF/view/error/500.jsp";
		try {
			Connection connection = dataSource.getConnection();
			//connection.setAutoCommit(false);
			command.setConnection(connection);
			viewName = command.execute(request, response);
		} catch (Exception e) {
			throw new CommandExecutionException(e);
		}finally {
			command.destroy();
		}
		return viewName;
	}
}
