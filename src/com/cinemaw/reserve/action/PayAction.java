package com.cinemaw.reserve.action;

import java.io.PrintWriter;
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
		
		//파라메타 정보 받아와서 저장
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
		
		
		
		//받아온 정보 dto에 저장하기
		ReserveDAO dao = new ReserveDAO();
		ReserveDTO dto = new ReserveDTO();
		dto.setU_id(u_id);
		dto.setT_id(t_id);
		dto.setS_date(s_date);
		dto.setS_time(s_time);
		dto.setM_id(m_id);
	
		dto.setR_adult(r_adult);
		dto.setR_teenager(r_teenager);
		dto.setR_elderly(r_elderly);
		
		
		
		int num = r_adult+r_teenager+r_elderly; // 예매 인원수
		
		//예매 좌석 저장하기
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
		
		
		
		//결제 정보 저장
		dto.setR_pay_type(r_pay_type);
		dto.setR_pay_price(r_adult*15000 + r_teenager*11000 + r_elderly*5000 - r_user_point);
		dto.setR_user_point(r_user_point); //사용한 포인트
		
		
		System.out.println("M : " + dto);
		
		
		
		
		
		
		//예매 제어하기
		synchronized(this){ //인스턴스 메서드 안의 동기화 블럭
			//디비의 좌석리스트 받아오기
			List<ReserveDTO> seatList = dao.getSeatList(s_date, s_time, m_id);
			List<String> seat_1 = new ArrayList<String>();
			List<String> seat_2 = new ArrayList<String>();
			List<String> seat_3 = new ArrayList<String>();
			List<String> seat_4 = new ArrayList<String>();
			
			for(int i=0; i<seatList.size(); i++){
				ReserveDTO dtos = seatList.get(i);
				seat_1.add(dtos.getR_seat_1());
				seat_2.add(dtos.getR_seat_2());
				seat_3.add(dtos.getR_seat_3());
				seat_4.add(dtos.getR_seat_4());
			}
			System.out.println("좌석정보 받아오기 완료.-------------------------");
			
			ActionForward forward = null;
			//예매한 좌석과 비교하기
			for(int i=0; i<num; i++){
				if(seat_1.contains(seatss[i])){
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('이미 선택된 좌석입니다');");
					out.println("location.href = './MovieSelect.re'");
					out.println("</script>");
					out.close();
					return null;
				} 
				else if(seat_2.contains(seatss[i])){
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('이미 선택된 좌석입니다');");
					out.println("location.href = './MovieSelect.re'");
					out.println("</script>");
					out.close();
					return null;
				}
				else if(seat_3.contains(seatss[i])){
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('이미 선택된 좌석입니다');");
					out.println("location.href = './MovieSelect.re'");
					out.println("</script>");
					out.close();
					return null;
				}
				else if(seat_4.contains(seatss[i])){
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('이미 선택된 좌석입니다');");
					out.println("location.href = './MovieSelect.re'");
					out.println("</script>");
					out.close();
					return null;
				}
			}
			System.out.println("검토 완료.------------------------------------");
			
		}

		
		
		
		
		//dto에 예매결제 정보 저장 완료
		dao.reservaion(dto);
		
		System.out.println("M : 예매결제 정보 디비에 저장 완료");

		
		
		
		
		//포인트 업데이트 - 포인트 사용하면 Point테이블에 적용하기
		int point = Integer.parseInt(request.getParameter("point"))-r_user_point;
		
		dao.pointUpdate(u_id, point);
		System.out.println("M : 포인트 디비에 저장 완료");
		
		
		
		
		//좌석현황 업데이트 - screen테이블에 예매좌석수 차감하기
		dao.seatUpdate(num, t_id, s_date, s_time, m_id);
		System.out.println("M : 좌석 현황 업데이트 완료.");
		
		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./payCom.re"); // 결제완료 페이지
		forward.setRedirect(true); 
		
		
		return forward;
	}

	
	
	
}
