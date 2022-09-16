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
		public List<ReserveDTO> getReserveList(String u_id, int startRow, int pageSize){
			List<ReserveDTO> reserveList = new ArrayList<>();
			
			try {
				//1. 드라이버 로드
				//2. 디비 연결
				con = getConnect();
				
				//3. sql 작성 & pstmt 객체
				sql = "select r_id, u_id, t_id, s_date, s_time, m.m_nm, r_adult, r_teenager, r_elderly, "
						+ "r_seat_1, r_seat_2, r_seat_3, r_seat_4, r_pay_type, r_pay_price, r_user_point "
						+ "from reserve_info r join movie_master m on r.m_id = m.m_id where u_id = ? order by r_id desc limit ?, ?";
				
				pstmt = con.prepareStatement(sql);
				
				//??
				pstmt.setString(1, u_id);
				pstmt.setInt(2, startRow); // 시작행-1
				pstmt.setInt(3, pageSize); // 몇개씩 보여줄 것인지

				
				//4. sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				while(rs.next()){
					//정보 있음
					//정보가 같으면
					ReserveDTO dto = new ReserveDTO();
					
					dto.setR_id(rs.getInt("r_id"));
					dto.setU_id(rs.getString("u_id"));
					dto.setT_id(rs.getInt("t_id"));
					dto.setS_date(rs.getString("s_date"));
					dto.setS_time(rs.getString("s_time"));
					
					dto.setM_nm(rs.getString("m_nm"));
					
//					dto.setM_id(rs.getInt("m_id"));
					dto.setR_adult(rs.getInt("r_adult"));
					dto.setR_teenager(rs.getInt("r_teenager"));
					dto.setR_elderly(rs.getInt("r_elderly"));
					
					dto.setR_seat_1(rs.getString("r_seat_1"));
					dto.setR_seat_2(rs.getString("r_seat_2"));
					dto.setR_seat_3(rs.getString("r_seat_3"));
					dto.setR_seat_4(rs.getString("r_seat_4"));
					
					dto.setR_pay_type(rs.getString("r_pay_type"));
					dto.setR_pay_price(rs.getInt("r_pay_price"));
					dto.setR_user_point(rs.getInt("r_user_point"));
					
					
					
					
					
					reserveList.add(dto);
					
				}
				
				
				System.out.println(" DAO : 예약 정보 저장 완료. ");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				closeDB();
			}
			
			return reserveList;
		}//좌석정보 가져오기
	
	
		// 예약 전체 개수 조회(all) - getReserveCount()
		public int getReserveCount(String u_id){
			System.out.println("\n DAO : getReserveCount() 호출");
			int cnt = 0;
			
			try {
				// 1.2. 디비 연결 ( 커넥션 풀)
				con = getConnect();
				
				// 3. sql작성 & pstmt 객체
				sql = "select count(*) from reserve_info where u_id = ? ";
				pstmt = con.prepareStatement(sql);
				
				//?
				pstmt.setString(1, u_id);
				
				// 4. sql 실행 - select
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()){
					//데이터 있을 때
					//cnt = rs.getInt("count(*)"); // 컬럼명이 count(*)
					cnt = rs.getInt(1); // 인덱스 명을 사용한다
					
				}
				
				System.out.println("DAO : 글 개수 - 총 : " + cnt + "개");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally{ 
				// 무조건 적어야한다 - 그래서 그냥 처음에 바로 적는다
				closeDB();
				//자동으로 자원해제를 해주는 구문 try-with구문
			}
			
			
			return cnt; // 리턴 꼭 해야한다 까먹지말고
			
		}// 글 전체 개수 조회(all) - getBoardCount()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
