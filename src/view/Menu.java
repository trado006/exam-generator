package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import model.User;

import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Menu extends JFrame {

	private JPanel contentPane;
	private User user;
	private Menu menu;
	
	/**
	 * Create the frame.
	 */
	public Menu(User user) {
		this.user = user;
		menu = this;
		setTitle("Test library manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(423,427);
		this.setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setForeground(Color.BLACK);
		lblHome.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\Tools.png"));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblHome.setBounds(10, 11, 387, 42);
		contentPane.add(lblHome);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Question", TitledBorder.CENTER, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel.setBounds(36, 248, 138, 107);
		contentPane.add(panel);
		
		JButton addQuestionButton = new JButton("New");
		addQuestionButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addQuestionButton.setHorizontalAlignment(SwingConstants.LEFT);
		addQuestionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewQuestion newQuestion = new NewQuestion(menu,user.getId());
				menu.setVisible(false);
				newQuestion.setVisible(true);
			}
		});
		addQuestionButton.setBackground(SystemColor.inactiveCaptionBorder);
		addQuestionButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\add_20.png"));
		
		JButton subQuestionButton = new JButton("Manager");
		subQuestionButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		subQuestionButton.setHorizontalAlignment(SwingConstants.LEFT);
		subQuestionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuestionManager questionManager = new QuestionManager(menu, user.getId());
				menu.setVisible(false);
				questionManager.setVisible(true);
				
			}
		});
		subQuestionButton.setBackground(SystemColor.inactiveCaptionBorder);
		subQuestionButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\manage_20.png"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(addQuestionButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(subQuestionButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(addQuestionButton)
					.addGap(5)
					.addComponent(subQuestionButton))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Test", TitledBorder.CENTER, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1.setBounds(227, 130, 138, 107);
		contentPane.add(panel_1);
		
		JButton createTestButton = new JButton("New");
		createTestButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		createTestButton.setHorizontalAlignment(SwingConstants.LEFT);
		createTestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewTest newTest = new NewTest(menu,user.getId());
				menu.setVisible(false);
				newTest.setVisible(true);
			}
		});
		createTestButton.setBackground(SystemColor.inactiveCaptionBorder);
		createTestButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\add_20.png"));
		
		JButton managerTestButton = new JButton("Manager");
		managerTestButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		managerTestButton.setHorizontalAlignment(SwingConstants.LEFT);
		managerTestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestManager testManager = new TestManager(menu,user.getId());
				menu.setVisible(false);
				testManager.setVisible(true);
			}
		});
		managerTestButton.setBackground(SystemColor.inactiveCaptionBorder);
		managerTestButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\manage_20.png"));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(createTestButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(managerTestButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(createTestButton)
					.addGap(5)
					.addComponent(managerTestButton))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Table of content", TitledBorder.CENTER, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_2.setBounds(36, 53, 329, 66);
		contentPane.add(panel_2);
		
		JButton btnManager = new JButton("Manager");
		btnManager.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableOfContent tableOfContent = new TableOfContent(user.getId(),menu);
				menu.setVisible(false);
				tableOfContent.setVisible(true);
			}
		});
		btnManager.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.add(btnManager);
		btnManager.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\item_24.png"));
		
		JLabel lblWelcomeToThe = new JLabel("");
		lblWelcomeToThe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblWelcomeToThe.setForeground(Color.BLUE);
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setBounds(60, 370, 286, 17);
		lblWelcomeToThe.setText("Welcome to: "+user.getName());
		contentPane.add(lblWelcomeToThe);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Template", TitledBorder.CENTER, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_3.setBounds(36, 130, 138, 107);
		contentPane.add(panel_3);
		
		JButton btnNewTemplate = new JButton("New");
		btnNewTemplate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewTemplate.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewTemplate.setBackground(SystemColor.inactiveCaptionBorder);
		btnNewTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewTemplate newTemplate = new NewTemplate(menu,user.getId());
				menu.setVisible(false);
				newTemplate.setVisible(true);
			}
		});
		btnNewTemplate.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\add_20.png"));
		
		JButton btn_changeTemplate = new JButton("Manager");
		btn_changeTemplate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_changeTemplate.setHorizontalAlignment(SwingConstants.LEFT);
		btn_changeTemplate.setBackground(SystemColor.inactiveCaptionBorder);
		btn_changeTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TemplateManager templateManager = new TemplateManager(menu, user.getId());
				menu.setVisible(false);
				templateManager.setVisible(true);
			}
		});
		btn_changeTemplate.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\manage_20.png"));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewTemplate, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_changeTemplate, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(5)
					.addComponent(btnNewTemplate)
					.addGap(5)
					.addComponent(btn_changeTemplate))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.window);
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Exit", TitledBorder.CENTER, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_4.setBounds(227, 248, 138, 107);
		contentPane.add(panel_4);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogOut.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn login = new LogIn();
				menu.setVisible(false);
				login.setVisible(true);
			}
		});
		btnLogOut.setBackground(SystemColor.inactiveCaptionBorder);
		btnLogOut.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\out_20.png"));
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExit.setHorizontalAlignment(SwingConstants.LEFT);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(SystemColor.inactiveCaptionBorder);
		btnExit.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\cancel.png"));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(5)
					.addComponent(btnLogOut)
					.addGap(5)
					.addComponent(btnExit))
		);
		panel_4.setLayout(gl_panel_4);
	}
}
