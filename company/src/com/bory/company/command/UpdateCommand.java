package com.bory.company.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bory.company.dao.CompanyDAOImpl;
import com.bory.company.dto.Employee;

public class UpdateCommand extends AbstractCommand{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		carrierOption = "redirect";
		String viewName = "/company/empDetail.do?empNo="+request.getParameter("empNo");
		
		Employee employee = new Employee();
		employee.create(request);
		new CompanyDAOImpl(connection).update(employee);
	
		return viewName;
	}

}
