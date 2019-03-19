<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${context}/resources/js/common/pagination.js"></script>



<script type="text/javascript">
	var pageInfo = {
		domain : '${param.domain}',
		searchWord : '${searchWord}',
		searchOption : '${searchOption}',
		contextPath : '${context}'
	};

	pagination.changePage(pageInfo);
</script>
