package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Answer;

public class AnswerDao {
	
	public static ArrayList<Answer> getAnswerList(ArrayList<model.Question> ql){
		ArrayList<Answer> al = new ArrayList<Answer>();
		for(model.Question q:ql) {
			if(q.getType()==0) {
				al.add(new Answer(0, "", "", "", ""));
			}else {
				model.Answer a = getAnswer(q.getId());
				if(a==null) return null;
				al.add(a);
			}
		}
		return al;
	}
	
	public static Answer getAnswer(int qid) {
		String sql = "select choose,answer1,answer2,answer3 from answer where qid="+qid+";";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				return new Answer(qid, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			return new Answer(0,"","","","");
		}catch(Exception ex) {
			System.out.println("no connect AnswerDao");
			return null;
		}
	}
	
	// -1 no connect
    //  1 did
	public static int addAnswer(Answer a) {
		String s = "insert into answer(qid,choose,answer1,answer2,answer3) "
				+"values (?,?,?,?,?);";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
		PreparedStatement stmt = conn.prepareStatement(s);
		stmt.setInt(1, a.getQid());
		stmt.setString(2, a.getChoose());
		stmt.setString(3, a.getAnswer1());
		stmt.setString(4, a.getAnswer2());
		stmt.setString(5, a.getAnswer3());
		stmt.execute();
		return 1;
		}catch(Exception e) {
			return -1;
		}
	}
	// -1 no connect
    //  1 did
	public static int deleteAnswer(int qid) {
		String s = "delete from answer "
				+"where qid="+qid+";";
		return ConnectDB.excuteCheck(s);
	}
	public static boolean updateAnswer(Answer a) {
		String s = "update answer set choose=?,answer1=?,answer2=?,answer3=? where qid =?;";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(s);
			stmt.setString(1, a.getChoose());
			stmt.setString(2, a.getAnswer1());
			stmt.setString(3, a.getAnswer2());
			stmt.setString(4, a.getAnswer3());
			stmt.setInt(5, a.getQid());
			stmt.execute();
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
