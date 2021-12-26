package model;

import java.sql.Date;

public class Test {
	int id;
	int subid;
	String code;
	String title;
	int testtype;
	Date date;
	
	public Test(int id, int subid, String code, String title, int testtype, Date date) {
		super();
		this.id = id;
		this.subid = subid;
		this.code = code;
		this.title = title;
		this.testtype = testtype;
		this.date = date;
	}

	
	public Test(int subid, String code, String title, int testtype, Date date) {
		super();
		this.subid = subid;
		this.code = code;
		this.title = title;
		this.testtype = testtype;
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getSubid() {
		return subid;
	}


	public void setSubid(int subid) {
		this.subid = subid;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getTesttype() {
		return testtype;
	}


	public void setTesttype(int testtype) {
		this.testtype = testtype;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	public String toString() {
		return code;
	}
	
}
