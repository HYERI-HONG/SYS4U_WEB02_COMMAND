package com.bory.company.command.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bory.company.command.common.AbstractCommand;
import com.bory.company.dao.emp.EmpDAOImpl;
import com.bory.company.dto.Employee;

public class EmpDetailCommand extends AbstractCommand {

	private static final String FOWARD_URL = "/WEB-INF/view/emp/empDetail.jsp";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		EmpDAOImpl companyDAOImpl = new EmpDAOImpl(connection);
		Employee employee = companyDAOImpl.findOne(Integer.parseInt(request.getParameter("empno")));
		request.setAttribute("employee", employee);
		
		return FOWARD_URL;
	}

	

}
