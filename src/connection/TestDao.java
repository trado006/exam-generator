package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.InnerQuestion;
import model.Test;

public class TestDao {
	public static ArrayList<Test> getTestList(int subid) {
		String s = "select id,code,title,testtype,createdate from test where subid="+subid+";";
		ArrayList<Test> tList = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getXAMPPConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while(rs.next()) {
				tList.add(new Test(rs.getInt("id"),subid,rs.getString("code"),rs.getString("title"),rs.getInt("testtype"),rs.getDate("createdate")));
			}
			return tList;
		}catch(Exception ex) {
			System.out.println("no connect at getTestList in TestDao");
			return null;
		}
	}
	public static boolean insert(Test test,ArrayList<InnerQuestion> innerQList) {
		int testid = insert(test);
		if(testid==-1) return false;
		else {
			InnerQuestionDao.insertInnerQuestionList(innerQList, testid);
		}
		return true;
	}
	public static boolean delete(int testid) {
		String s = "delete from test where id="+testid+";";
		int x = InnerQuestionDao.deleteInnerQuestionList(testid);
		if(x==-1) return false;
		else {
			if(ConnectDB.excuteCheck(s)==1) return true;
		}
		return false;
	}
	private static int insert(Test test) {
		String s = "insert into test(subid,code,title,testtype,createdate) values (?,?,?,?,?);";
		String s2 = "select last_insert_id();";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
		PreparedStatement stmt = conn.prepareStatement(s);
		stmt.setInt(1, test.getSubid());
		stmt.setString(2, test.getCode());
		stmt.setString(3,test.getTitle());
		stmt.setInt(4, test.getTesttype());
		stmt.setDate(5, test.getDate());
		stmt.execute();
		Statement stmt2 = conn.createStatement();
		ResultSet rs = stmt2.executeQuery(s2);
		while(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
		}catch(SQLException ex) {
			System.out.println("no connection at testDao");
			return -1;
		}
	}
}
