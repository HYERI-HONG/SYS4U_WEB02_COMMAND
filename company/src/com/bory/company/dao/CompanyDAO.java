package com.bory.company.dao;

import java.util.List;
import com.bory.company.dto.Employee;
import com.bory.company.dto.Pagination;

public interface CompanyDAO {
	
	public int insert(Employee employee);
	public int update(Employee employee);
	public List<Employee> findAll(Pagination page);
	public List<Employee> findSome(String option, Pagination page);
	public Employee findOne(int empno);
	public int countAll();
	public int countSome(String option);
	public boolean exists(String option);
}
