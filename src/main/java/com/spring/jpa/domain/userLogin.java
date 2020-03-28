package com.spring.jpa.domain;

public class userLogin {
	
	
	private boolean isLogin=false;
	private String writer=null;
	
	

	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	

}
