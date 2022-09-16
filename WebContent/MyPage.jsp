<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>마이페이지</h1>

<fieldset>
<table border="1">
	<tr>
		<td>예약번호</td>
		<td>영화제목</td>
		<td>날짜</td>
	<tr>
	
	<c:forEach var="dto" items="${requestScope.reserveList }">
		<tr>
			<td>${dto.r_id }</td>
			<td>
			${dto.m_nm }
			</td>
			<td>${dto.s_date }</td>
		<tr>
	
	</c:forEach>
	
		
</table>


<!-- 하단 페이징 처리 -->
<c:if test="${cnt != 0 }">
	<!-- 이전 : 직전 페이지블럭의 첫번째 페이지 호출  -->
	<c:if test="${startPage > pageBlock }">
		<a href="./MyPage.me?pageNum=${startPage-pageBlock}">[이전]</a>
	</c:if>
	
	<!-- 1,2,3,4,... -->
	<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
		<a href="./MyPage.me?pageNum=${i }">[${i }]</a>
	</c:forEach>
	
	<!-- 다음 -->
	<c:if test="${endPage < pageCount }">
		<a href="./MyPage.me?pageNum=${startPage+pageBlock}">[다음]</a>
	</c:if>

</c:if>



</fieldset>












</body>
</html>