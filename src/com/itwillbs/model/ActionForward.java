package com.itwillbs.model;

public class ActionForward {
	private String path; //이동할 주소
	private boolean isRedirect; // 이동할 방식
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
}
