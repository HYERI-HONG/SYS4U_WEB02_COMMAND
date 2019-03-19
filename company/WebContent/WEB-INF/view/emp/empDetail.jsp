<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="content">
	<div id="content_detail">
		<h3>사원 정보 조회</h3>
		<table id="empInfo">
			<tr>
				<td>사원 번호</td>
				<td>${employee.empno}</td>
				<td>이 름</td>
				<td>${employee.ename}</td>
			</tr>

			<tr>
				<td>직 책</td>
				<td>${employee.job}</td>
				<td>부 서</td>
				<td>${employee.deptno}</td>
			</tr>

			<tr>
				<td>직속 상관</td>
				<td>${employee.mgr}</td>
				<td>입사 날짜</td>
				<td>${employee.hiredate}</td>
			</tr>

			<tr>
				<td>연 봉</td>
				<td>${employee.sal}</td>
				<td>상 여 금</td>
				<td>${employee.comm}</td>
			</tr>
			<tr>
				<td><input type="button" value="사원 정보 수정"
					onclick="javascript:location.href='${context}/move.do?pageName=empUpdate&empno=${employee.empno}';" />
				</td>
				<td colspan="2"><input type="button" value="사원 목록으로 돌아가기"
					onclick="javascript:location.href='${context}/empList.do';">
				</td>
			</tr>
		</table>
	</div>
</div>
