package com.cinemaw.reserve.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.member.db.MovieDTO;
import com.cinemaw.member.db.PointDTO;
import com.cinemaw.reserve.action.Action;
import com.cinemaw.reserve.action.ActionForward;
import com.cinemaw.reserve.db.ReserveDAO;
import com.cinemaw.reserve.db.ReserveDTO;

public class SeatSelectAction implements Action{
	//이거 안된다
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : SeatSelectAction.execute() 호출");
		request.setCharacterEncoding("UTF-8");
		
		//디비의 좌석예매 정보를 가져오기
		//영화아이디,상영날짜,시간이 같으면 같은 좌석을 예매하지 못하게
		ReserveDAO dao = new ReserveDAO();
		
		
		HttpSession session = request.getSession();
		
		ReserveDTO dtoR = (ReserveDTO)session.getAttribute("dtoR");
		
		
		
		//view 페이지 정보 전달을 위해서 request 영역에 저장
		session.setAttribute("seats", request.getParameterValues("seat"));
		
		
		//포인트 가져오기
		PointDTO dtoP = dao.getPoint(dtoR.getU_id());
		

		//view 페이지 정보 전달을 위해서 request 영역에 저장
		session.setAttribute("dtoP", dtoP);
		
		// 화면에 출력
		// 페이지 이동(화면전환)
		ActionForward forward = new ActionForward();
		forward.setPath("./payView.jsp");
		forward.setRedirect(false); // 화면만 바뀌는 false
		
		
		
		
		
		return forward;
	}
	
}
