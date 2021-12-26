package connection;

import connection.ConnectDB;
import model.Subject;
import model.User;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class SubjectDao {
	public static ArrayList<Subject> getSubjectList(int userid) {
		ArrayList<Subject> subList = new ArrayList<Subject>(); 
		String s = "select id,name from subject "
				+ "where userid = "+userid+";";
		Connection conn = ConnectDB.getXAMPPConnection();
		if (conn == null) return null;
		try {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			subList.add(new Subject(id,name));
		}
		return subList;
		}catch(Exception ex) {
			System.out.println("no connection");
		}
		return null;
	}
	// -1 no connect or error
	// 0 name error
    //  1 did
	public static int addSubject(String subname,int userid) {
		String s = "insert into subject(name,userid) "
				+"values('"+subname+"',"+userid+");";
		return ConnectDB.excuteCheck(s);
	}
	// -1 no connect
    //  0 non available
    //  1 available
	public static int checkSubjectName(String subname, int userid) {
		String s = "select id,name from subject "
				+ "where (userid="+userid+")and(name='"+subname+"');";
		return ConnectDB.excuteQueryCheck(s);
	}
	// -1 no connect or error
	// 0 no available
    //  1 did
	public static int updateSubject(Subject sub) {
		String s = "update subject "
				+"set name='" +sub.getName()+"' "
				+"where id="+sub.getId()+";";
		return ConnectDB.excuteCheck(s);
	}
	// -1 no connect or error
	// no available
    //  1 did
	public static int deleteSubject(int subid) {
		ChapterDao.deleteChapterList(subid);
		String s = "delete from subject where id="+subid+";";
		return ConnectDB.excuteCheck(s);
	}
}







