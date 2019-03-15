package com.bory.company.command;

import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	
	public void setConnection(Connection connection);
	public String execute(HttpServletRequest request, HttpServletResponse response);
	public void destroy();
	
}
