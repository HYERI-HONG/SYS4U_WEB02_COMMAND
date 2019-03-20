package com.bory.company.command.emp;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bory.company.command.common.AbstractCommand;
import com.bory.company.dao.emp.EmpDAOImpl;
import com.bory.company.dto.Employee;
import com.bory.company.exception.CommandExecutionException;
import com.bory.company.exception.FailToReadJsonFromRequestException;
import com.bory.company.pool.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;


public class EmpAddCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		ObjectMapper mapper = new ObjectMapper();
		Employee employee = null;
        try {
         employee = mapper.readValue(request.getInputStream(), Employee.class);
      } catch (IOException e) {
         throw new FailToReadJsonFromRequestException();
      }

       int successed = new EmpDAOImpl(connection).insert(employee);
       if(successed>0) {

    	   	request.setAttribute("ajaxResponse", "SUCCESS");
   			return null;
		}
		
		throw new CommandExecutionException("Employee data[" + employee.getEname() + "] not inserted.");
       
	}
}
