package com.itwillbs.model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8088/Model2/BoardWrite.bo

public class BoardFrontController extends HttpServlet{
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET,POST 방식 호출 - doProcess() 실행");
		// 1.가상주소계산
		String requestURI = request.getRequestURI(); // 프로젝트명+주소
		System.out.println("C : requestURI : " + requestURI);
		
		String ctxPath = request.getContextPath(); // 프로젝트명
		System.out.println("C : ctxPath : " + ctxPath);
		
		String command = requestURI.substring(ctxPath.length()); //프로젝트명 길이만큼 자름
		System.out.println("C : command : " + command);
		System.out.println("1. 가상주소 계산 - 끝");
		
		//2. 가상 주소 매핑
		ActionForward forward = null;
		if(command.equals("/BoardWrite.bo")){
			forward = new ActionForward();
			forward.setPath("./board/writeForm.jsp");
			forward.setRedirect(false);
		}
		
		//3. 가상주소 이동
		if(forward != null){
			if(forward.isRedirect()){
				//true
				response.sendRedirect(forward.getPath());
			}else{
				//false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
		
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
