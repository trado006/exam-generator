package connection;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.InnerQuestion;

public class InnerQuestionDao {
	// -1 no connect
	// 1 did
	public static int insertInnerQuestionList(ArrayList<InnerQuestion> innerQuestionList, int testid) {
		for(InnerQuestion innerQuestion:innerQuestionList) {
			if(insertInnerQuestion(innerQuestion,testid)==-1) return -1;
		}
		return 1;
	}
	// -1 no connect
	// 1 did
	public static int insertInnerQuestion(InnerQuestion innerQuestion, int testid) {
		String s = "insert into innerquestion(testid,qnumber,score,content,answer1,answer2,answer3,answer4,choose) values(?,?,?,?,?,?,?,?,?)";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
		PreparedStatement stmt = conn.prepareStatement(s);
		stmt.setInt(1, testid);
		stmt.setInt(2, innerQuestion.getQnumber());
		stmt.setFloat(3, innerQuestion.getScore());
		stmt.setString(4,innerQuestion.getcontent());
		stmt.setString(5, innerQuestion.getAnswer1());
		stmt.setString(6, innerQuestion.getAnswer2());
		stmt.setString(7, innerQuestion.getAnswer3());
		stmt.setString(8, innerQuestion.getAnswer4());
		stmt.setString(9, innerQuestion.getChoose());
		if(!stmt.execute()) return 1;
		else return -1;
		}catch(Exception e) {
			return -1;
		}
	}
	public static ArrayList<InnerQuestion> getInnerQuestionList(int testid){
		String s = "select id,qnumber,score,content,answer1,answer2,answer3,answer4,choose from innerquestion where (testid=?) order by qnumber asc;";
		ArrayList<InnerQuestion> innerQuestionList = new ArrayList<InnerQuestion>();
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
		PreparedStatement stmt = conn.prepareStatement(s);
		stmt.setInt(1, testid);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
		int id = rs.getInt("id");
		int qnumber = rs.getInt("qnumber");
		float score = rs.getFloat("score");
		String content = rs.getString("content");
		String answer1 = rs.getString("answer1");
		String answer2 = rs.getString("answer2");
		String answer3 = rs.getString("answer3");
		String answer4 = rs.getString("answer4");
		String choose = rs.getString("choose");
		innerQuestionList.add(new InnerQuestion(id,testid , qnumber, score, content, answer1, answer2, answer3, answer4, choose));
		}
		return innerQuestionList;
		}catch(Exception e) {
			return null;
		}
	}
	// -1 no connect
	//  1 did
	public static int deleteInnerQuestionList(int testid) {
		String s = "delete from innerquestion where testid="+testid+";";
		return ConnectDB.excuteCheck(s);
	}
}
