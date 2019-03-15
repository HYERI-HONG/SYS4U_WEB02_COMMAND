package com.bory.company.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bory.company.dao.CompanyDAOImpl;
import com.bory.company.pool.Constants;


public class DuplCheckCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(connection);
		String ename = request.getParameter("ename");
		boolean exists = false;
		
		exists = companyDAOImpl.exists("ENAME/"+ename);
		request.setAttribute("ajaxResponse", exists? "exists" : "nonexists");
		
		return Constants.AJAX_VIEW;
	}

}
