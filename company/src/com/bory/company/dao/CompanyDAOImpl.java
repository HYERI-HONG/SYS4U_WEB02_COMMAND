package com.bory.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bory.company.dto.Employee;
import static com.bory.company.dao.DTOFactory.*;
import static com.bory.company.dao.DataResourceCloser.*;

public class CompanyDAOImpl implements CompanyDAO {

	protected final Connection connection;

	public CompanyDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public int insert(Employee employee) {

		System.out.println("insert da에 진입");
		PreparedStatement statement = null;
		int successed;

		try {
			statement = connection.prepareStatement(QueryFactory.getQuery("insert"));
			successed = fromEmployee(statement, employee).executeUpdate();
		} catch (SQLException e) {
			successed = 0;
		} finally {
			close(statement);
		}

		return successed;
	}

	@Override
	public int update(Employee employeeBean) {
		return 0;
	}

	@Override
	public List<Employee> findAll() {

		List<Employee> list = new ArrayList<Employee>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = connection.prepareStatement(QueryFactory.getQuery("all"));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(fromResultSet(rs, Employee.class));
			}

		} catch (Exception e) {

		} finally {
			close(rs, pstmt);
		}

		return list;
	}

	@Override
	public List<Employee> findSome(Map<String, String> findOption) {
		return null;
	}

	@Override
	public Employee findOne(int empNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee employee = new Employee();
		
		try {

			pstmt = connection.prepareStatement(QueryFactory.getQuery("findOne"));
			pstmt.setInt(1, empNo);
			rs = pstmt.executeQuery();	
			
			while (rs.next()) {
					employee.setEmpNo(rs.getInt("EMPNO"));
					employee.seteName(rs.getString("ENAME"));
					employee.setJob(rs.getString("JOB"));
					employee.setMgr(rs.getInt("MGR"));
					employee.setHireDate(rs.getDate("HIREDATE"));
					employee.setSal(rs.getInt("SAL"));
					employee.setComm(rs.getInt("COMM"));
					employee.setDeptNo(rs.getInt("DEPTNO"));		
			}
		} catch (Exception e) {

		} finally {
			close(rs, pstmt);
		}
		return employee;
	}

	@Override
	public int count() {
		return 0;
	}

}
