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

	private static final String LIST_VIEW_NAME = "/WEB-INF/view/empList.jsp";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("ListCommand ");
		List<Employee> list = new ArrayList<>();
		carrierOption = "forward";
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(connection);
	
		System.err.println("searchWord : "+request.getParameter("searchWord"));
		System.err.println("searchoption : "+request.getParameter("serchOption"));
		
		
		String pageNum=request.getParameter("pageNum");
		int pn =(pageNum==null)? 1 :Integer.parseInt(pageNum);
		
		String searchWord = request.getParameter("searchWord");
		String serchOption = request.getParameter("serchOption");
		if(serchOption==null) {
			serchOption = "all";
		}else if(serchOption=="ename"||serchOption=="dname") {
			serchOption = "some";
		}
				
	
		
		Pagination page = new Pagination();
	
		switch(serchOption) {
		case "empno":
			list.add(companyDAOImpl.findOne(Integer.parseInt(searchWord)));
			page.calcPage(1, 1);
			break;
		case "all":
			page.calcPage(pn,companyDAOImpl.countAll());
			list = companyDAOImpl.findAll(page);
			break;
		case "some":
			System.out.println("searchOption : "+serchOption);
			System.out.println("searchWord : "+searchWord);
			
			int count = companyDAOImpl.countSome(serchOption+"/"+searchWord);
			System.out.println("count : "+count);
			page.calcPage(pn, count);
			list = companyDAOImpl.findSome(serchOption+"/"+searchWord, page);
			System.out.println("결과 개수 "+list.size());
			request.setAttribute("searchOption",serchOption);
			request.setAttribute("searchWord",searchWord);
			break;
		}
		
		request.setAttribute("page",page);
		request.setAttribute("list", list);

		return LIST_VIEW_NAME;
	}
}
