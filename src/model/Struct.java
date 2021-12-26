package model;

import java.util.ArrayList;

public class Struct {
	int id;
	int cid;
	String cName;
	int count;
	int type;
	int level;
	float totalScore;
	int templateid;
	
	
	public Struct(int cid, int type, int count, int level, float totalScore) {
		super();
		this.cid = cid;
		this.type = type;
		this.count = count;
		this.level = level;
		this.totalScore = totalScore;
	}
	
	public Struct(int id, int cid, String cName, int type, int count, int level, float totalScore) {
		super();
		this.id = id;
		this.cid = cid;
		this.cName = cName;
		this.type = type;
		this.count = count;
		this.level = level;
		this.totalScore = totalScore;
	}
	
	public Struct(int cid, String cName, int type, int count, int level, float totalScore) {
		super();
		this.cid = cid;
		this.cName = cName;
		this.type = type;
		this.count = count;
		this.level = level;
		this.totalScore = totalScore;
	}


	public Struct() {
		// TODO Auto-generated constructor stub
	}


	public boolean checkEqual(int cid,int level,int type) {
		if(this.cid==cid&&this.level==level&&this.type==type) {
			return true;
		}else {
			return false;
		}
	}
	public static float getScores(ArrayList<Struct> strList) {
		float scores = 0;
		for(Struct str : strList) {
			scores += str.getTotalScore();
		}
		return scores;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public float getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}


	public String toString() {
		String sLevel=null;
		switch(level) {
			case 0: sLevel = "easy";break;
			case 1: sLevel = "normal";break;
			case 2: sLevel = "hard";break;
		}
		String stype=null;
		switch(type) {
		case 0: stype = "foreword"; break;
		case 1:stype = "multiple-choice"; break;
		}
		String s = "chapter name: "+cName+"\n"
				+",count: "+count+"\t"
				+",type: "+stype+"\t"+",level: "+sLevel+"\t"+",total score: "+totalScore;
		return s;
	}
	
}
