<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
	<title>EmployeeList</title>
</head>
<body>
	<h3>��� ���</h3>
	<div id="content">
		<div id="content_search">
			<select name="serch" id="searchOption">
				<option value="all">��ü ��ȸ</option>
				<option value="name">�̸�</option>
				<option value="empno">�����ȣ</option>
				<option value="deptno">�μ���</option>
			</select> 
			<input id="searchWord" type="text" placeholder="�˻��� �Է�" /> 
			<input type="button" id="searchButton" value="�˻�" />
		</div>
		<div id="content_list">
			<table id="employee_list">
				<tr>
					<th>��� ��ȣ</th>
					<th>�� ��</th>
					<th>�� å</th>
					<th>���� ���</th>
					<th>�Ի� ��¥</th>
					<th>�� ��</th>
					<th>�� �ݾ�</th>
					<th>�ش� �μ�</th>
				</tr>
				<c:forEach items='${list}' var='employee'>
					<tr>
						<td><a href ="${context}/empDetail.do?empNo=${employee.empNo}" class="empNo" >${employee.empNo}</a></td>
						<td>${employee.eName}</td>
						<td>${employee.job}</td>
						<td>${employee.mgr}</td>
						<td>${employee.hireDate}</td>
						<td>${employee.sal}</td>
						<td>${employee.comm}</td>
						<td>${employee.deptNo}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script>	
		document.getElementById('searchButton').onclick = function () {
			location.href = ${context}+"/empList.do?option="+document.getElementById('searchOption').value+"&word="+document.getElementById('searchWord').value;
		};		
	</script>
</body>
</html>