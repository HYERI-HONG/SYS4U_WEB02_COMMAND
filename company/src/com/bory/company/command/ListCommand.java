package com.bory.company.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bory.company.dao.CompanyDAOImpl;
import com.bory.company.dto.Employee;
import com.bory.company.dto.Pagination;

public class ListCommand extends AbstractCommand {

	private static final String LIST_VIEW_NAME = "/WEB-INF/view/emp/empList.jsp";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<Employee> list = new ArrayList<>();
		carrierOption = "forward";
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(connection);
		
		String pageNum=request.getParameter("pageNum");
		int pn =(pageNum==null)? 1 :Integer.parseInt(pageNum);
		
		String searchWord = request.getParameter("searchWord");
		String searchOption = request.getParameter("searchOption");
		
		String check="all";
		if(searchOption==null) {
			check = "all";
		}else if(searchOption.equals("ename")||searchOption.equals("dname")) {
			check = "some";
		}else if(searchOption.equals("empno")) {
			check = "one";
		}

		Pagination page = new Pagination();
	
		switch(check) {
		case "one":
			list.add(companyDAOImpl.findOne(Integer.parseInt(searchWord)));
			page.calcPage(1, 1);
			break;
		case "all":
			page.calcPage(pn,companyDAOImpl.countAll());
			list = companyDAOImpl.findAll(page);
			break;
		case "some":		
			int count = companyDAOImpl.countSome(searchOption+"/"+searchWord);
			page.calcPage(pn, count);
			list = companyDAOImpl.findSome(searchOption+"/"+searchWord, page);
			request.setAttribute("searchOption",searchOption);
			request.setAttribute("searchWord",searchWord);
			break;
		}
		
		request.setAttribute("page",page);
		request.setAttribute("list", list);

		return LIST_VIEW_NAME;
	}
}
