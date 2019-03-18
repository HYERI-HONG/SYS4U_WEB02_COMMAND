package com.bory.company.dao.common;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bory.company.dto.Employee;

import java.lang.reflect.Field;

public class DTOFactory {

	public static PreparedStatement fromEmployee(PreparedStatement statement, Employee employee) throws SQLException {

		statement.setInt(1, employee.getEmpno());
		statement.setString(2, employee.getEname());
		statement.setString(3, employee.getJob());
		statement.setInt(4, employee.getMgr());
		statement.setDate(5, employee.getHiredate());
		statement.setInt(6, employee.getSal());
		statement.setInt(7, employee.getComm());
		statement.setInt(8, employee.getDeptno());

		return statement;

	}

	public static <T> T fromResultSet(ResultSet rs, Class<T> clazz) throws Exception {
		T instance = clazz.newInstance();

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals("serialVersionUID")) {
				continue;
			}
			field.setAccessible(true);
			String columnName = field.getName().toUpperCase();
			Class<?> type = field.getType();
			if (type == int.class) {
				field.set(instance, rs.getInt(columnName));
			} else if (type == String.class) {
				field.set(instance, rs.getString(columnName));
			} else if (type == Date.class) {
				field.set(instance, rs.getDate(columnName));
			}
		}
		return instance;
	}

}