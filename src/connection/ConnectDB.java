package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Question;
 
public class ConnectDB {
    private static String DB_URL = "jdbc:mysql://localhost:3306/thuvien";
    private static String USER_NAME = "root";
    private static String PASSWORD = "hoanghieu";
    
    public static void main(String args[]) {
    	String s = "select id,content,qtype,qlevel from question where (cid=?)and(qtype=?);";
		Connection conn = ConnectDB.getXAMPPConnection();
		ArrayList<Question> questionList = new ArrayList<Question>();
		try {
		PreparedStatement stmt = conn.prepareStatement(s);
		stmt.setInt(1, 2);
		stmt.setString(2, "1");
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1));
		}
		}catch(Exception ex) {
			System.out.println("no connect QuestionDao");
		}
    }
    public static Connection getXAMPPConnection() {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            return conn;
        } catch (Exception ex) {
            System.out.println("error connect to database");
        }
        return null;
    }
    // -1 no connect
    // 0 khong hop le
    //  1 did
    public static int excuteCheck(String s) {
    	Connection conn = ConnectDB.getXAMPPConnection();
		if (conn == null) return -1;
		try {
			Statement stmt = conn.createStatement();
			if(stmt.execute(s)==false) return 1;
			else return 0;
		}catch(Exception ex) {
			return -1;
		}
    }
    // -1 no connect
    //  0 non available
    //  1 available
    public static int excuteQueryCheck(String s) {
    	Connection conn = ConnectDB.getXAMPPConnection();
		if (conn == null) return -1;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			if(rs.next()) return 1;
			else return 0;
		}catch(Exception ex) {
			return -1;
		}
    }
}

