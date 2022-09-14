package com.cinemaw.reserve.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.reserve.action.Action;
import com.cinemaw.reserve.action.ActionForward;
import com.cinemaw.reserve.db.ReserveDAO;
import com.cinemaw.reserve.db.ReserveDTO;

public class SeatSelectAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : SeatSelectAction.execute() 호출");
		request.setCharacterEncoding("UTF-8");
		
		//디비의 좌석예매 정보를 가져오기
		//영화아이디,상영날짜,시간이 같으면 같은 좌석을 예매하지 못하게
		ReserveDAO dao = new ReserveDAO();
		
		String s_date = request.getParameter("s_date");
		String s_time = request.getParameter("s_time");
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		String u_id = request.getParameter("u_id");
		
		List<ReserveDTO> seatList = dao.getSeatList(s_date, s_time, m_id);
		List<String> seats = new ArrayList<String>();
		System.out.println("M : 좌석정보 저장 완료!");
		
		for(int i=0; i<seatList.size(); i++){
			ReserveDTO dto = seatList.get(i);
			seats.add(dto.getR_seat());
		}
		
		//영화정보 가져오기
		ReserveDTO dto = dao.getMovieInfo(m_id);
		String m_nm = dto.getM_nm();
		String mv_picture = dto.getMv_picture();
		
		//view 페이지 정보 전달을 위해서 request 영역에 저장
		request.setAttribute("seats", seats);
		request.setAttribute("m_nm", m_nm);
		request.setAttribute("mv_picture", mv_picture);
		
		
		//포인트 가져오기
		dto = dao.getPoint(u_id);
		
		int point = dto.getPoint();

		//view 페이지 정보 전달을 위해서 request 영역에 저장
		request.setAttribute("point", point);
		
		// 화면에 출력
		// 페이지 이동(화면전환)
		ActionForward forward = new ActionForward();
		forward.setPath("./seatSelectView.jsp");
		forward.setRedirect(false); // 화면만 바뀌는 false
		
		
		
		
		
		return forward;
	}
	
}
