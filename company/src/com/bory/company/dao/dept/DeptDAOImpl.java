package com.bory.company.dao.dept;

import static com.bory.company.dao.common.DTOFactory.*;
import static com.bory.company.dao.common.DataResourceCloser.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bory.company.dto.Department;
import com.bory.company.dto.Pagination;
import com.bory.company.exception.DAOException;

public class DeptDAOImpl implements DeptDAO{
	private Connection connection;
	
	public DeptDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Department> findAll(Pagination page) {
		
		List<Department> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try{
			statement = connection.prepareStatement(DeptQueryFactory.getQuery("all"));
			statement.setInt(1, page.getEndRow());
			statement.setInt(2, page.getBeginRow());
			
			rs = statement.executeQuery();
			
			while(rs.next()) {
				list.add(fromResultSet(rs,Department.class));
			}
		}catch(Exception e) {
			throw new DAOException();
		}finally {
			close(rs, statement);
		}
		return list;
	}

	@Override
	public int count() {
	
		ResultSet rs = null;

		try { 
			rs = connection.prepareStatement(DeptQueryFactory.getQuery("count")).executeQuery();
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

}
