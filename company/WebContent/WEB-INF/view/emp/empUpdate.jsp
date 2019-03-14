<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>empUpdate</title>
	<script>
	var deptNo = document.getElementById('deptNo');
	var job = document.getElementById('job');
	
	for(var i=0; i<deptNo.options.length; i++){
		if(deptNo.options[i].value==='${employ.deptNo}'){
			deptNo.options[i].setAttribute("selected","selected");
		}
	}
	for(var j=0; j<job.options.length; j++){
		if(job.options[j].value==='${employ.job}'){
			job.options[j].setAttribute("selected","selected");
		}
	}
	</script>
</head>
<body>
	<div id="content">
		<div id="content_update">
			<h3>사원 정보 수정</h3>
			<form id="updateForm" action="${context}/empUpdate.do" method="post">
			<table id="updateInfo">
			<tr>
				<td>사원 번호</td>
				<td><input type="text" name ="empNo" value="${employee.empNo}" readonly/></td>
				<td>이       름</td>
				<td><input type="text" name ="eName" value="${employee.eName}" readonly/></td>
			</tr>
			
			<tr>
				<td>직속 상관</td>
				<td><input type="text" name ="mgr" value="${employee.mgr}"/></td>
				<td>입사 날짜</td>
				<td><input type="date" name ="hireDate" value="${employee.hireDate}" readonly/></td>
			</tr>
			
			<tr>
				<td>연      봉</td>
				<td><input type="text" name="sal" value="${employee.sal}"/></td>
				<td>상  여  금</td>
				<td><input type="text" name="comm" value="${employee.comm}"/></td>
			</tr>
			
			<tr>
				<td>부서</td>
				<td>
					<select name="deptNo" id="deptNo">
		    			<option value="10" > Accounting</option>
		    			<option value="20" >Research</option>
		    			<option value="30" >Sales</option>
		    			<option value="40" >Operations</option>
		    		</select>
				<td>직책</td>
				<td>
					<select name="job" id="job">
		    			<option value="CLERK" >Clerk</option>
		    			<option value="SALESMAN" >Salesman</option>
		    			<option value="MANAGER" >manager</option>
		    			<option value="ANALYST" >Analyst</option>
		    			<option value="PRESIDENT" >President</option>
		    		</select>
				</td>
			</tr>
			<tr>
				<td colspan="3"><td>
				<td><input id="updateFormBtn" type="submit" value="변경" />
				<input type="button" onclick="javascript:history.back();" value="취소"></td>
			</tr>
			</table>
		</form>
		</div>
	</div>
</body>
</html>