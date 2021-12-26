package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import model.Struct;
import model.Template;

public class TemplateDao {
	// -1 no connect
	// 1 template
	// 2 the questionStruct
	public static int insertTemplateStruct(Template t,int subid,ArrayList<Struct> strList) {
		int x = -1;
		int id = insertTandId(t,subid);
		//System.out.printf("id "+id+"\n");
		if(id==-1) return x;
		x =1;
		if(StructDao.insertStructList(strList, id)) x++;
		return x;
	}
	public static int insertTandId(Template t,int subid) {
		String s1 = "insert into template(title,templatetype,easyfirst,subid) values(?,?,?,?);";
		String s2 = "select last_insert_id();";
		try {
		Connection conn = ConnectDB.getXAMPPConnection();
		PreparedStatement stmt = conn.prepareStatement(s1);
		stmt.setString(1, t.getTitle());
		stmt.setInt(2,t.getType());
		stmt.setInt(3, t.getEasyfirst());
		stmt.setInt(4, subid);
		stmt.execute();
		Statement stmt2 = conn.createStatement();
		ResultSet rs = stmt2.executeQuery(s2);
		if(rs.next()) {
		int x = rs.getInt(1);
		return x;
		}else return -1;
		}catch(Exception e) {
			return -1;
		}
	}
	// -1 no connect
	//  1 did
	public static int deleteTemplate(int templateid) {
		StructDao.deleteStructList(templateid);
		String s = "delete from template where (id="+templateid+");";
		return ConnectDB.excuteCheck(s);
	}
	
	public static boolean update(int tid,int earsyfirst) {
		String s = "update template set earsyfirst="+earsyfirst+" where id="+tid+";";
		try {
			ConnectDB.excuteCheck(s);
			return true;
		}catch(Exception e) {
			System.out.println("no connection at template database");
			return false;
		}
	}
	
	public static ArrayList<Template> getAllTemplate(int subid){
		ArrayList<Template> templateList = new ArrayList<Template>();
		String s = "select id,title,templatetype,easyfirst from template "
				+ "where subid = "+subid+" order by id;";
		Connection conn = ConnectDB.getXAMPPConnection();
		if (conn == null) return null;
		try {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			int type = rs.getInt("templatetype");
			int easyfirst = rs.getInt("easyfirst");
			templateList.add(new Template(id,title,type,easyfirst));
		}
		return templateList;
		}catch(Exception ex) {
			return null;
		}
	}
}
