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
	<h3>사원 목록</h3>
	<div id="content">
		<div id="content_search">
		<form action="${context}/empList.do" method="post">
			<select name="searchOption">
				<option value="all">전체 조회</option>
				<option value="ename">이름</option>
				<option value="empno">사원번호</option>
				<option value="dname">부서명</option>
			</select> 
			<input name="searchWord" type="text" placeholder="검색어 입력" /> 
			<input type="submit" id="searchButton" value="검색" />
		</form>		
		</div>
		<div id="content_list">
			<table id="employee_list">
				<tr>
					<th>사원 번호</th>
					<th>이 름</th>
					<th>직 책</th>
					<th>직속 상관</th>
					<th>입사 날짜</th>
					<th>연 봉</th>
					<th>상여 금액</th>
					<th>해당 부서</th>
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
				<tr>
					<td>
						<h4>조회 결과 : ${page.count}</h4>
					</td>
					<td colspan="5">
						<ul class="pageBox" style="display: inline; list-style: none;">
						<c:if test="${page.existPrev}">
							<li style="display: inline" id="${page.prevBlock}" class="changePage">◀이전 </li>
						</c:if>
						<c:forEach begin="${page.beginPage}" end="${page.endPage}" step="1" varStatus="i">
							<li style="display: inline">
								<a class="changePage" id="${i.index}">${i.index}</a>
							</li>
						</c:forEach>
						<c:if test="${page.existNext}">
							<li style="display: inline" id="${page.nextBlock}" class="changePage"> 다음▶</li>
						</c:if>
						</ul>
					</td>				
					<td>
						<input id="addBtn" type="button" value="사원 등록" />
						
					</td>
				<tr>
			
			</table>
		</div>
	</div>
	<script type="text/javascript">
		
		document.getElementById('addBtn').addEventListener('click',function(){
			location.href = "<%=application.getContextPath()%>/move.do?pageName=empAdd"
		});
		
		var pageNum = document.querySelectorAll('.changePage');
		for(var i=0; i<pageNum.length; i++){
			pageNum[i].addEventListener('click',function(){
				var pageNum = this.getAttribute('id');
				location.href ="<%=application.getContextPath()%>/empList.do?pageNum="+pageNum+"&searchWord=${searchWord}&searchOption=${searchOption}";
			});
		}
	</script>
</body>
</html>