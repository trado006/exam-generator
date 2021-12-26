package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import model.Chapter;
import model.Subject;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class ChapterDao {
	public static ArrayList<Chapter> getChapterList(int subid){
		ArrayList<Chapter> cList = new ArrayList<Chapter>(); 
		String s = "select id,name from chapter "
				+ "where subid = "+subid+";";
		Connection conn = ConnectDB.getXAMPPConnection();
		if (conn == null) return null;
		try {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			cList.add(new Chapter(id,name));
		}
		return cList;
		}catch(Exception ex) {
			System.out.println("no connection");
			return null;
		}
	}
	// -1 no connect
    //  0 non available
    //  1 available
	public static int checkChapterName(String cname,int subid) {
		String s = "select id,name from chapter "
				+ "where (subid="+subid+")and(name='"+cname+"');";
		return ConnectDB.excuteQueryCheck(s);
	}
	// -1 no connect or error
    //  1 did
	public static int addChapter(String cname,int subid) {
		String s = "insert into chapter(name,subid) "
				+"values('"+cname+"',"+subid+");";
		Connection conn = ConnectDB.getXAMPPConnection();
		return ConnectDB.excuteCheck(s);
	}
	// -1 no connect or error
    //  1 did
	public static int updateChapter(Chapter c) {
		String s = "update chapter "
				+"set name='" +c.getName()+"' "
				+"where id="+c.getId()+";";
		return ConnectDB.excuteCheck(s);
	}
	// -1 no connect or error
    //  1 did
	public static int deleteChapter(int cid) {
		QuestionDao.deleteQuestionList(cid);
		String s = "delete from chapter " + 
				"where id="+cid+";";
		return ConnectDB.excuteCheck(s);
	}
	// -1 no connect or error
    //  1 did
	public static int deleteChapterList(int subid) {
		String s = "select id " + 
				"from chapter " + 
				"where subid ="+subid+";";
		Connection conn = ConnectDB.getXAMPPConnection();
		if (conn == null) return -1;
		ArrayList<Integer> idChapList = new ArrayList<Integer>();
		try {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		while(rs.next()) {
			idChapList.add(rs.getInt("id"));
		}
		}catch(Exception ex) {
			return -1;
		}
		for(int cid : idChapList) {
			int status=ChapterDao.deleteChapter(cid);
			if(status!=1)
				return status;
		}
		return 1;
	}
}









