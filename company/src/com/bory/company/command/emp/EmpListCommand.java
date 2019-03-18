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

		List<Employee> list = new ArrayList<>();
		EmpDAO empDAO = new EmpDAOImpl(connection);

		String pageNum = request.getParameter("pageNum");
		int pn = (pageNum == null) ? 1 : Integer.parseInt(pageNum);
	
		String searchWord = request.getParameter("searchWord");
		String searchOption = request.getParameter("searchOption");

		String check = "all";
		if (searchOption == null) {
			check = "all";
		}else if (searchOption.equals("ename") || searchOption.equals("dname")) {
			check = "some";
		} else if (searchOption.equals("empno")) {
			check = "one";
		}
		
		
		Pagination page = new Pagination();
		int count;


		switch (check) {
		case "one":
			//검색어로 숫자가 아닌 문자열을 입력한 경우 처리 방법?
			Employee employee = empDAO.findOne(Integer.parseInt(searchWord));
			list.add(employee);
			count = 1;
			if(employee == null) {
				count = 0;
			}
			page.calcPage(1, count);
			
			break;
		case "some":
			count = empDAO.countSome(searchOption + "/" + searchWord);
			page.calcPage(pn, count);
			list = empDAO.findSome(searchOption + "/" + searchWord, page);
			break;
		case "all":
			page.calcPage(pn, empDAO.countAll());
			list = empDAO.findAll(page);
			break;
		}
		
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("searchWord", searchWord);

		request.setAttribute("page", page);
		request.setAttribute("list", list);

		return FOWARD_URL;
	}
}
