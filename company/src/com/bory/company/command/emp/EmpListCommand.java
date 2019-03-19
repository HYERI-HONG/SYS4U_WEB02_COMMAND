package com.bory.company.command.emp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bory.company.command.common.AbstractCommand;
import com.bory.company.dao.emp.EmpDAO;
import com.bory.company.dao.emp.EmpDAOImpl;
import com.bory.company.dto.Employee;
import com.bory.company.dto.Pagination;

public class EmpListCommand extends AbstractCommand {

	private static final String FOWARD_URL = "/WEB-INF/view/emp/empList.jsp";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String pageNum = request.getParameter("pageNum");
		int pn = (pageNum == null) ? 1 : Integer.parseInt(pageNum);
	
		String searchOption = getParameter(request,"searchOption","EMPNO");
		String searchWord = getParameter(request,"searchWord","");
		
		EmpDAO empDAO = new EmpDAOImpl(connection);
		Pagination page = new Pagination(3 ,5);
		
		int count = empDAO.count(searchOption,searchWord);
		page.calcPage(pn, count);
		List<Employee> list = empDAO.find(searchOption,searchWord, page);
		
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("searchWord", searchWord);

		request.setAttribute("page", page);
		request.setAttribute("list", list);

		return FOWARD_URL;
	}
}
