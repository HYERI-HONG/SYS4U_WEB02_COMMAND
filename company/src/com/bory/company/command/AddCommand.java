package com.bory.company.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bory.company.dao.CompanyDAOImpl;
import com.bory.company.dto.Employee;

public class AddCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("----------AddCommand----------");
		String viewName = "/company/empAdd.do";
		carrierOption = "redirect";
		
		Employee employee = setEmployee(request);	
		if(new CompanyDAOImpl(connection).insert(employee)==1) {
			viewName="/company/empList.do?option=all";
		}
		
		return viewName;
	}
	
	

}
