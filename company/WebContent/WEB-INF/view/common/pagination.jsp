<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${context}/resources/js/common/pagination.js"></script>

<h4 style="display: inline;">조회 결과 : ${page.count}</h4>
<ul class="pageBox" style="display: inline; list-style: none;">
	<c:if test="${page.existPrev}">
		<li style="display: inline; cursor: pointer;" id="${page.prevBlock}" class="changePage">◀이전</li>
	</c:if>
	<c:forEach begin="${page.beginPage}" end="${page.endPage}" step="1"
		varStatus="i">
		<li style="display: inline;cursor: pointer;"><a class="changePage" id="${i.index}">${i.index}</a></li>
	</c:forEach>
	<c:if test="${page.existNext}">
		<li style="display: inline;cursor: pointer;" id="${page.nextBlock}" class="changePage">다음▶</li>
	</c:if>
</ul>

<script type="text/javascript">
	var pageInfo = {
		domain : '${param.domain}',
		searchWord : '${searchWord}',
		searchOption : '${searchOption}',
		contextPath : '${context}'
	};

	pagination.changePage(pageInfo);
</script>
