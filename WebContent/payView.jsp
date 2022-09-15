<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
/* 포인트 사용하면 바로 반영되게 */
$( document ).ready( function() {
    $('#r_user_point').change( function() {
      var a = $( '#r_user_point' ).val();
      var b = $('#point').val();
      var totalPoint = Number(b) - Number(a)
      $( '#pointS' ).text( totalPoint );
      $( '#use' ).text( -Number(a) );
      
      var x = $( '#r_adult' ).val();
      var y = $( '#r_teenager' ).val();
      var z = $( '#r_elderly' ).val();
      var totalPay = Number(x)*15000 + Number(y)*11000 + Number(z)*5000 - Number(a);
      $( '#r_pay_price' ).text( totalPay );    
      
    });

});
</script>
</head>
<body>
<!-- 로그인 제어하기 넣어야함 // 일단 이렇게 적어놓는다 -->
<%
	request.setCharacterEncoding("UTF-8");
	//세션영역에 있는 로그인 아이디 정보를 가져오기
	String id =	(String)session.getAttribute("loginID");
	if(id == null){
		// 로그인을 안했다
		System.out.println("로그인 정보 없음!");
		response.sendRedirect("./login.me");
	}
	
	//결제페이지에서 10분이 지나면 영화선택 페이지로 이동한다
	response.addHeader("Refresh", "600; url=./RefreshPage.re");
%>



<h1>결제 페이지</h1>

<fieldset>
	<h3>예매 정보</h3>
	<form action="./PayAction.re" method="post">
		<img alt="" src="${param.mv_picture }" width="150" height="200"> <br>
		영화이름 : ${param.m_nm }
		날짜 : ${param.s_date }
		시간 : ${param.s_time }


		<input type="hidden" name="t_id" id="t_id" value="${param.t_id }">
		<input type="hidden" name="m_id" id="m_id" value="${param.m_id }">
		<input type="hidden" name="s_date" id="s_date" value="${param.s_date }">
		<input type="hidden" name="s_time" id="s_time" value="${param.s_time }">
		<input type="hidden" name="u_id" id="u_id" value="${param.u_id }">
		<input type="hidden" name="mv_picture" id="mv_picture" value="${param.mv_picture }">
		<input type="hidden" name="m_nm" id="m_nm" value="${param.m_nm }">


		
		<br>
		일반 : ${param.r_adult }
		청소년 : ${param.r_teenager }
		우대 : ${param.r_elderly } &nbsp;&nbsp;&nbsp;&nbsp;
		총 인원수 : ${param.r_adult + param.r_teenager + param.r_elderly }
		
		<input type="hidden" name="r_adult" id="r_adult" value="${param.r_adult }">
		<input type="hidden" name="r_teenager" id="r_teenager" value="${param.r_teenager }">
		<input type="hidden" name="r_elderly" id="r_elderly" value="${param.r_elderly }">



		
		<hr>
		선택한 좌석 : 
		<%
			String[] seat = request.getParameterValues("seat");

			for(String s : seat){
				out.print(s + "  ");
			}
		%>
		
		<c:forEach var="seatss" items="${paramValues.seat }">
			<input type="hidden" name="r_seat" id="r_seat" value="${seatss }">
		</c:forEach>
		<hr>




		결제 방법 : 
		<select name="r_pay_type" id="r_pay_type">
			<option>신용카드</option>
			<option>카카오페이</option>
			<option>휴대폰결제</option>
			<option>기프티콘</option>
		</select>
		<br>
		


		
		보유 포인트 : <span id="pointS" >${param.point }</span>
		<input type="hidden" name="point" id="point" value="${param.point }">
		<br>
		
		
		포인트 사용 : <input type="number" name="r_user_point" id="r_user_point" max="${param.point }" min="0" step="100" value="0"> 
		<small>(포인트는 100 단위로 사용가능합니다.)</small>





		<h3>금액 : ${param.r_adult*15000 + param.r_teenager*11000 + param.r_elderly*5000 }</h3>
		<h3>할인된 금액 : <span id="use">-</span></h3>

		<h3>총 결제 금액  </h3> 
		<h1><span id="r_pay_price">${param.r_adult*15000 + param.r_teenager*11000 + param.r_elderly*5000 }</span></h1>

		<input type="submit" value="결제하기">
		<input type="button" value="결제취소" onclick="location.href='./main.me';">


	</form>

</fieldset>





</body>
</html>