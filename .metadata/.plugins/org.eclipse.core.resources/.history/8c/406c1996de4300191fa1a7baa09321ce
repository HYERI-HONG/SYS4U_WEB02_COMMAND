package dao;

public class QueryFactory {
	
	public static String getQuery(String option) {

		String query = "";
		
		switch (option) {
		case "all":
			query = "SELECT * FROM EMP ORDER BY HIREDATE DESC";
			break;
		case "name":
			query = "SELECT * FROM EMP WHERE ENAME LIKE '%' || ? || '%'";
			break;
		case "empno":
			query = "SELECT * FROM EMP WHERE EMPNO = ?";
			break;
		case "deptno":
			query = "SELECT E.* FROM EMP E WHERE E.DEPTNO = (SELECT D.DEPTNO FROM DEPT D WHERE DNAME = ?)";
			break;
		}
		return query;
	}
}
