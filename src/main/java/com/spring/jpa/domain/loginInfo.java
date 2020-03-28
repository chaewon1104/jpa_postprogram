package com.spring.jpa.domain;

import java.util.Date;

import javax.persistence.Column;
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
		  name = "BBSNO_SEQ_GENERATOR", 
		  sequenceName = "BBSNO_SEQ",
		  initialValue = 1,
		  allocationSize = 1)
@Entity
@Table(name="logininfo")
@Data
public class loginInfo {

	
	private String nm;
	@Id
	@Column(name = "idd")
	private String idd;
	private String passwd;
	private String birthday;
	private String phonenum;


	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getIdd() {
		return idd;
	}
	public void setIdd(String idd) {
		this.idd = idd;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
	

}