package connection;

import connection.ConnectDB;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
public class UserDao {
	public static User getUser(User user) {
		String sql = "select id, username, password from userlist "
				+"where (username=?)and(password=?);";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("username");
				String password = rs.getString("password");
				return new User(id,name,password);
			}
			return new User(0,"","");
		}catch(Exception ex) {
			return null;
		}
	}
	public static int checkUserName(String name) {
		String sql = "select * from userlist where (username=?);";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
			return 0;
		}catch(Exception ex) {
			return -1;
		}
	}
	public static boolean addUser(User user) {
		String sql = "insert into userlist(username,password)"
				+" values(?,?);";
		Connection conn = ConnectDB.getXAMPPConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.execute();
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
