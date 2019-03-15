package com.bory.company.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bory.company.dto.Employee;
import com.bory.company.dto.Pagination;
import com.bory.company.exception.DAOException;

import static com.bory.company.dao.DTOFactory.*;
import static com.bory.company.dao.DataResourceCloser.*;

public class CompanyDAOImpl implements CompanyDAO {

	protected final Connection connection;

	public CompanyDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public int insert(Employee employee) {

		PreparedStatement statement = null;
		int successed;

		try {
			statement = connection.prepareStatement(QueryFactory.getQuery("insert"));
			successed = fromEmployee(statement, employee).executeUpdate();
		} catch (Exception e) {
			successed = 0;
		} finally {
			close(statement);
		}

		return successed;
	}

	@Override
	public int update(Employee employee) {
		
		PreparedStatement statement = null;
		int successed = 0;;
		try{
			statement = connection.prepareStatement(QueryFactory.getQuery("update"));

			statement.setString(1, employee.getJob());
			statement.setInt(2, employee.getMgr());
			statement.setInt(3, employee.getSal());
			statement.setInt(4, employee.getComm());
			statement.setInt(5, employee.getDeptNo());
			statement.setInt(6, employee.getEmpNo());

			successed = statement.executeUpdate();

		} catch (Exception e) {
			successed = 0;
		}
		return successed;
	}

	@Override
	public List<Employee> findAll(Pagination page) {

		List<Employee> list = new ArrayList<Employee>();

		PreparedStatement statement = null;
		ResultSet rs = null;

		try {

			statement = connection.prepareStatement(QueryFactory.getQuery("all"));
			statement.setInt(1, page.getBeginRow());
			statement.setInt(2, page.getEndRow());
			rs = statement.executeQuery();

			while (rs.next()) {
				list.add(fromResultSet(rs, Employee.class));
			}

		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(rs, statement);
		}

		return list;
	}

	@Override
	public List<Employee> findSome(String findOption, Pagination page) {
		
		String[] option = findOption.split("/");
		List<Employee> list = new ArrayList<>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			String sql = String.format(QueryFactory.getQuery("some"), option[0]);
			statement = connection.prepareStatement(sql);
			statement.setString(1, option[1]);
			statement.setInt(2, page.getBeginRow());
			statement.setInt(3, page.getEndRow());
			rs = statement.executeQuery();
			
			while(rs.next()) {
				list.add(fromResultSet(rs, Employee.class));
			}
			
		}catch(Exception e) {
			throw new DAOException(e);
		}finally {
			close(rs, statement);
		}
		
		return list;
	}

	@Override
	public Employee findOne(int empNo) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		Employee employee = new Employee();

		try {
			statement = connection.prepareStatement(QueryFactory.getQuery("one"));
			statement.setInt(1, empNo);
			rs = statement.executeQuery();

			while (rs.next()) {
				employee = fromResultSet(rs, Employee.class);
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(rs, statement);
		}
		return employee;
	}

	@Override
	public int countSome(String option) {
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		String searchOption = option.split("/")[0];
		String searchWord = option.split("/")[1];

		try { 
			String sql = String.format(QueryFactory.getQuery("countsome"), searchOption);
			statement = connection.prepareStatement(sql);
			statement.setString(1, searchWord);
			rs = statement.executeQuery();
			if(rs.next()) {
				return rs.getInt("NUM");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(rs, statement);
		}
		return 0;
	}

	@Override
	public int countAll() {
		
		ResultSet rs = null;

		try { 
			rs = connection.prepareStatement(QueryFactory.getQuery("countall")).executeQuery();
			if(rs.next()) {
				return rs.getInt("NUM");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(rs);
		}
		return 0;
	
	}

	@Override
	public boolean exists(String option) {
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean exists = false;
		
		try {
			String sql = String.format(QueryFactory.getQuery("exists"), option.split("/")[0]);
			statement = connection.prepareStatement(sql);
			statement.setString(1, option.split("/")[1]);
			rs = statement.executeQuery();

			exists = rs.next();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally {
			close(rs, statement);
		}
		
		return exists;
	}

	

}
