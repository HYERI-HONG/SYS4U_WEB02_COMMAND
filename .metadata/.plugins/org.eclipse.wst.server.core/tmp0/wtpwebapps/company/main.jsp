<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
</head>
<body>
	<h3>메뉴</h3>
	<ul>
		<li><a href="" id="empAdd">신규 사원 등록</a></li>
		<li><a href="" id="empDetail">사원 정보 조회</a></li>
		<li><a href="" id="empUpdate">사원 정보 수정</a></li>
		<li><a href="" id="emplist">사원 목록 조회</a></li>
	</ul>
	
	<script>	
	document.getElementById('empAdd').onclick = function(){
		location.href="<%=application.getContextPath()%>/empAdd.do"
	}
	document.getElementById('empDetail').onclick = function(){
		location.href="<%=application.getContextPath()%>/empDetail.do"
	}
	document.getElementById('empUpdate').onclick = function(){
		location.href="<%=application.getContextPath()%>/empUpdate.do"
	}
	document.getElementById('emplist').onclick = function(){
		location.href="<%=application.getContextPath()%>/emplist.do?option=all"
	}	
	</script>
</body>
</html>
