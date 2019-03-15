package com.bory.company.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bory.company.dao.CompanyDAOImpl;

public class MoveCommand extends AbstractCommand{

	private static final String FOWARD_URL = "/WEB-INF/view/emp/";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String pageName = request.getParameter("pageName");
		String destinationURL = FOWARD_URL+pageName+".jsp";
		
		switch(pageName) {
		case "empUpdate":
			request.setAttribute("employee", new CompanyDAOImpl(connection).findOne(Integer.parseInt(request.getParameter("empNo"))));
			break;
		}
		return destinationURL;
	}

}
