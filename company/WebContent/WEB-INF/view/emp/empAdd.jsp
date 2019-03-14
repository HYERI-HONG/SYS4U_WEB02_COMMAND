<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empAdd</title>
<script type="text/javascript" src="/company/resources/js/emp/empAdd.js"></script>
</head>
<body>
	<div id="content">
		<div id="content_add">
			<h3>신입 사원 등록</h3>
			<form id="addForm" name="addForm">
				<table id="addTavble" style="">
					<tr>
						<td>사원 번호</td>
						<td><input type="text" name="empNo" /></td>
					</tr>
					<tr>
						<td>이 름</td>
						<td><input type="text" name="eName" id="eName"/>
						<input type="button" id="duplCheckBtn" value="확인"></td>
					</tr>
					<tr>
						<td>입 사 일</td>
						<td><input type="date" name="hireDate" min="2019-01-01"/></td>
					</tr>
					<tr>
						<td>연 봉</td>
						<td><input type="text" name="sal" /></td>
					</tr>
					<tr>
						<td>상 여 금</td>
						<td><input type="text" name="comm" /></td>
					</tr>
					<tr>
						<td>직속 상관</td>
						<td><input type="text" name="mgr" /></td>
					</tr>
					<tr>
						<td>부 서</td>
						<td>
						<input type="radio" name="deptNo" value="10" /> Accounting 
						<input type="radio" name="deptNo" value="20" /> Research 
						<input type="radio" name="deptNo" value="30" />Sales 
						<input type="radio" name="deptNo" value="40" /> Operations</td>
					</tr>
					<tr>
						<td>직 책</td>
						<td>
							<select name="job">
								<option value="CLERK">Clerk</option>
								<option value="SALESMAN">Salesman</option>
								<option value="MANAGER">manager</option>
								<option value="ANALYST">Analyst</option>
								<option value="PRESIDENT">President</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<input id="addFormSubmitBtn" type="button" value="등록" />
							<input type="button" onclick="javascript:history.back();" value="취소">
						</td>
						<td></td>
					</tr>

				</table>
			</form>
		</div>
	</div>
</body>
</html>