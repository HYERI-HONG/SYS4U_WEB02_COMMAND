<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script type="text/javascript" src="${context}/resources/js/emp/empAddUpdate.js"></script>
</head>
<body>
	<div id="content">
		<div id="content_update">
			<h3>${formName}</h3>
			<form id="${formType}Form" name="${formType}Form">
			<table>
			<tr>
				<td>사원 번호</td>
				<td><input type="text" name ="empno"/></td>
				<td>이       름</td>
				<td><input type="text" name ="ename"/>
				<input type="button" name="duplCheckBtn" id="duplCheckBtn" value="중복확인" style="visibility:hidden">
				</td>
			</tr>
			
			<tr>
				<td>직속 상관</td>
				<td><input type="text" name ="mgr"/></td>
				<td>입사 날짜</td>
				<td><input type="date" name ="hiredate"/></td>
			</tr>
			
			<tr>
				<td>연      봉</td>
				<td><input type="text" name="sal" /></td>
				<td>상  여  금</td>
				<td><input type="text" name="comm" /></td>
			</tr>
			
			<tr>
				<td>부서</td>
				<td>
					<select name="deptno" >
		    			<option class = "deptno-opt" value="10" > Accounting</option>
		    			<option class = "deptno-opt" value="20" >Research</option>
		    			<option class = "deptno-opt" value="30" >Sales</option>
		    			<option class = "deptno-opt" value="40" >Operations</option>
		    		</select>
				<td>직책</td>
				<td>
					<select name="job" >
		    			<option class = "job-opt" value="CLERK" >Clerk</option>
		    			<option class = "job-opt" value="SALESMAN" >Salesman</option>
		    			<option class = "job-opt" value="MANAGER" >manager</option>
		    			<option class = "job-opt" value="ANALYST" >Analyst</option>
		    			<option class = "job-opt" value="PRESIDENT" >President</option>
		    		</select>
				</td>
			</tr>
			<tr>
				<td colspan="3"><td>
				<td><input id="${formType}FormBtn" type="button" value="확인" />
				<input type="button" onclick= "javascript:history.back();" value="취소"></td>
			</tr>
			</table>
		</form>
		</div>
	</div>
	<script>
	var employee = {empno : "${employee.empno}",
					ename : "${employee.ename}",
					mgr : "${employee.mgr}",
					hiredate : "${employee.hiredate}",
					sal : "${employee.sal}",
					comm : "${employee.comm}",
					deptno : "${employee.deptno}",
					job : "${employee.job}",
					context : "${context}"}
						
	if('${formType}'==='update'){
		emp.loadUpdateForm(employee);
	}else{
		emp.loadAddForm('${context}');
	}	
	</script>
</body>
</html>