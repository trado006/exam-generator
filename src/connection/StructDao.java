package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import connection.InnerQuestionDao;
import model.Struct;

public class StructDao {
	// -1 no connect
	//  1 did
	public static void main(String args[]) {
		int x = insertStruct(new Struct(2,12,1,3,5),29);
		System.out.println(x);
	}
	
	public static boolean insertStructList(ArrayList<Struct> strList,int tid) {
		for(Struct str:strList) {
			if(StructDao.insertStruct(str, tid)==-1) {
				return false;
			}
		}
		return true;
	}
	public static int insertStruct(Struct str,int templateid) {
		String s = "insert into struct(cid,qcount,qtype,qlevel,totalscore,templateid) values(?,?,?,?,?,?);";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
		PreparedStatement stmt = conn.prepareStatement(s);
		stmt.setInt(1, str.getCid());
		stmt.setInt(2, str.getCount());
		stmt.setInt(3, str.getType());
		stmt.setInt(4, str.getLevel());
		stmt.setFloat(5, str.getTotalScore());
		stmt.setInt(6, templateid);
		if(!stmt.execute()) return 1;
		else return -1;
		}catch(Exception e) {
			return -1;
		}
	}
	public static boolean updateList(ArrayList<Struct> strList) {
		for(Struct str : strList) {
			if(!update(str)) return false;
		}
		return true;
	}
	public static boolean update(Struct str) {
		String s = "update struct set qcount=?,totalscore=? where id=?;";
		try {
			Connection conn = ConnectDB.getXAMPPConnection();
			PreparedStatement stmt = conn.prepareStatement(s);
			stmt.setInt(1, str.getCount());
			stmt.setFloat(2, str.getTotalScore());
			stmt.setInt(3, str.getId());
			stmt.execute();
			return true;
		}catch(Exception ex) {
			System.out.println("no connect in struct database");
			return false;
		}
	}
	// -1 no connect
	//  1 did
	public static int deleteStructList(int templateid) {
		String s = "delete from struct where (templateid="+templateid+");";
		return ConnectDB.excuteCheck(s);
	}
	// null no connect
	public static ArrayList<Struct> getAllStruct(int templateid){
		ArrayList<Struct> structList = new ArrayList<Struct>(); 
		String s = "select struct.id,cid,name,qcount,qtype,qlevel,totalscore "
				+"from struct inner join chapter on struct.cid=chapter.id "
				+"where (templateid="+templateid+") order by cid,qtype,qlevel ;";
		Connection conn = ConnectDB.getXAMPPConnection();
		if (conn == null) return null;
		try {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		while(rs.next()) {
			int id = rs.getInt("struct.id");
			int cid = rs.getInt("cid");
			String cname = rs.getString("name");
			int count = rs.getInt("qcount");
			int type = rs.getInt("qtype");
			int level = rs.getInt("qlevel");
			float totalScore = rs.getFloat("totalscore");
			structList.add(new Struct(id, cid,cname, type, count, level, totalScore));
		}
		return structList;
		}catch(Exception ex) {
			return null;
		}
	}
	
}
