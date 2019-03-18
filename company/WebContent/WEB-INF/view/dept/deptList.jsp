<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
	<title>DepartmentList</title>
</head>
<body>
	<h3>부서 목록</h3>
	<div id="content">
		<div id="content_list">
			<table id="department_list">
				<tr>
					<th>부서 번호</th>
					<th>부 서 명</th>
					<th>부서 위치</th>
				</tr>
				<c:forEach items='${list}' var='department'>
					<tr>
						<td>${department.deptno}</td>
						<td>${department.dname}</td>
						<td>${department.loc}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3" style="height: 50px;">
						<jsp:include page="/WEB-INF/view/common/pagination.jsp" >
							<jsp:param value="dept" name="domain"/>
						</jsp:include>
					</td>				
				<tr>
			</table>
		</div>
	</div>
</body>
</html>