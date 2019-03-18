package com.bory.company.dao.emp;

public class EmpQueryFactory {
	
	public static String getQuery(String option) {

		String query = "";
		
		switch (option) {
		case "insert":
			query = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)" 
					+ " VALUES(?,?,?,?,?,?,?,?)";
			break;
		case "update":
			query = "UPDATE EMP SET JOB = ?, MGR = ?, SAL = ?, COMM = ?, DEPTNO = ? WHERE EMPNO = ?";
			break;
		case "one":
			query = "SELECT E.ENAME, E.JOB, E.EMPNO, E.MGR, E.SAL, E.COMM, E.DEPTNO, E.HIREDATE, D.DNAME "
					+ " FROM EMP E, DEPT D "
					+ " WHERE E.DEPTNO = D.DEPTNO"
					+ " AND EMPNO = ?";
			break;
		case "all":
			query = "SELECT T.EMPNO, T.ENAME, T.JOB, T.MGR, T.HIREDATE, T.SAL, T.COMM, T.DEPTNO, T.DNAME"
					+ " FROM (SELECT ROWNUM SEQ, E.*, D.DNAME"
					+ " FROM (SELECT *"
					+ " FROM EMP"
					+ " ORDER BY HIREDATE DESC) E, DEPT D"
					+ " WHERE E.DEPTNO = D.DEPTNO"
					+ " AND ROWNUM <= ?) T"
					+ " WHERE ? <= T.SEQ";
			break;
		case "some":
			query = "SELECT T.EMPNO, T.ENAME, T.JOB, T.MGR, T.HIREDATE, T.SAL, T.COMM, T.DEPTNO, T.DNAME "
					+ " FROM ("
					+ " SELECT ROWNUM AS RNUM, E.*, D.DNAME "
					+ " FROM (SELECT * FROM EMP" 
					+ " ORDER BY HIREDATE DESC) E, DEPT D" 
					+ " WHERE E.DEPTNO = D.DEPTNO" 
					+ " AND %s LIKE ? "
					+ " AND ROWNUM <= ?) T" 
					+ " WHERE ? <= T.RNUM";
			break;
		case "countsome":
			query = "SELECT COUNT(*) AS NUM" + 
					" FROM EMP E, DEPT D" + 
					" WHERE E.DEPTNO = D.DEPTNO" + 
					" AND %s LIKE ?";
			break;
		case "countall":
			query = "SELECT COUNT(1) AS NUM" + 
					" FROM EMP";
			break;
		case "exists":
			query = "SELECT 1 FROM DUAL WHERE EXISTS (SELECT 1 FROM EMP WHERE %s = ?)";
			break;
			
		}
		return query;
	}
}
