package com.bory.company.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bory.company.dto.Employee;

import java.lang.reflect.Field;

public class DTOFactory {

	public static PreparedStatement fromEmployee(PreparedStatement statement, Employee employee) throws SQLException {

		statement.setInt(1, employee.getEmpNo());
		statement.setString(2, employee.geteName());
		statement.setString(3, employee.getJob());
		statement.setInt(4, employee.getMgr());
		statement.setDate(5, employee.getHireDate());
		statement.setInt(6, employee.getSal());
		statement.setInt(7, employee.getComm());
		statement.setInt(8, employee.getDeptNo());

		return statement;

	}

	public static <T> T fromResultSet(ResultSet rs, Class<T> clazz) throws Exception {
		T instance = clazz.newInstance();

		Field[] fields = Employee.class.getDeclaredFields();
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