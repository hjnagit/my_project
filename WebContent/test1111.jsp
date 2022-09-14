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

	<c:forEach var="j" begin="A" end="H">
        ${j }
       	<c:forEach var="i" begin="1" end="12">

			<input type="checkbox" name="seat" id="${j }_${i}" value="${j }_${i}">

		</c:forEach>
		
		<br>
		
		<c:if test="${j == 4 }">
			<br>
		</c:if>
		
	</c:forEach>

<%



%>

</body>
</html>