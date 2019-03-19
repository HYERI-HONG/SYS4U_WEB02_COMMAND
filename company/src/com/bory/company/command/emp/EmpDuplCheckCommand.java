package com.bory.company.command.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bory.company.command.common.AbstractCommand;
import com.bory.company.dao.emp.EmpDAOImpl;
import com.bory.company.pool.Constants;


public class EmpDuplCheckCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		EmpDAOImpl companyDAOImpl = new EmpDAOImpl(connection);
		String ename = request.getParameter("ename");
		boolean exists = false;
		
		exists = companyDAOImpl.exists("ENAME", ename);
		request.setAttribute("ajaxResponse", exists? "exists" : "nonexists");
		
		return Constants.AJAX_VIEW;
	}

}
