package com.bory.company.dao.dept;

import java.util.List;

import com.bory.company.dto.Department;
import com.bory.company.dto.Pagination;

public interface DeptDAO {
	
	public List<Department> findAll(Pagination page);
	public int count();
}
