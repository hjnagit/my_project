package com.cinemaw.reserve.db;

public class ReserveDTO {
	private int r_id; //예약변호
	private String u_id; //구매자 아이디
	private int t_id; //극장번호
	private String s_date; //상영날짜
	private String s_time; //상영시간
	private int m_id; //영화아이디
	
	private int r_adult; //어른수 - 15000
	private int r_teenager; //청소년수 - 11000
	private int r_elderly; //우대수 - 5000
	
	
	
	
	
	private String r_seat; //좌석번호
	private String r_seat_1; //좌석번호
	private String r_seat_2; //좌석번호
	private String r_seat_3; //좌석번호
	private String r_seat_4; //좌석번호
	
	private String r_pay_type; //결제타입
	private int r_pay_price; //가격
	private int r_user_point; //포인트
	
	private String m_nm; //영화 이름
	private String mv_picture; //영화 사진
	
	private int point; //회원 보유 포인트
	
	
	
	
	
	
	
	
	public String getR_seat_1() {
		return r_seat_1;
	}
	public void setR_seat_1(String r_seat_1) {
		this.r_seat_1 = r_seat_1;
	}
	public String getR_seat_2() {
		return r_seat_2;
	}
	public void setR_seat_2(String r_seat_2) {
		this.r_seat_2 = r_seat_2;
	}
	public String getR_seat_3() {
		return r_seat_3;
	}
	public void setR_seat_3(String r_seat_3) {
		this.r_seat_3 = r_seat_3;
	}
	public String getR_seat_4() {
		return r_seat_4;
	}
	public void setR_seat_4(String r_seat_4) {
		this.r_seat_4 = r_seat_4;
	}
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getM_nm() {
		return m_nm;
	}
	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}
	public String getMv_picture() {
		return mv_picture;
	}
	public void setMv_picture(String mv_picture) {
		this.mv_picture = mv_picture;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getS_date() {
		return s_date;
	}
	public void setS_date(String s_date) {
		this.s_date = s_date;
	}
	public String getS_time() {
		return s_time;
	}
	public void setS_time(String s_time) {
		this.s_time = s_time;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	
	public int getR_adult() {
		return r_adult;
	}
	public void setR_adult(int r_adult) {
		this.r_adult = r_adult;
	}
	public int getR_teenager() {
		return r_teenager;
	}
	public void setR_teenager(int r_teenager) {
		this.r_teenager = r_teenager;
	}
	public int getR_elderly() {
		return r_elderly;
	}
	public void setR_elderly(int r_elderly) {
		this.r_elderly = r_elderly;
	}
	public String getR_seat() {
		return r_seat;
	}
	public void setR_seat(String r_seat) {
		this.r_seat = r_seat;
	}
	public String getR_pay_type() {
		return r_pay_type;
	}
	public void setR_pay_type(String r_pay_type) {
		this.r_pay_type = r_pay_type;
	}
	public int getR_pay_price() {
		return r_pay_price;
	}
	public void setR_pay_price(int r_pay_price) {
		this.r_pay_price = r_pay_price;
	}
	public int getR_user_point() {
		return r_user_point;
	}
	public void setR_user_point(int r_user_point) {
		this.r_user_point = r_user_point;
	}
	@Override
	public String toString() {
		return "ReserveDTO [r_id=" + r_id + ", u_id=" + u_id + ", t_id=" + t_id + ", s_date=" + s_date + ", s_time="
				+ s_time + ", m_id=" + m_id + ", r_adult=" + r_adult + ", r_teenager=" + r_teenager + ", r_elderly="
				+ r_elderly + ", r_seat=" + r_seat + ", r_seat_1=" + r_seat_1 + ", r_seat_2=" + r_seat_2 + ", r_seat_3="
				+ r_seat_3 + ", r_seat_4=" + r_seat_4 + ", r_pay_type=" + r_pay_type + ", r_pay_price=" + r_pay_price
				+ ", r_user_point=" + r_user_point + ", m_nm=" + m_nm + ", mv_picture=" + mv_picture + ", point="
				+ point + "]";
	}
	
	
	
	
	
}
