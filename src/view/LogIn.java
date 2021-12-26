package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import connection.UserDao;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setTitle("log in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(476, 309);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User name");
		lblNewLabel.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\Write.png"));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 84, 114, 33);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBackground(SystemColor.inactiveCaptionBorder);
		usernameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		usernameTextField.setBounds(134, 88, 316, 25);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\Key.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 136, 114, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel loginstatus = new JLabel("Log in");
		loginstatus.setForeground(Color.DARK_GRAY);
		loginstatus.setBackground(Color.WHITE);
		loginstatus.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\client.png"));
		loginstatus.setFont(new Font("Times New Roman", Font.BOLD, 26));
		loginstatus.setHorizontalAlignment(SwingConstants.CENTER);
		loginstatus.setBounds(10, 0, 440, 73);
		contentPane.add(loginstatus);
		
		JButton loginButton = new JButton("Log in");
		loginButton.setBackground(SystemColor.inactiveCaptionBorder);
		loginButton.setVerticalAlignment(SwingConstants.TOP);
		loginButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\login_20.pmg.png"));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getNoticeOnLogIn();
			}

		});
		
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		loginButton.setBounds(10, 193, 114, 33);
		contentPane.add(loginButton);
		
		JButton createButton = new JButton("Create");
		createButton.setBackground(SystemColor.inactiveCaptionBorder);
		createButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\add_20.png"));
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getNoticeOnCreate();
			}
		});
		createButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		createButton.setBounds(336, 191, 114, 33);
		contentPane.add(createButton);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBackground(SystemColor.inactiveCaptionBorder);
		passwordTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordTextField.setBounds(134, 140, 316, 25);
		contentPane.add(passwordTextField);
	}
	
	
	public void getNoticeOnCreate() {
		String name = usernameTextField.getText();
		String password = passwordTextField.getText(); 
		if((name.length()>20)||name.length()==0) {
			JOptionPane.showMessageDialog(this, "The length of name no available\n"
					+"The length >0 and <=20");
			return;
		}
		if((password.length()>20)||(password.length()==0)) {
			JOptionPane.showMessageDialog(this, "The length of password >20 no available\n"
					+"The length >0 and <=20");
			return;
		}
		switch(UserDao.checkUserName(name)) {
		case -1: 
			JOptionPane.showMessageDialog(this, "No connect to data base");
			return;
		case 1:
			JOptionPane.showMessageDialog(this, "Name available");
			return;
		case 0: 
			if(UserDao.addUser(new User(name,password))) {
				JOptionPane.showMessageDialog(this, "Add new account successfully");
			}else {
				JOptionPane.showMessageDialog(this, "No connect to data base"
						+"\nPlase check again later");
			}
		}
	}
	
	public void getNoticeOnLogIn() {
		String name = usernameTextField.getText();
		String password = passwordTextField.getText(); 
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "Let input the name");
			return;
		}
		if(password.equals("")) {
			JOptionPane.showMessageDialog(this, "Let input the password");
			return;
		}
		User user = UserDao.getUser(new User(name,password));
		if(user==null) {
			JOptionPane.showMessageDialog(this, "No connection to data base");
			return;
		}
		if(user.getId()==0) {
			JOptionPane.showMessageDialog(this, "Name or password on correctly");
			return;
		}
		Menu menu = new Menu(user);
		setVisible(false);
		menu.setVisible(true);
	}
	
	
}
