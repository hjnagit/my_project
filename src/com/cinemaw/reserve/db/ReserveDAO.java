package com.cinemaw.reserve.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinemaw.member.db.MovieDTO;
import com.cinemaw.member.db.PointDTO;

public class ReserveDAO {
	// 공통변수 (인스턴스 변수)
	private Connection con = null; // 디비연결정보 저장
	private PreparedStatement pstmt = null; // 디비에 sql 실행 처리 객체
	private ResultSet rs = null; // select 실행 결과 저장 객체
	private String sql = ""; // sql 쿼리 구문 저장
	
	
	public ReserveDAO() {	
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
	
	
	// 예약정보 디비에 넣기 - reservation()
	public void reservaion(ReserveDTO dto){
		int r_id = 0; // 예약번호 저장
		try {
			//예약번호 저장/////////////////////////////////////////
			//1. 드라이버 로드
			//2. 디비 연결
			con = getConnect();
			
			//3. sql 작성 & pstmt 객체
			sql = "select max(r_id) from reserve_info";
			pstmt = con.prepareStatement(sql);
			//4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리(글번호 계산)가장 마지막 글번호 +1
			if(rs.next()){
				r_id = rs.getInt(1)+1; // 인덱스사용
			}
			
			System.out.println(" DAO : 글번호 bno : " + r_id);
			
			// 예약 결제 정보 저장///////////////////////////////////////////////////////
			// 3.sql작성 & pstmt  객체
			//date는 now() 함수를 사용해준다
			sql = "insert into reserve_info "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			//???
			pstmt.setInt(1, r_id);
			pstmt.setString(2, dto.getU_id());
			pstmt.setInt(3, dto.getT_id());
			pstmt.setString(4, dto.getS_date());
			pstmt.setString(5, dto.getS_time());
			pstmt.setInt(6, dto.getM_id());
			
			pstmt.setInt(7, dto.getR_adult()); 
			pstmt.setInt(8, dto.getR_teenager()); 
			pstmt.setInt(9, dto.getR_elderly()); 
			
			pstmt.setString(10, dto.getR_seat_1());
			pstmt.setString(11, dto.getR_seat_2());
			pstmt.setString(12, dto.getR_seat_3());
			pstmt.setString(13, dto.getR_seat_4());
			
			pstmt.setString(14, dto.getR_pay_type());
			pstmt.setInt(15, dto.getR_pay_price());
			pstmt.setInt(16, dto.getR_user_point()); //유저포인트 사용한것 해야함
			
			//4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 예매 결제 완료 ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}

	}// 예약정보 디비에 넣기 - reservation()
	
	//영화정보 가져오기
	public MovieDTO getMovieInfo(int m_id){
		
		MovieDTO dto = new MovieDTO();
		try {
			//1. 드라이버 로드
			//2. 디비 연결
			con = getConnect();
			
			//3. sql 작성 & pstmt 객체
			sql = "select m_nm, mv_picture from movie_master where m_id = ?";
			pstmt = con.prepareStatement(sql);
			
			//??
			pstmt.setInt(1, m_id);
			
			//4. sql 실행
			rs = pstmt.executeQuery();
		
			// 5. 데이터 처리
			if(rs.next()){
				
				
				dto.setM_nm(rs.getString("m_nm"));
				dto.setMv_picture(rs.getString("mv_picture"));
					
			}
			
			
			System.out.println(" DAO : 영화정보 저장 완료. ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}
		
		return dto;
	}//영화정보 가져오기
	
	
	
	//좌석정보 가져오기
	public List<ReserveDTO> getSeatList(int t_id, String s_date, String s_time, int m_id){
		List<ReserveDTO> seatList = new ArrayList<>();
		
		try {
			//1. 드라이버 로드
			//2. 디비 연결
			con = getConnect();
			
			//3. sql 작성 & pstmt 객체
			sql = "select r_seat_1, r_seat_2, r_seat_3, r_seat_4 from reserve_info where t_id=? and s_date=? and s_time=? and m_id=?";
			pstmt = con.prepareStatement(sql);
			
			//??
			pstmt.setInt(1, t_id);
			pstmt.setString(2, s_date);
			pstmt.setString(3, s_time);
			pstmt.setInt(4, m_id);
			
			//4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()){
				//정보 있음
				//if(t_id == rs.getInt("t_id") && s_date.equals(rs.getString("s_date")) && s_time.equals(rs.getString("s_time"))){
				//정보가 같으면
				ReserveDTO dtoR = new ReserveDTO();
				dtoR.setR_seat_1(rs.getString("r_seat_1"));
				dtoR.setR_seat_2(rs.getString("r_seat_2"));
				dtoR.setR_seat_3(rs.getString("r_seat_3"));
				dtoR.setR_seat_4(rs.getString("r_seat_4"));
				
				seatList.add(dtoR);
				
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
	
	//회원 보유 포인트 가져오기
	public PointDTO getPoint(String u_id){
		
		PointDTO dtoP = new PointDTO();
		try {
			//1. 드라이버 로드
			//2. 디비 연결
			con = getConnect();
			
			//3. sql 작성 & pstmt 객체
			sql = "select point from point where u_id=?";
			pstmt = con.prepareStatement(sql);
			
			//??
			pstmt.setString(1, u_id);
			
			//4. sql 실행
			rs = pstmt.executeQuery();
		
			// 5. 데이터 처리
			if(rs.next()){
				dtoP.setPoint(rs.getInt("point"));
			}
			
			
			System.out.println(" DAO : 영화정보 저장 완료. ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}
		
		return dtoP;
	}//회원 보유 포인트 가져오기	
	
	
	
	 // 사용포인트 반영하기
	public void pointUpdate(String u_id, int point) {
		try {
			// 예약번호 저장/////////////////////////////////////////
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getConnect();

			// 3. sql 작성 & pstmt 객체
			sql = "update point set point = ? where u_id = ?";
			pstmt = con.prepareStatement(sql);

			// ??
			pstmt.setInt(1, point);
			pstmt.setString(2, u_id);

			// 4. sql 실행
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}// 사용포인트 반영하기

	// 좌석 현황 반영하기
	public void seatUpdate(int num, int t_id, String s_date, String s_time, int m_id) {
		int seat_cnt = 0;
		try {
			// 예약번호 저장/////////////////////////////////////////
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getConnect();

			// 디비정보 가져오기 현재의 좌석정보
			sql = "select seat_cnt from screen_master where  t_num = ? and s_date=? and s_time=? and m_id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, t_id);
			pstmt.setString(2, s_date);
			pstmt.setString(3, s_time);
			pstmt.setInt(4, m_id);

			// 데이터 처리
			rs = pstmt.executeQuery();

			if (rs.next()) {
				seat_cnt = rs.getInt("seat_cnt");
			}

			// 디비에 업데이트 하기
			// 3. sql 작성 & pstmt 객체
			sql = "update screen_master set seat_cnt = ? where t_num = ? and s_date=? and s_time=? and m_id=?";
			pstmt = con.prepareStatement(sql);

			// ??
			pstmt.setInt(1, seat_cnt - num);
			pstmt.setInt(2, t_id);
			pstmt.setString(3, s_date);
			pstmt.setString(4, s_time);
			pstmt.setInt(5, m_id);

			// 4. sql 실행
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}// 좌석 현황 반영하기
	
	
}
