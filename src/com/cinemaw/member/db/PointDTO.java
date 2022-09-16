package com.cinemaw.member.db;

import java.sql.Timestamp;

public class PointDTO {
	private String u_id;
	private int p_seq;
	private String p_type;
	private int point;
	private Timestamp p_dt;
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public int getP_seq() {
		return p_seq;
	}
	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Timestamp getP_dt() {
		return p_dt;
	}
	public void setP_dt(Timestamp p_dt) {
		this.p_dt = p_dt;
	}
	@Override
	public String toString() {
		return "PointDTO [u_id=" + u_id + ", p_seq=" + p_seq + ", p_type=" + p_type + ", point=" + point + ", p_dt="
				+ p_dt + "]";
	}
	
	
	
}
