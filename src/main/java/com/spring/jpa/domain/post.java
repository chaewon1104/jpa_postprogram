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
		  name = "BBSNO_SEQ_GENERATOR", 
		  sequenceName = "BBSNO_SEQ",
		  initialValue = 1,
		  allocationSize = 1)
@Entity
@Table(name="post")
public class post {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="BBSNO_SEQ_GENERATOR")
	
	
	private int bbsno;
	public int getBbsno() {
		return bbsno;
	}
	public void setBbsno(int bbsno) {
		this.bbsno = bbsno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentt() {
		return contentt;
	}
	public void setContentt(String contentt) {
		this.contentt = contentt;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDaydate() {
		return daydate;
	}
	public void setDaydate(String daydate) {
		this.daydate = daydate;
	}
	private String title;
	private String contentt;
	private String writer;
	private String daydate;
	
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date regdt;

	

}