package com.bory.company.dao.dept;

public class DeptQueryFactory {
	
	public static String getQuery(String option){
		String query = "";
		
		switch(option) {
		case "all" :
			query = "SELECT T.*" 
					+ " FROM (SELECT ROWNUM SEQ, E.*"  
					+ " FROM (SELECT * FROM DEPT" 
					+ " ORDER BY DEPTNO DESC) E "
					+ " WHERE ROWNUM <= ? ) T"  
					+ " WHERE ? <= T.SEQ";
			break;
		case "count" :
			query = "SELECT COUNT(1) AS NUM" + 
					" FROM DEPT";
			break;
		
		}
		return query;
	}

}
