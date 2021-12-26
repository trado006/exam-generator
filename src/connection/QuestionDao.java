package connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Answer;
import model.Question;

public class QuestionDao {
	// -1 no connect
    //  1 did
	public static int addForewordQuestion(Question q,int cid) {
		String s = "insert into question(content,qtype,qlevel,cid) "
				+"values(?,?,?,?);";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
		PreparedStatement stmt = conn.prepareStatement(s);
		stmt.setString(1, q.getContent());
		stmt.setInt(2, q.getType());
		stmt.setInt(3, q.getLevel());
		stmt.setInt(4, cid);
		stmt.execute();
		return 1;
		}catch(Exception e) {
			return -1;
		}
	}
	// -1: no connect
	//  1: insert question
	//  2: insert answer and question
	public static int addMultiple_ChooseQuestion(Question q, int cid,Answer a) {
		String s1 = "insert into question(content,qtype,qlevel,cid) "
				+"values(?,?,?,?);";
		String s2 = "select last_insert_id();";
		int x = -1, qid;
		try {
			Connection conn = ConnectDB.getXAMPPConnection();
			PreparedStatement stmt = conn.prepareStatement(s1);
			stmt.setString(1, q.getContent());
			stmt.setInt(2, q.getType());
			stmt.setInt(3, q.getLevel());
			stmt.setInt(4, cid);
			stmt.execute();
			x = 1;
			Statement stmt2 = conn.createStatement();
	    	ResultSet rs = stmt2.executeQuery(s2);
	    	if(rs.next()) {
	    		qid = rs.getInt(1);
	    	}else return x;
	    	a.setQid(qid);
	    	// -1 no connect
	        //  1 did
	    	if(AnswerDao.addAnswer(a)==-1) return x;
	    	x++;
	    	return x;
	    	}catch(Exception e) {
	    		return x;
	    	}
	}
	public static boolean update(Question q) {
		String sql = "update question set content=?,qlevel=? where id=?;";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, q.getContent());
			stmt.setInt(2, q.getLevel());
			stmt.setInt(3, q.getId());
			stmt.execute();
			return true;
		}catch(Exception ex) {
			System.out.println("no connection at update in QuestionDao");
			return false;
		}
	}
	public static int deleteQuestion(Question q) {
		String s = "delete from question "
				+"where id = "+q.getId()+";";
		if(q.getType()==0)
			return ConnectDB.excuteCheck(s);
			else {
				AnswerDao.deleteAnswer(q.getId());
				return ConnectDB.excuteCheck(s);
			}
	}
	public static ArrayList<Question> getQuestionList(int cid){
		String s = "select id,content,qtype,qlevel from question where cid="+cid+";";
		Connection conn = ConnectDB.getXAMPPConnection();
		ArrayList<Question> questionList = new ArrayList<Question>();
		try {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		while(rs.next()) {
			int id = rs.getInt(1);
			String content = rs.getString(2);
			int type = rs.getInt(3);
			int level = rs.getInt(4);
			questionList.add(new Question(id, content, type, level));
		}
		return questionList;
		}catch(Exception ex) {
			System.out.println("no connect QuestionDao");
			return null;
		}
	}
	// -1 no connection
	public static int getQIndexs(int cid,int level,int type) {
		String s = "select count(id) from question where (cid="+cid+")and(qlevel="+level+")and(qtype="+type+");";
		int count=0;
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		while(rs.next()) {
			count=rs.getInt(1);
		}
		return count;
		}catch(Exception e) {
			return -1;
		}
	
	}
	
	public static int deleteQuestionList(int cid) {
		String s1 = "delete from answer " + 
				"where qid in (select id from question " + 
				"where cid="+cid+");";
		ConnectDB.excuteCheck(s1);
		String s2 = "delete from question "
				+"where cid="+cid+";";
		return ConnectDB.excuteCheck(s2);
	}
}





