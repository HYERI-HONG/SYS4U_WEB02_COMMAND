package com.bory.company.dao.emp;

public class EmpQueryFactory {
	
	public static String getQuery(String option, String column) {

		StringBuilder query = new StringBuilder();
		
		switch (option) {
		case "insert":
			query.append("INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)")
				 .append(" VALUES(?,?,?,?,?,?,?,?)");
			break;
		case "update":
			query.append("UPDATE EMP SET JOB = ?, MGR = ?, SAL = ?, COMM = ?, DEPTNO = ? WHERE EMPNO = ?");
			break;
		case "find":
			query.append("SELECT T.EMPNO, T.ENAME, T.JOB, T.MGR, T.HIREDATE, T.SAL, T.COMM, T.DEPTNO, T.DNAME ")
			     .append(" FROM (")
			     .append(" SELECT ROWNUM AS RNUM, E.*, D.DNAME ")
			     .append(" FROM (SELECT * FROM EMP")
			     .append(" ORDER BY HIREDATE DESC) E, DEPT D")
			     .append(" WHERE E.DEPTNO = D.DEPTNO")
			     .append(" AND "+column+" LIKE '%' || UPPER(?) || '%' ")
			     .append(" AND ROWNUM <= ?) T").append(" WHERE ? <= T.RNUM");
			break;
		case "findOne":
			query.append("SELECT E.ENAME, E.JOB, E.EMPNO, E.MGR, E.SAL, E.COMM, E.DEPTNO, E.HIREDATE, D.DNAME")
				 .append(" FROM EMP E, DEPT D")
				 .append(" WHERE E.DEPTNO = D.DEPTNO")
				 .append(" AND E.EMPNO = ?");
			break;
		case "count":
			query.append("SELECT COUNT(*) AS NUM")
				 .append(" FROM EMP E, DEPT D")
				 .append(" WHERE E.DEPTNO = D.DEPTNO")
				 .append(" AND "+column+" LIKE '%' || UPPER(?) || '%' ");
			break;
		case "exists":
			query.append("SELECT 1 FROM DUAL WHERE EXISTS (SELECT 1 FROM EMP WHERE "+column+" = ?)");
			break;
		}
		return query.toString();
	}
}
