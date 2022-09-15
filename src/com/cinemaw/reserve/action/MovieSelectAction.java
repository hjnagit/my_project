package com.cinemaw.reserve.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.reserve.db.ReserveDAO;
import com.cinemaw.reserve.db.ReserveDTO;

public class MovieSelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MovieSelectAction.execute() 호출");
		request.setCharacterEncoding("UTF-8");
		
		
		//디비의 좌석예매 정보를 가져오기
		//영화아이디,상영날짜,시간이 같으면 같은 좌석을 예매하지 못하게
		ReserveDAO dao = new ReserveDAO();
		
		String u_id = request.getParameter("u_id");
		String s_date = request.getParameter("s_date");
		String s_time = request.getParameter("s_time");
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		
		List<ReserveDTO> seatList = dao.getSeatList(s_date, s_time, m_id);
		List<String> seat_1 = new ArrayList<String>();
		List<String> seat_2 = new ArrayList<String>();
		List<String> seat_3 = new ArrayList<String>();
		List<String> seat_4 = new ArrayList<String>();
		
		for(int i=0; i<seatList.size(); i++){
			ReserveDTO dto = seatList.get(i);
			seat_1.add(dto.getR_seat_1());
			seat_2.add(dto.getR_seat_2());
			seat_3.add(dto.getR_seat_3());
			seat_4.add(dto.getR_seat_4());
		}
		
		//예매된 좌석 저장하기
		//view 페이지 정보 전달을 위해서 request 영역에 저장
		request.setAttribute("seat_1", seat_1);
		request.setAttribute("seat_2", seat_2);
		request.setAttribute("seat_3", seat_3);
		request.setAttribute("seat_4", seat_4);
		
		System.out.println("M : 좌석정보 저장 완료!");
		
		
		
		//포인트 가져오기 -> 지금 필요없음 수정해야함...
		ReserveDTO dto = dao.getPoint(u_id);
		
		int point = dto.getPoint();
		
		request.setAttribute("point", point);
		
		
		//영화정보 가져오기
		dto = dao.getMovieInfo(m_id);
		
		//dto에 영화 정보 저장하기
		request.setAttribute("dto", dto);
		
		
		

		
		
		
		
		// 화면에 출력
		// 페이지 이동(화면전환)
		ActionForward forward = new ActionForward();
		forward.setPath("./seatSelectView.jsp");
		forward.setRedirect(false); // 화면만 바뀌는 false
		
		
		
		
		
		return forward;
	}
	
	
	
}
