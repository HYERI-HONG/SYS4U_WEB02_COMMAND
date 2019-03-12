<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>empDetail</title>
</head>
<body>
<div id="content">
		<div id="content_detail">
		<h3>사원 정보 조회</h3>
			<table id="empInfo">
			<tr>
				<td>사원 번호</td>
				<td>${employee.empNo}</td>
				<td>이       름</td>
				<td>${employee.eName}</td>
			</tr>
				
			<tr>
				<td>직      책</td>
				<td>${employee.job}</td>
				<td>부      서</td>
				<td>${employee.deptNo}</td>
			</tr>
			
			<tr>
				<td>직속 상관</td>
				<td>${employee.mgr}</td>
				<td>입사 날짜</td>
				<td>${employee.hireDate}</td>
			</tr>
			
			<tr>
				<td>연      봉</td>
				<td>${employee.sal}</td>
				<td>상  여  금</td>
				<td>${employee.comm}</td>
			</tr>
			</table>
			<input type="button" value="사원 정보 수정" onclick="javascript:location.href='${context}/move.do?pageName=empUpdate&empNo=${employee.empNo}';"/>	
		</div>
</div>
</body>
</html>