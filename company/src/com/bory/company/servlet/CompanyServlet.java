package com.bory.company.servlet;

import java.io.IOException;

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

import com.bory.company.command.Command;
import com.bory.company.command.CommandFactory;

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
			
		}
		
		String commandPropertiesFilePath = servletConfig.getServletContext().getRealPath("/WEB-INF/conf/command.properties");
		commandFactory = new CommandFactory(commandPropertiesFilePath);
		commandFactory.init();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		
		System.out.println("----------CompanyServlet----------");
		request.getSession().setAttribute("context",request.getContextPath());
		request.getSession().setAttribute("js",request.getContextPath()+"/resources/js");
		request.getSession().setAttribute("css",request.getContextPath()+"/resources/css");
	
		
		String uri = request.getRequestURI();
		String viewName = "/WEB-INF/error/404.jsp";
		
		Command command = commandFactory.createCommand(uri);
		if(command != null) {
			viewName = executeCommand(command, request, response);
		}
		System.out.println("option : "+command.getCarrierOption());
		new Carrier(request, response).carryTo(command.getCarrierOption(), viewName);
	
	}
	
	private String executeCommand(Command command, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("----------executeCommand----------");
		String viewName = "/WEB-INF/error/500.jsp";
		try {
			command.setConnection(dataSource.getConnection());
			viewName = command.execute(request, response);
		} catch (Exception e) {
			
		}finally {
			command.destroy();
		}
		return viewName;
	}
}