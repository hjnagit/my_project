package com.cinemaw.member.db;

import java.sql.Timestamp;

public class MovieDTO {
	
	private int m_id;
	private String m_nm;
	private String m_story;
	private String m_person;
	private String mv_genre;
	private int mv_runtime;
	private String mv_grade;
	private Timestamp mv_rlsdate;
	private String mv_picture;
	private String mv_video;
	
	
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public String getM_nm() {
		return m_nm;
	}
	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}
	public String getM_story() {
		return m_story;
	}
	public void setM_story(String m_story) {
		this.m_story = m_story;
	}
	public String getM_person() {
		return m_person;
	}
	public void setM_person(String m_person) {
		this.m_person = m_person;
	}
	public String getMv_genre() {
		return mv_genre;
	}
	public void setMv_genre(String mv_genre) {
		this.mv_genre = mv_genre;
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
	public Timestamp getMv_rlsdate() {
		return mv_rlsdate;
	}
	public void setMv_rlsdate(Timestamp mv_rlsdate) {
		this.mv_rlsdate = mv_rlsdate;
	}
	public String getMv_picture() {
		return mv_picture;
	}
	public void setMv_picture(String mv_picture) {
		this.mv_picture = mv_picture;
	}
	public String getMv_video() {
		return mv_video;
	}
	public void setMv_video(String mv_video) {
		this.mv_video = mv_video;
	}
	@Override
	public String toString() {
		return "MovieDTO [m_id=" + m_id + ", m_nm=" + m_nm + ", m_story=" + m_story + ", m_person=" + m_person
				+ ", mv_genre=" + mv_genre + ", mv_runtime=" + mv_runtime + ", mv_grade=" + mv_grade + ", mv_rlsdate="
				+ mv_rlsdate + ", mv_picture=" + mv_picture + ", mv_video=" + mv_video + "]";
	}
	
	
	
	
}
