package com.bory.company.command.dept;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bory.company.command.common.AbstractCommand;
import com.bory.company.dao.dept.DeptDAOImpl;
import com.bory.company.dto.Department;
import com.bory.company.dto.Pagination;

public class DeptListCommand extends AbstractCommand {

	private static final String FOWARD_URL = "/WEB-INF/view/dept/deptList.jsp";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		List<Department> list = new ArrayList<>();
		DeptDAOImpl deptDAOImpl = new DeptDAOImpl(connection);

		String pageNum = request.getParameter("pageNum");
		int pn = (pageNum == null) ? 1 : Integer.parseInt(pageNum);
		
		Pagination page = new Pagination(2,2);
		page.calcPage(pn, deptDAOImpl.count());
		list = deptDAOImpl.findAll(page);
		
		request.setAttribute("page", page);
		request.setAttribute("list", list);

		return FOWARD_URL;
	}
}
