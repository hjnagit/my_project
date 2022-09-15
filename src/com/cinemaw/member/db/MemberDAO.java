package com.cinemaw.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinemaw.reserve.db.ReserveDTO;

public class MemberDAO {
	// 공통변수 (인스턴스 변수)
	private Connection con = null; // 디비연결정보 저장
	private PreparedStatement pstmt = null; // 디비에 sql 실행 처리 객체
	private ResultSet rs = null; // select 실행 결과 저장 객체
	private String sql = ""; // sql 쿼리 구문 저장
	
	
	public MemberDAO() {	
		System.out.println("DAO : DB연결에 관한 모든 정보를 준비 완료!");
	}
	
	//디비 연결
	private Connection getConnect() throws Exception{
		//디비 연결 정보
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String DBURL = "jdbc:mysql://localhost:3306/movieswill";
		String DBID = "root";
		String DBPW = "1234";
				
		//1. 드라이버 로드
		Class.forName(DRIVER);
		System.out.println("드라이버로드 성공");
				
		//2. 디비연결
		con = DriverManager.getConnection(DBURL, DBID, DBPW);
		System.out.println("디비 연결 성공");
		System.out.println("con : " + con);
				
		return con;		
	
	}// 디비연결
	
	
	// 자원 해제
	public void closeDB(){
		try {
			// 역순으로 자원을 닫아준다
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			
			System.out.println(" DAO : 자원해제 성공!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// 자원 해제
	
	
	
	
	//예매내역리스트 가져오기
		public List<ReserveDTO> getReserveList(String u_id){
			List<ReserveDTO> seatList = new ArrayList<>();
			
			try {
				//1. 드라이버 로드
				//2. 디비 연결
				con = getConnect();
				
				//3. sql 작성 & pstmt 객체
				sql = "select * from reserve_info where u_id=?";
				pstmt = con.prepareStatement(sql);
				
				//??
				pstmt.setString(1, u_id);

				
				//4. sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				while(rs.next()){
					//정보 있음
					//정보가 같으면
					ReserveDTO dto = new ReserveDTO();
					
					dto.setM_id(rs.getInt("m_id"));
					dto.setM_nm(rs.getString("m_nm"));
					
					
					
					
					dto.setR_seat_1(rs.getString("r_seat_1"));
					dto.setR_seat_2(rs.getString("r_seat_2"));
					dto.setR_seat_3(rs.getString("r_seat_3"));
					dto.setR_seat_4(rs.getString("r_seat_4"));
					
					seatList.add(dto);
					
					//}
				}
				
				
				System.out.println(" DAO : 좌석 리스트 저장 완료. ");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				closeDB();
			}
			
			return seatList;
		}//좌석정보 가져오기
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
