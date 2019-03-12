package com.bory.company.command;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bory.company.dto.Employee;

public abstract class AbstractCommand implements Command{

	protected Connection connection;
	protected String carrierOption;

	@Override
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	@Override
	public String getCarrierOption() {
		return carrierOption;
	}
	protected String getParameter(HttpServletRequest req, String name, String defaultValue) {
		String value = req.getParameter(name);
		return value == null ? defaultValue : value;
	}
	
	protected int getIntParameter(HttpServletRequest req, String name, String defaultValue) {
		String value = req.getParameter(name);
		value = value == null ? defaultValue : value;
		int intValue;
		try {
			intValue = Integer.parseInt(value);
		} catch(Exception e) {
			intValue =  Integer.parseInt(defaultValue);
		}
		return intValue;
	}
	
	protected Employee setEmployee(HttpServletRequest request) {
		Employee employee = new Employee();
		employee.setComm(Integer.parseInt(request.getParameter("comm"))); 	
		employee.setDeptNo(Integer.parseInt(request.getParameter("deptNo")));
		employee.setEmpNo(Integer.parseInt(request.getParameter("empNo")));
		employee.seteName(request.getParameter("eName"));
		employee.setHireDate(request.getParameter("hireDate"));
		employee.setJob(request.getParameter("job"));
		employee.setMgr(Integer.parseInt(request.getParameter("mgr")));
		employee.setSal(Integer.parseInt(request.getParameter("sal")));
		
		return employee;
	}
	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			
		}
	}
}
