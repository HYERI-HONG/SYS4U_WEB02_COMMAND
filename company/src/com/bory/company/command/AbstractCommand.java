package com.bory.company.command;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractCommand implements Command{

	protected Connection connection;

	@Override
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	protected String getParameter(HttpServletRequest request, String name, String defaultValue) {
		
		return (request.getParameter(name)==null? defaultValue : request.getParameter(name));
		
	}
	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			
		}
	}
}
