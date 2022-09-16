package com.cinemaw.reserve.db;

import java.sql.Date;

public class ReservationDTO {
	
	private int m_id; // 영화 id
	private String m_name; // 영화 제목
	private int mv_runtime; // 상영 시간
	private String mv_grade; // 관람 등급
	private int t_num; // 상영관 ID
	private String s_date; // 상영일
	private String s_time; // 상영 시간
	private int seat_cnt; // 좌석
	
		
	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public int getMv_runtime() {
		return mv_runtime;
	}

	public void setMv_runtime(int mv_runtime) {
		this.mv_runtime = mv_runtime;
	}

	public String getMv_grade() {
		return mv_grade;
	}

	public void setMv_grade(String mv_grade) {
		this.mv_grade = mv_grade;
	}

	public int getT_num() {
		return t_num;
	}

	public void setT_num(int t_num) {
		this.t_num = t_num;
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
	
	public int getSeat_cnt() {
		return seat_cnt;
	}

	public void setSeat_cnt(int seat_cnt) {
		this.seat_cnt = seat_cnt;
	}

	@Override
	public String toString() {
		return "ReservationDTO [m_id=" + m_id + ", m_name=" + m_name + ", mv_runtime=" + mv_runtime + ", mv_grade="
				+ mv_grade + ", t_num=" + t_num + ", s_date=" + s_date + ", s_time=" + s_time + ", seat_cnt=" + seat_cnt + "]";
	}
	
}
	