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
			request.setAttribute("employee", new CompanyDAOImpl(connection).findOne(Integer.parseInt(request.getParameter("empno"))));
			request.setAttribute("formType", "update");
			request.setAttribute("formName", "사원 정보 수정");
			destinationURL =  FOWARD_URL+"empAddUpdate.jsp";
			break;
		case "empAdd":
			request.setAttribute("formType", "add");
			request.setAttribute("formName", "신입 사원 등록");
			destinationURL =  FOWARD_URL+"empAddUpdate.jsp";
			break;
		}
		return destinationURL;
	}

}
