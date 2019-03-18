package com.bory.company.dao.emp;

import static com.bory.company.dao.common.DTOFactory.*;
import static com.bory.company.dao.common.DataResourceCloser.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bory.company.dto.Employee;
import com.bory.company.dto.Pagination;
import com.bory.company.exception.DAOException;

public class EmpDAOImpl implements EmpDAO {

	protected final Connection connection;

	public EmpDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public int insert(Employee employee) {

		PreparedStatement statement = null;
		int successed;

		try {
			statement = connection.prepareStatement(EmpQueryFactory.getQuery("insert"));
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
			statement = connection.prepareStatement(EmpQueryFactory.getQuery("update"));

			statement.setString(1, employee.getJob());
			statement.setInt(2, employee.getMgr());
			statement.setInt(3, employee.getSal());
			statement.setInt(4, employee.getComm());
			statement.setInt(5, employee.getDeptno());
			statement.setInt(6, employee.getEmpno());

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

			statement = connection.prepareStatement(EmpQueryFactory.getQuery("all"));
			statement.setInt(1, page.getEndRow());
			statement.setInt(2, page.getBeginRow());
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
			String sql = String.format(EmpQueryFactory.getQuery("some"), option[0]);
			statement = connection.prepareStatement(sql);
			statement.setString(1, option[1]);
			statement.setInt(2, page.getEndRow());
			statement.setInt(3, page.getBeginRow());
			
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
	public Employee findOne(int empno) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		Employee employee = new Employee();

		try {
			statement = connection.prepareStatement(EmpQueryFactory.getQuery("one"));
			statement.setInt(1, empno);
			rs = statement.executeQuery();

			while (rs.next()) {
				employee = fromResultSet(rs, Employee.class);
				return employee;
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(rs, statement);
		}
		return null;
	}

	@Override
	public int countSome(String option) {
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		String searchOption = option.split("/")[0];
		String searchWord = option.split("/")[1];

		try { 
			String sql = String.format(EmpQueryFactory.getQuery("countsome"), searchOption);
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
			rs = connection.prepareStatement(EmpQueryFactory.getQuery("countall")).executeQuery();
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
			String sql = String.format(EmpQueryFactory.getQuery("exists"), option.split("/")[0]);
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
