<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2> 영화 정보 입력 페이지 </h2>
<%
	
	//일단 유저 아이디 저장해두기
	session.setAttribute("loginID", "aaa");

%>

<fieldset>
	<form action="./MovieSelectAction.re" method="post">
		구매자아이디 : <input type="text" name="u_id" value="aaa"><br>
		극장아이디 : <input type="text" name="t_id" value="101"><br>
		상영날짜 : <input type="text" name="s_date" value="20220927"><br>
		상영시간 : <input type="text" name="s_time" value="10:30"><br>
		영화아이디 : <input type="text" name="m_id" value="10"><br>
		
		
		<input type="submit" value="인원/좌석 선택하기">
	</form>
</fieldset>



</body>
</html>