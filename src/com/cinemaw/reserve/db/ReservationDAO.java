package com.cinemaw.reserve.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
	
	// 공통 변수
	private Connection con = null;
	private PreparedStatement pstmt = null; 
	private ResultSet rs = null;
	private String sql = "";
	
	public ReservationDAO(){
		System.out.println("DAO : DB 연결에 관한 모든 정보를 준비 완료!");
	}
	
	
	// DB 연결
	private Connection getConnect() throws Exception {
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String DBURL = "jdbc:mysql://localhost:3306/movieswill";
		String DBID = "root";
		String DBPW = "1234"; 
		
		// 1. 드라이버 로드 
		Class.forName(DRIVER);
		System.out.println("드라이버로드 성공!");
		
		//2. 디비연결
		con = DriverManager.getConnection(DBURL, DBID, DBPW);
		System.out.println("디비연결 성공");
		System.out.println("con :"+con);
		
		return con;
	} // DB 연결 끝
	
		
	// 자원 해제 - 시작
	public void closeDB(){
	
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt !=null) {
				pstmt.close();
			}
			if(con!=null){
				con.close();
			}
			
			System.out.println("DAO : 자원 해제 성공!");
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	} //자원 해제 끝
		
	
	// 상영 정보 조회
	public List<ReservationDTO> getReservationList(){
			
		List<ReservationDTO> reservationList = new ArrayList<ReservationDTO>();
			
		//DB테이블에 있는 데이터를 꺼내오기	
		try {
			//1. 드라이버 로드
			//2. 디비연결
			con = getConnect();
			
			//3. sql 작성 & pstmt 객체
			sql = "select m.m_id, m.m_name, m.mv_runtime, m.mv_grade, s.t_num, s.s_date, s.s_time, s.seat_cnt"
					+ "from movie_masterm join screen_master s on m.m_id = s.m_id";
			pstmt =con.prepareStatement(sql);
				
			//4. sql 실행
			rs = pstmt.executeQuery();
				
			//5. 데이터 처리 
			while(rs.next()){
				ReservationDTO dto = new ReservationDTO();
				dto.setM_id(rs.getInt("m_id"));
				dto.setM_name(rs.getString("m_name"));
				dto.setMv_grade(rs.getString("mv_grade"));
				dto.setMv_runtime(rs.getInt("mv_runtime"));
				
				dto.setT_num(rs.getInt("t_num"));
				dto.setS_date(rs.getString("s_date"));
				dto.setS_time(rs.getString("s_time"));
				dto.setSeat_cnt(rs.getInt("seat_cnt"));
				
				reservationList.add(dto);
			} // while문 끝
			
			System.out.println("C : 상영 정보 저장 완료");
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}
		
		return reservationList;
		}
		
}


