package com.bory.company.command.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bory.company.command.common.AbstractCommand;


public class EmpMoveCommand extends AbstractCommand{

	private static final String FOWARD_URL = "/WEB-INF/view/emp/";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String pageName = request.getParameter("pageName");
		StringBuilder destinationURL = new StringBuilder();
		destinationURL.append(FOWARD_URL).append(pageName).append(".jsp");
		
		switch(pageName) {
		case "empList":
			request.setAttribute("searchOption", "empno");
			request.setAttribute("searchWord", "");
		}
		return destinationURL.toString();
	}
	
	

}
