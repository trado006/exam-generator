package connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import model.*;
import model.Struct;

public class FullQuestionDao {
	public static ArrayList<FullQuestion> getFullQuestions(ArrayList<Struct> strList){
		ArrayList<FullQuestion> fullQuestions = new ArrayList<FullQuestion>();
		for(Struct str:strList) {
			ArrayList<Integer> indexs = getIdsFromStr(str);
			if(indexs==null) return null;
			for(int i:indexs) {
				FullQuestion fq = getFullQuestion(i);
				if(fq==null) return null;
				fq.setScore(str.getTotalScore()/str.getCount());
				fullQuestions.add(fq);
			}
		}
		return fullQuestions;
	}
	public static ArrayList<Integer> getIdsFromStr(Struct str){
		String s = "select id from question where (cid=?)and(qtype=?)and(qlevel=?);";
		ArrayList<Integer> allQ = new ArrayList<Integer>();
		ArrayList<Integer> choiceQ = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getXAMPPConnection();
			PreparedStatement stmt = conn.prepareStatement(s);
			stmt.setInt(1, str.getCid());
			stmt.setInt(2, str.getType());
			stmt.setInt(3, str.getLevel());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				allQ.add(rs.getInt(1));
			}
			int count = str.getCount();
			Random rand = new Random();
			for(int i=0;i<count;i++) {
				int index = rand.nextInt(allQ.size());
				choiceQ.add(allQ.get(index));
				allQ.remove(index);
			}
			return choiceQ;
		}catch(Exception ex) {
			System.out.println("error getIdsfromstr in fullquestiondao");
			return null;
		}
	}
	public static  FullQuestion getFullQuestion(int qid) {
		String s = "select content,qtype,qlevel,cid from question where id="+qid+";";
		String s2 = "select choose,answer1,answer2,answer3 from answer where qid="+qid+";";
		FullQuestion fullQuestion = null;
		try {
			Connection conn = ConnectDB.getXAMPPConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while(rs.next()) {
				fullQuestion = new FullQuestion(rs.getInt("cid"),rs.getInt("qtype"),rs.getInt("qlevel"),rs.getString("content"));
			}
			if(fullQuestion.getType()==1) {
				ResultSet rs2 = stmt.executeQuery(s2);
				while(rs2.next()) {
					fullQuestion.setAnswers(rs2.getString("choose"), rs2.getString("answer1"), rs2.getString("answer2"), rs2.getString("answer3"));
				}
			}
			return fullQuestion;
		}catch(SQLException ex) {
			System.out.println("error at getfullquestion in fullquestiondao");
			return null;
		}
	}
	// 0 no error
	public static boolean CheckListAvailable(ArrayList<Struct> strList){
		for(Struct str:strList) {
			if(checkStrAvailable(str)) continue;
			else {
				return false;
			}
		}
		return true;
	}
	public static boolean checkStrAvailable(Struct str) {
		String s = "select count(*) from question where (cid=?)and(qtype=?)and(qlevel=?);";
		try {
			Connection conn = ConnectDB.getXAMPPConnection();
			PreparedStatement stmt = conn.prepareStatement(s);
			stmt.setInt(1, str.getCid());
			stmt.setInt(2, str.getType());
			stmt.setInt(3, str.getLevel());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				if(str.getCount()>rs.getInt(1)) return false;
				else return true;
			}
		}catch(Exception ex) {
			return false;
		}
		return false;
	}
}
