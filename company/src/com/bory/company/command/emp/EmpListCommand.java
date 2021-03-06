package com.bory.company.command.emp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bory.company.command.common.AbstractCommand;
import com.bory.company.dao.emp.EmpDAO;
import com.bory.company.dao.emp.EmpDAOImpl;
import com.bory.company.dto.Employee;
import com.bory.company.dto.Pagination;
import com.bory.company.exception.CommandExecutionException;
import com.bory.company.pool.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmpListCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
//		if(request!=null) {
//			throw new CommandExecutionException("강제로 오류를 발생시켰습니다.");
//		}

		String pageNum = request.getParameter("pageNum");
		int pn = (pageNum == null) ? 1 : Integer.parseInt(pageNum);

	
		String searchOption = getParameter(request,"searchOption","EMPNO");
		String searchWord = getParameter(request,"searchWord","");
		
		EmpDAO empDAO = new EmpDAOImpl(connection);
		Pagination page = new Pagination(3 ,5);
		
		int count = empDAO.count(searchOption,searchWord);
	
		page.calcPage(pn, count);
		List<Employee> list = empDAO.find(searchOption,searchWord, page);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("employee", list);
		resultMap.put("page", page);
		resultMap.put("searchOption", searchOption);
		resultMap.put("searchWord", searchWord);
		
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(resultMap);
		} catch (JsonProcessingException e) {
			throw new CommandExecutionException();
		}
	
		request.setAttribute("ajaxResponse", json);
		return Constants.AJAX_VIEW;
	}
	
	
	
	
	

}
