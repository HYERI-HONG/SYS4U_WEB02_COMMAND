<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
	<title>EmployeeList</title>
	<script type="text/javascript" src="${context}/resources/js/emp/empList.js" ></script>
</head>
<body>
	<h3>사원 목록</h3>
	<div id="content">
		<div id="content_search">
		<form  name="searchForm" style="display: inline;">
			<select name="searchOption">
				<option value="all">전체 조회</option>
				<option value="ename">이름</option>
				<option value="empno">사원번호</option>
				<option value="dname">부서명</option>
			</select> 
			<input name="searchWord" type="text" placeholder="검색어 입력"/> 
			<input type="button" id="searchButton" value="검색" />
		</form>	
		<input id= "context" type="hidden" value="${context}"/>
		<input id="addBtn" type="button" value="사원 등록" />
		</div>
		<div id="content_list">
			<table id="employee_list">
				<tr>
					<th>사원 번호</th>
					<th>이 름</th>
					<th>입사 날짜</th>
					<th>해당 부서</th>
				</tr>
				<c:forEach items='${list}' var='employee'>
					<tr>
						<td><a href ="${context}/empDetail.do?empno=${employee.empno}" class="empno" style="text-decoration: none; color: black;">${employee.empno}</a></td>
						<td>${employee.ename}</td>
						<td>${employee.hiredate}</td>
						<td>${employee.dname}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" style="height: 50px;">
					<jsp:include page="/WEB-INF/view/common/pagination.jsp" >
						<jsp:param value="emp" name="domain"/>
					</jsp:include>
					</td>				
				<tr>
			</table>
		</div>
	</div>
</body>
</html>