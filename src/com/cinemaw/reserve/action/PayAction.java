package com.cinemaw.reserve.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.member.db.PointDTO;
import com.cinemaw.reserve.db.ReserveDAO;
import com.cinemaw.reserve.db.ReserveDTO;

public class PayAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("M : PayAction.execute() 호출");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		ReserveDTO dtoR = (ReserveDTO)session.getAttribute("dtoR");
		
		System.out.println("dtoR" + dtoR);
		
		int r_adult = Integer.parseInt(request.getParameter("r_adult"));
		int r_teenager = Integer.parseInt(request.getParameter("r_teenager"));
		int r_elderly = Integer.parseInt(request.getParameter("r_elderly"));
		
		String r_pay_type = request.getParameter("r_pay_type");
		int r_user_point = Integer.parseInt(request.getParameter("r_user_point"));
		
		
		
		//받아온 정보 dto에 저장하기
		ReserveDAO dao = new ReserveDAO();
	
		dtoR.setR_adult(r_adult);
		dtoR.setR_teenager(r_teenager);
		dtoR.setR_elderly(r_elderly);
		
		
		
		int num = r_adult+r_teenager+r_elderly; // 예매 인원수
		
		//예매 좌석 저장하기
		String[] seatss = request.getParameterValues("r_seat");
		
		
		if(num == 1){
			dtoR.setR_seat_1(seatss[0]);
		}else if(num==2){
			dtoR.setR_seat_1(seatss[0]);
			dtoR.setR_seat_2(seatss[1]);
		}else if(num == 3){
			dtoR.setR_seat_1(seatss[0]);
			dtoR.setR_seat_2(seatss[1]);
			dtoR.setR_seat_3(seatss[2]);
		}else if(num == 4){
			dtoR.setR_seat_1(seatss[0]);
			dtoR.setR_seat_2(seatss[1]);
			dtoR.setR_seat_3(seatss[2]);
			dtoR.setR_seat_4(seatss[3]);
		}
		
		
		
		//결제 정보 저장
		dtoR.setR_pay_type(r_pay_type);
		dtoR.setR_pay_price(r_adult*15000 + r_teenager*11000 + r_elderly*5000 - r_user_point);
		dtoR.setR_user_point(r_user_point); //사용한 포인트
		
		
		System.out.println("M : " + dtoR);
		
		
		
		
		
		
		//예매 제어하기
		synchronized(this){ //인스턴스 메서드 안의 동기화 블럭
			//디비의 좌석리스트 받아오기
			List<ReserveDTO> seatList = dao.getSeatList(dtoR.getT_id(), dtoR.getS_date(), dtoR.getS_time(), dtoR.getM_id());
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
		dao.reservaion(dtoR);
		
		System.out.println("M : 예매결제 정보 디비에 저장 완료");

		
		PointDTO dtoP = (PointDTO)session.getAttribute("dtoP");
		
		
		//포인트 업데이트 - 포인트 사용하면 Point테이블에 적용하기
		int point = dtoP.getPoint() - r_user_point;
		
		dao.pointUpdate(dtoR.getU_id(), point);
		System.out.println("M : 포인트 디비에 저장 완료");
		
		
		
		
		//좌석현황 업데이트 - screen테이블에 예매좌석수 차감하기
		dao.seatUpdate(num, dtoR.getT_id(), dtoR.getS_date(), dtoR.getS_time(), dtoR.getM_id());
		System.out.println("M : 좌석 현황 업데이트 완료.");
		
		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./payCom.re"); // 결제완료 페이지
		forward.setRedirect(true); 
		
		
		return forward;
	}

	
	
	
}
