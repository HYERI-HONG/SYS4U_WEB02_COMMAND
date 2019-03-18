package com.bory.company.command.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bory.company.command.common.AbstractCommand;
import com.bory.company.dao.emp.EmpDAOImpl;
import com.bory.company.dto.Employee;
import com.bory.company.exception.CommandExecutionException;
import com.bory.company.pool.Constants;

public class EmpUpdateCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		StringBuilder redirectUrl = new StringBuilder();
		redirectUrl.append("/company/empDetail.do?empno=").append(request.getParameter("empno"));

		Employee employee = new Employee();
		employee.create(request);
		int successed = new EmpDAOImpl(connection).update(employee);

		if (successed == 1) {
			request.setAttribute("redirectUrl", redirectUrl.toString());
			request.setAttribute("message", "성공적으로 수정되었습니다.");
			return Constants.REDIRECT_VIEW;
		}
		//실패 했을 경우
		//conn.rollback();
		
		throw new CommandExecutionException("Employee data[" + employee.getEname() + "] not updated.");
	}

}
