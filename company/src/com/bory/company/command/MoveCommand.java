package com.bory.company.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bory.company.dao.CompanyDAOImpl;

public class MoveCommand extends AbstractCommand{

	private static final String VIEW_NAME = "/WEB-INF/view/";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("----------MoveCommand----------");
		carrierOption = "forward";
		String pageName = request.getParameter("pageName");
		
		String moveViewName = VIEW_NAME+pageName+".jsp";
		System.out.println(pageName);
		switch(pageName) {
		case "empUpdate":
			request.setAttribute("employee", new CompanyDAOImpl(connection).findOne(Integer.parseInt(request.getParameter("empNo"))));
			break;
		}
		return moveViewName;
	}

}
