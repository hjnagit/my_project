<%@page import="java.util.ArrayList"%>
<%@page import="com.cinemaw.reserve.db.ReserveDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
/* 인원수 고르면 금액/총인원수 반영하기 */
$( document ).ready( function() {
    $('#r_adult, #r_teenager, #r_elderly').change( function() {
      var a = $( '#r_adult' ).val();
      var b = $( '#r_teenager' ).val();
      var c = $( '#r_elderly' ).val();
      var totalNum = Number(a) + Number(b) + Number(c);
      $( '#totalNum' ).text( totalNum );
      var totalPay = Number(a)*15000 + Number(b)*11000 + Number(c)*5000;
      $( '#r_pay_price' ).text( totalPay );
    });
    
     $('#submit').click(function(){
    	var selectamount = $('input:checkbox[name="seat"]:checked').length;
    	var a = $( '#r_adult' ).val();
        var b = $( '#r_teenager' ).val();
        var c = $( '#r_elderly' ).val();
        var totalNum = Number(a) + Number(b) + Number(c);
   		
   		if(totalNum == 0){
   			alert('인원수를 선택해주세요');
   			return false;
   		}else if(totalNum > 4){
   			alert('최대 예매 인원수는 4명 입니다');
   			return false;
   		}
   		else if(selectamount > totalNum){ 
    		alert('선택한 인원수 보다 많은 좌석수입니다.');
   			return false;
   		}
    	
    }); 
});//제이쿼리

</script>




<!-- 예매된 좌석 가져와서 제어하기 -->
<%
	List s1 = (ArrayList) session.getAttribute("seat_1");
	List s2 = (ArrayList) session.getAttribute("seat_2");
	List s3 = (ArrayList) session.getAttribute("seat_3");
	List s4 = (ArrayList) session.getAttribute("seat_4");
	String sss1 = "";
	String sss2 = "";
	String sss3 = "";
	String sss4 = "";
	for (int i = 0; i < s1.size(); i++) {
		sss1 = (String) s1.get(i);
		sss2 = (String) s2.get(i);
		sss3 = (String) s3.get(i);
		sss4 = (String) s4.get(i);
%>
<script type="text/javascript">
$( document ).ready( function() {
	var se1 = "<%=sss1%>";
	var se2 = "<%=sss2%>";
	var se3 = "<%=sss3%>";
	var se4 = "<%=sss4%>";
		$('#' + se1 + '').prop('disabled', true);
		$('#' + se2 + '').prop('disabled', true);
		$('#' + se3 + '').prop('disabled', true);
		$('#' + se4 + '').prop('disabled', true);
	});
</script>

<%
	}
%>






</head>
<body>

<!-- 로그인 제어하기 넣어야함ㅋ // 일단 이렇게 적어놓는다 -->
<%
	//세션영역에 있는 로그인 아이디 정보를 가져오기
	String id =	(String)session.getAttribute("loginID");
	if(id == null){
		// 로그인을 안했다
		System.out.println("로그인 정보 없음!");
		response.sendRedirect("./login.me");
	}
%>





<h1>좌석선택페이지</h1>


<form action="PayView.re" method="POST" class="seatView" style="max-width:720px;">

	<fieldset>
		<h3>영화 선택 정보</h3>
		<!-- 정보 넘기기 -->
		

		<img alt="" src="${dtoM.mv_picture }" width="150" height="200"> <br>
		영화이름 : ${dtoM.m_nm }
		날짜 : ${dtoR.s_date }
		시간 : ${dtoR.s_time }
	</fieldset>





	<fieldset>
		<h3>인원과 좌석을 선택해주세요</h3>
		<hr>
	
		일반 <input type="number" name="r_adult" id="r_adult" min="0" max="4" value="0">
		청소년 <input type="number" name="r_teenager" id="r_teenager" min="0" max="4" value="0">
		우대 <input type="number" name="r_elderly" id="r_elderly" min="0" max="4" value="0">
		<br>
		총 인원수 <span id="totalNum"></span> &nbsp;&nbsp;&nbsp;<small>(최대 예매 인원수는 4명 입니다)</small>
		<br>
		가격 <span id="r_pay_price"></span>
		<hr>

 
		<br>
         	 
		<!-- 좌석출력 -->
		<c:forEach var="j" begin="1" end="8">
			<small>${j }열</small>
			
			<c:forEach var="i" begin="1" end="12">
				<input type="checkbox" name="seat" id="${j }_${i}" value="${j }_${i}">
			</c:forEach>
	
			<br>
		
			<c:if test="${j == 4 }">
				<br>
			</c:if>
		
		</c:forEach>
 
		<hr>
		<input type="submit" id="submit" value="결제하기">
		<input type="reset" value="다시 선택하기">

	</fieldset>

</form>




</body>
</html>