package com.bory.company.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCommand extends AbstractCommand{

	private static final String ADD_VIEW_NAME = "/WEB-INF/view/empAdd.jsp";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return ADD_VIEW_NAME;
	}
	
	

}
