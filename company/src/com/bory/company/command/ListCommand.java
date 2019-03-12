package com.bory.company.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bory.company.dao.CompanyDAOImpl;

public class ListCommand extends AbstractCommand {

	private static final String LIST_VIEW_NAME = "/WEB-INF/view/empList.jsp";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		carrierOption = "forward";
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(connection);
		request.setAttribute("list",companyDAOImpl.findAll());
		if(request.getParameter("option").equals("all")) {
			request.setAttribute("list",companyDAOImpl.findAll());
		}else {
			Map<String, String> findInfo = new HashMap<>();
			findInfo.put("findOption", request.getParameter("option"));
			findInfo.put("findWord", request.getParameter("word"));
			request.setAttribute("list",companyDAOImpl.findSome(findInfo));
		}
		return LIST_VIEW_NAME;
	}
}