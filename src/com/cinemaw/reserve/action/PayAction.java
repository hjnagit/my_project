package com.cinemaw.reserve.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.reserve.db.ReserveDAO;
import com.cinemaw.reserve.db.ReserveDTO;

public class PayAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("M : PayAction.execute() 호출");
		request.setCharacterEncoding("UTF-8");
		
		//dto에 전달정보 저장하기
//		List<String> r_seat = new ArrayList<String>();
// 		r_seat = (String)request.getAttribute("seatList");
//		System.out.println("배열 크기"+r_seat);
		
		
		
		String u_id = request.getParameter("u_id"); //세션에서 정보를 받아와야 함!!!
		
		
		
		
		
		
		int t_id = Integer.parseInt(request.getParameter("t_id"));
		String s_date = request.getParameter("s_date");
		String s_time = request.getParameter("s_time");
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		
		int r_adult = Integer.parseInt(request.getParameter("r_adult"));
		int r_teenager = Integer.parseInt(request.getParameter("r_teenager"));
		int r_elderly = Integer.parseInt(request.getParameter("r_elderly"));
		
		String r_pay_type = request.getParameter("r_pay_type");
		int r_user_point = Integer.parseInt(request.getParameter("r_user_point"));
		
		
//		System.out.println(r_seat.length);
		
		ReserveDAO dao = new ReserveDAO();
//		for(int i=0; i < r_seat.length; i++){
		
		ReserveDTO dto = new ReserveDTO();
		dto.setU_id(u_id);
		dto.setT_id(t_id);
		dto.setS_date(s_date);
		dto.setS_time(s_time);
		dto.setM_id(m_id);
		
		
		
		dto.setR_adult(r_adult);
		dto.setR_teenager(r_teenager);
		dto.setR_elderly(r_elderly);
		
		
//		dto.setR_seat(r_seat.toString());//좌석번호 받아와야함
//		dto.setR_seat((String)request.getAttribute("r_seat"));//좌석번호 받아와야함
//		dto.setR_seat(request.getParameter("r_seat"));//좌석번호 받아와야함
		
		int num = r_adult+r_teenager+r_elderly;
		
		
		String[] seatss = request.getParameterValues("r_seat");
		
		
		if(num == 1){
			dto.setR_seat_1(seatss[0]);
		}else if(num==2){
			dto.setR_seat_1(seatss[0]);
			dto.setR_seat_2(seatss[1]);
		}else if(num == 3){
			dto.setR_seat_1(seatss[0]);
			dto.setR_seat_2(seatss[1]);
			dto.setR_seat_3(seatss[2]);
		}else if(num == 4){
			dto.setR_seat_1(seatss[0]);
			dto.setR_seat_2(seatss[1]);
			dto.setR_seat_3(seatss[2]);
			dto.setR_seat_4(seatss[3]);
		}
		
		
		
		
		dto.setR_pay_type(r_pay_type);
		dto.setR_pay_price(r_adult*15000 + r_teenager*11000 + r_elderly*5000 - r_user_point);
		dto.setR_user_point(r_user_point); //사용한 포인트
		
		
		System.out.println("M : " + dto);
		
		
		dao.reservaion(dto);
		
		System.out.println("M : 예매결제 정보 디비에 저장 완료");
		
		//포인트 업데이트
		int point = Integer.parseInt(request.getParameter("point"))-r_user_point;
		
		dao.pointUpdate(u_id, point);
		System.out.println("M : 포인트 디비에 저장 완료");
		
		
		//좌석현황 업데이트
		dao.seatUpdate(num, t_id, s_date, s_time, m_id);
		System.out.println("M : 좌석 현황 업데이트 완료.");
		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./payCom.re"); // 결제완료 페이지
		forward.setRedirect(true); 
		
		
		return forward;
	}

	
	
	
}
