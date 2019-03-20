package com.bory.company.command.common;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractCommand implements Command{

	protected Connection connection;

	@Override
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	protected String getParameter(HttpServletRequest req, String name, String defaultValue) {
		String value = req.getParameter(name);
		return value == null || value.isEmpty()||value.equals("null")? defaultValue : value;
	}
	
	protected int getIntParameter(HttpServletRequest req, String name, String defaultValue) {
		String value = req.getParameter(name);
		value = value == null ? defaultValue : value;
		int intValue;
		try {
			intValue = Integer.parseInt(value);
		} catch(Exception e) {
			intValue =  Integer.parseInt(defaultValue);
		}
		return intValue;
	}
	
	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			
		}
	}
}
