package com.bory.company.command.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bory.company.command.common.AbstractCommand;
import com.bory.company.dao.emp.EmpDAOImpl;
import com.bory.company.dto.Employee;

public class EmpMoveCommand extends AbstractCommand{

	private static final String FOWARD_URL = "/WEB-INF/view/emp/";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String pageName = request.getParameter("pageName");
		StringBuilder destinationURL = new StringBuilder();
		destinationURL.append(FOWARD_URL).append(pageName).append(".jsp");
		
		switch(pageName) {
		case "empUpdate":
			Employee e = new EmpDAOImpl(connection).findOne(Integer.parseInt(request.getParameter("empno")));
			request.setAttribute("employee", e);
			request.setAttribute("formType", "update");
			request.setAttribute("formName", "사원 정보 수정");
			destinationURL.delete(0, destinationURL.length());
			destinationURL.append(FOWARD_URL).append("empAddUpdate.jsp");
			break;
		case "empAdd":
			request.setAttribute("formType", "add");
			request.setAttribute("formName", "신입 사원 등록");
			destinationURL.delete(0, destinationURL.length());
			destinationURL.append(FOWARD_URL).append("empAddUpdate.jsp");
			break;
		}
		return destinationURL.toString();
	}
	
	

}
