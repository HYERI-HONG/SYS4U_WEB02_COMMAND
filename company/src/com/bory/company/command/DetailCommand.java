package com.bory.company.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bory.company.dao.CompanyDAOImpl;
import com.bory.company.dto.Employee;

public class DetailCommand extends AbstractCommand {

	private static final String FOWARD_URL = "/WEB-INF/view/emp/empDetail.jsp";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(connection);
		Employee employee = companyDAOImpl.findOne(Integer.parseInt(request.getParameter("empNo")));
		request.setAttribute("employee", employee);
		
		return FOWARD_URL;
	}

	

}
