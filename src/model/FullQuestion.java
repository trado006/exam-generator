package model;

import java.util.ArrayList;

public class FullQuestion {
	int cid;
	int type;
	int level;
	float score;
	String content;
	String choose;
	String answer1;
	String answer2;
	String answer3;
	
	public FullQuestion(int cid, int type, int level, String content) {
		super();
		this.cid = cid;
		this.type = type;
		this.level = level;
		this.content = content;
	}

	public FullQuestion(int cid, int type, int level, String content, String choose, String answer1, String answer2,
			String answer3) {
		super();
		this.cid = cid;
		this.type = type;
		this.level = level;
		this.content = content;
		this.choose = choose;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
	}
	
	public void setAnswers(String choose, String answer1, String answer2, String answer3) {
		this.choose = choose;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	public ArrayList<String> getAnswers() {
		ArrayList<String> sArr = new ArrayList<>();
		sArr.add(choose);
		if(answer1!=null&&!answer1.equals("")) {
			sArr.add(answer1);
		}
		if(answer2!=null&&!answer2.equals("")) {
			sArr.add(answer2);
		}
		if(answer3!=null&&!answer3.equals("")) {
			sArr.add(answer3);
		}
		return sArr;
	}
}
