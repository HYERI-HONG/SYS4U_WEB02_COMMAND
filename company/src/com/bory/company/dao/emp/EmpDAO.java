package com.bory.company.dao.emp;

import java.util.List;
import com.bory.company.dto.Employee;
import com.bory.company.dto.Pagination;

public interface EmpDAO {
	
	public int insert(Employee employee);
	public int update(Employee employee);
	public List<Employee> find(String column,String value, Pagination page);
	public Employee findOne(int value);
	public int count(String column,String value);
	public boolean exists(String column,String value);
}
