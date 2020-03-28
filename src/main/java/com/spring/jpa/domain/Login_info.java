package com.spring.jpa.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@SequenceGenerator(
		  name = "Log_In_Generator", 
		  sequenceName = "Log_In",
		  initialValue = 1,
		  allocationSize = 1)

public class Login_info {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Log_In_Generator")
	
	//private int bbsno;//primarykey
	private String name;//이름
	private String id;	// id
	private String password;//패스워드
	private String birthday;//생일
	//private String sex; //성별 
	private String phoneNum;//전화번호
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	

	
	

}
