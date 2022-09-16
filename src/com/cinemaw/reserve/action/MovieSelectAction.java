package com.cinemaw.reserve.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.reserve.db.MovieDTO;
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
		
		String u_id = request.getParameter("u_id");// 세션에서 받아야함 확인
		
		int t_id = Integer.parseInt(request.getParameter("t_id"));
		String s_date = request.getParameter("s_date");
		String s_time = request.getParameter("s_time");
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		
		List<ReserveDTO> seatList = dao.getSeatList(t_id, s_date, s_time, m_id);
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
		
		
		System.out.println("M : 좌석정보 저장 완료!");
		
		ReserveDTO dtoR = new ReserveDTO();
		
		dtoR.setU_id(u_id);
		dtoR.setT_id(t_id);
		dtoR.setS_date(s_date);
		dtoR.setS_time(s_time);
		dtoR.setM_id(m_id);
		
		
		
		//영화정보 가져오기
		MovieDTO dtoM = dao.getMovieInfo(m_id);
		
		
		
		//세션에 저장하기
		HttpSession session = request.getSession();
		session.setAttribute("dtoM", dtoM);
		
		session.setAttribute("seat_1", seat_1);
		session.setAttribute("seat_2", seat_2);
		session.setAttribute("seat_3", seat_3);
		session.setAttribute("seat_4", seat_4);
		
		session.setAttribute("dtoR", dtoR);
		
		
		
		
		// 화면에 출력
		// 페이지 이동(화면전환)
		ActionForward forward = new ActionForward();
		forward.setPath("./reservation/seatSelectView.jsp");
		forward.setRedirect(false); // 화면만 바뀌는 false
		
		
		
		
		
		return forward;
	}
	
	
	
}
