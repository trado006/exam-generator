package model;

import java.util.ArrayList;

public class InnerQuestion {
	int id;
	int testid;
	int qnumber;
	float score;
	String content;
	String answer1;
	String answer2;
	String answer3;
	String answer4;
	String choose;
	public static void main(String args[]) {
		float score = (float) 22.20022222222222222;
		System.out.println(score);
		String newScore = String.format("%.3f", score);
		
			ArrayList<Character> cs = new ArrayList<>();
			for(char c : newScore.toCharArray()) {
				cs.add(c);
			}
			while(!cs.isEmpty()) {
				if(cs.get(cs.size()-1)=='0') {
					cs.remove(cs.size()-1);
				}else {
					break;
				}
			}
			newScore = "";
			for(char c : cs) {
				newScore += c;
			}
			
				
		
	}
	
	public InnerQuestion(int id, int testid, int qnumber, float score, String content, String answer1, String answer2,
			String answer3, String answer4, String choose) {
		super();
		this.id = id;
		this.testid = testid;
		this.qnumber = qnumber;
		this.score = score;
		this.content = content;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.choose = choose;
	}
	
	public InnerQuestion(int testid, int qnumber, float score, String content, String answer1, String answer2,
			String answer3, String answer4, String choose) {
		super();
		this.testid = testid;
		this.qnumber = qnumber;
		this.score = score;
		this.content = content;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.choose = choose;
	}
	public String getFullChoose() {
		return "Câu "+qnumber+":"+"("+formatScore(score)+"đ)"+choose; 
	}
	public String getFullContent() {
		return "Câu "+qnumber+":"+"("+formatScore(score)+"đ)"+content;
	}
	public String getFullQuestionNumber() {
		return "Câu "+qnumber+":"+"("+formatScore(score)+"đ)";
	}
	private String formatScore(float score) {
		String newScore = String.format("%.3f", score);
		
		ArrayList<Character> cs = new ArrayList<>();
		for(char c : newScore.toCharArray()) {
			cs.add(c);
		}
		while(!cs.isEmpty()) {
			if(cs.get(cs.size()-1)=='0') {
				cs.remove(cs.size()-1);
			}else {
				break;
			}
		}
		newScore = "";
		for(char c : cs) {
			newScore += c;
		}
		return newScore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTestid() {
		return testid;
	}

	public void setTestid(int testid) {
		this.testid = testid;
	}

	public String getcontent() {
		return content;
	}

	public void setcontent(String content) {
		this.content = content;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getQnumber() {
		return qnumber;
	}

	public void setQnumber(int qnumber) {
		this.qnumber = qnumber;
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

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
}
