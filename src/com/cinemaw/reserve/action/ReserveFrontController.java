package com.cinemaw.reserve.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.reserve.action.ActionForward;

public class ReserveFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("c : doProcess() 호출");
		
		// 1.가상주소계산/////////////////////////////////////////////////////////////////////////
		System.out.println("1. 가상주소 계산 - 시작");
		String requestURI = request.getRequestURI(); // 프로젝트명+주소
		System.out.println("C : requestURI : " + requestURI);
		
		String ctxPath = request.getContextPath(); // 프로젝트명
		System.out.println("C : ctxPath : " + ctxPath);
				
		String command = requestURI.substring(ctxPath.length()); //프로젝트명 길이만큼 자름
		System.out.println("C : command : " + command);
		System.out.println("1. 가상주소 계산 - 끝");
		
		
		
		//2. 가상 주소 매핑/////////////////////////////////////////////////////////////////////////
		System.out.println("2. 가상주소 매핑 - 시작");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MovieSelect.re")){ //영화선택페이지로 이동
			forward = new ActionForward();
			forward.setPath("./movieSelectView.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/MovieSelectAction.re")){ //영화선택액션으로 이동
			System.out.println("C : /MovieSelectAction.re 호출");
			System.out.println("C : DB에서 정보 가져오기, 페이지 이동");
			
			action = new MovieSelectAction();
			
			try {
				forward = action.execute(request, response); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/SeatSelectAction.re")){ //좌석선택액션으로 이동
			
			System.out.println("C : /SeatSelectAction.re 호출");
			
			action = new SeatSelectAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}	
		else if(command.equals("/seatSelectView.re")){ //좌석선택페이지로 이동
			forward = new ActionForward();
			forward.setPath("./seatSelectView.jsp");
			forward.setRedirect(false);
		}	
		else if(command.equals("/payView.re")){ //결제페이지로 이동
			forward = new ActionForward();
			forward.setPath("./payView.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/PayAction.re")){ //결제액션으로 이동
			
			System.out.println("C : /PayAction.re 호출");
			
			action = new PayAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}	
		else if(command.equals("/payCom.re")){ //결제완료 이동
			forward = new ActionForward();
			forward.setPath("./payCom.jsp");
			forward.setRedirect(true);
		}
		
		System.out.println("2. 가상주소 매핑 - 끝");
		
		
		
		
		//3. 가상주소 이동//////////////////////////////////////////////////////////////////////////
		System.out.println("3. 가상주소 이동 - 시작");
		if(forward != null){
			if(forward.isRedirect()){
				//true
				response.sendRedirect(forward.getPath());
				System.out.println("C : true - " + forward.getPath());
			}else{
				//false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println("C : false - ");
			}
		}		
		System.out.println("3. 가상주소 이동 - 끝");
		
		

		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
	
	
}
