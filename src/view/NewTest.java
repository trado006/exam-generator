package view;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import connection.SubjectDao;
import connection.TemplateDao;
import connection.TestDao;
import control.TestControl;
import io.WriteTextFile;
import model.*;

import java.awt.SystemColor;

public class NewTest extends JFrame {
	Menu menu;
	NewTest thisFrame;
	private JPanel contentPane;
	private JTextField code;
	JTextPane textPane;
	JComboBox titleComboBox;
	JComboBox subComboBox;
	
	private int userid;
	
	TestControl testControl = null;
	
	private DefaultComboBoxModel<Subject> subModel;
	private DefaultComboBoxModel<Template> temModel;
	

	/**
	 * Create the frame.
	 */
	public NewTest(Menu menu,int userid) {
		thisFrame = this;
		this.menu = menu;
		this.userid = userid;
		prepareData();
		setTitle("New Test");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(714,679);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewAndOutput = new JLabel("New Test");
		lblViewAndOutput.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\option.png"));
		lblViewAndOutput.setForeground(Color.DARK_GRAY);
		lblViewAndOutput.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblViewAndOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewAndOutput.setBounds(164, 11, 371, 48);
		contentPane.add(lblViewAndOutput);
		
		JLabel lblTestCode = new JLabel("Test code:");
		lblTestCode.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTestCode.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\testcode.png"));
		lblTestCode.setBounds(10, 595, 90, 33);
		contentPane.add(lblTestCode);
		
		JLabel lblTheTest = new JLabel("The test");
		lblTheTest.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\tests_32.png"));
		lblTheTest.setBounds(10, 153, 138, 41);
		contentPane.add(lblTheTest);
		
		JButton btnView = new JButton("View");
		btnView.setBackground(SystemColor.inactiveCaptionBorder);
		btnView.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\view_20.png"));
		btnView.setBounds(587, 111, 101, 30);
		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewButtonListener();
			}
			
		});
		contentPane.add(btnView);
		
		JButton btnCancel = new JButton("Out");
		btnCancel.setBackground(SystemColor.inactiveCaptionBorder);
		btnCancel.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\cancel.png"));
		btnCancel.setBounds(604, 596, 84, 30);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnCancelListener();
			}
			
		});
		contentPane.add(btnCancel);
		
		
		
		JButton saveOnline = new JButton("Push");
		saveOnline.setBackground(SystemColor.inactiveCaptionBorder);
		saveOnline.setToolTipText("Save Online");
		saveOnline.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\top.png"));
		saveOnline.setBounds(265, 596, 101, 30);
		saveOnline.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveOnlineButtonListener();
			}
			
		});
		contentPane.add(saveOnline);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 196, 678, 388);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setBackground(SystemColor.inactiveCaptionBorder);
		textPane.setEditable(false);
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textPane.setToolTipText("tap text to change");
		scrollPane.setViewportView(textPane);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\book.png"));
		lblSubject.setBounds(9, 70, 84, 33);
		contentPane.add(lblSubject);
		
		subComboBox = new JComboBox(subModel);
		subComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		subComboBox.setBounds(97, 75, 469, 25);
		subComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subComboBoxListener();
			}
			
		});
		contentPane.add(subComboBox);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\title.png"));
		lblTitle.setBounds(10, 110, 84, 32);
		contentPane.add(lblTitle);
		
		titleComboBox = new JComboBox(temModel);
		titleComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		titleComboBox.setBounds(97, 115, 469, 25);
		titleComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				titleComboBoxListener();
			}
			
		});
		contentPane.add(titleComboBox);
		
		code = new JTextField();
		code.setFont(new Font("Tahoma", Font.BOLD, 12));
		code.setHorizontalAlignment(SwingConstants.RIGHT);
		code.setBackground(SystemColor.inactiveCaptionBorder);
		code.setBounds(97, 598, 97, 26);
		contentPane.add(code);
		code.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(SystemColor.inactiveCaptionBorder);
		btnReset.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\undo_20.png"));
		btnReset.setBounds(587, 159, 101, 30);
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resetButtonListener();
			}
			
		});
		contentPane.add(btnReset);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(SystemColor.inactiveCaptionBorder);
		btnSave.setToolTipText("Save on this computer");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveButtonListener();
			}
		});
		btnSave.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\memory_save_20.png"));
		btnSave.setBounds(376, 596, 101, 30);
		contentPane.add(btnSave);
		
		JButton btnSaveAnswer = new JButton("Answer");
		btnSaveAnswer.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\memory_save_20.png"));
		btnSaveAnswer.setBackground(SystemColor.inactiveCaptionBorder);
		btnSaveAnswer.setToolTipText("Save Answer on this computer");
		btnSaveAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAnswerActionListener();
			}
		});
		btnSaveAnswer.setBounds(487, 596, 107, 30);
		contentPane.add(btnSaveAnswer);
	}
	
	public void saveAnswerActionListener() {
		if(!textPane.getText().equals("")) {
			if(code.getText().contentEquals("")) {
				JOptionPane.showMessageDialog(this, "No code","Error",JOptionPane.ERROR_MESSAGE); return;
			}
			ArrayList<InnerQuestion> iQs = testControl.getInnerQuestionList();
			if(control.InnerQuestionControl.getMultipleQuestions(iQs)==0) {
				JOptionPane.showMessageDialog(this, "No multiple-choice question");
				return;
			}
			long millis = System.currentTimeMillis();
			Template t = (Template)titleComboBox.getSelectedItem();
	        java.sql.Date date = new java.sql.Date(millis);
			String s = "\n"+t.getTitle()+"\n\n"+"Test Code: "+code.getText()+"\t"
					+"      Date: "+date.toString()+"\n";
			s += control.InnerQuestionControl.getTestAnswer(iQs);
			WriteTextFile.writeTextFile(s, "Save a Test file");
		}else {
			JOptionPane.showMessageDialog(this, "No test","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void prepareData() {
		subModel = new DefaultComboBoxModel<>();
		temModel = new DefaultComboBoxModel<>();
		ArrayList<Subject> subList = SubjectDao.getSubjectList(userid);
		if(subList==null) {
			JOptionPane.showMessageDialog(this, "Error no connection","Error",JOptionPane.ERROR_MESSAGE);
		}else {
			subModel.addAll(subList);
		}
	}
	
	public void viewButtonListener() {
		if(testControl!=null) {
			if(!textPane.getText().equals("")) {
				int x = JOptionPane.showConfirmDialog(this, "The test will be lost","Notification",JOptionPane.OK_CANCEL_OPTION);
				if(x==2) return;
			}
			if(!testControl.prepareStrList()) {
				JOptionPane.showMessageDialog(this, "No connection","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!testControl.removeZero()) {
				JOptionPane.showMessageDialog(this, "Question in template is zero","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!testControl.checkStrList()) {
				JOptionPane.showMessageDialog(this, "Data not ready","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!testControl.getFullQuestions()) {
				JOptionPane.showMessageDialog(this, "No connection","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			testControl.sort();
			testControl.setInnerQuestionList();
			textPane.setText(testControl.getTestUnit());
			JOptionPane.showMessageDialog(this, "Create the test successfully","successfully",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void saveOnlineButtonListener() {
		if(!textPane.getText().equals("")) {
			if(code.getText().equals("")||code.getText().length()>10) {
				JOptionPane.showMessageDialog(this, "Code not ready","Error",JOptionPane.ERROR_MESSAGE);
			}else {
				long millis = System.currentTimeMillis();
				Template t = (Template)titleComboBox.getSelectedItem();
		        java.sql.Date date = new java.sql.Date(millis);
		        Test test = new Test(((Subject)subComboBox.getSelectedItem()).getId(),code.getText()
		        		, t.getTitle(),t.getType(),date);
		        ArrayList<InnerQuestion> innerQList = testControl.getInnerQuestionList();
				if(TestDao.insert(test, innerQList)) {
					code.setText("");
					JOptionPane.showMessageDialog(this, "Save to data base successfully","successfully",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(this, "No connect to data base","Error connect",JOptionPane.ERROR_MESSAGE);
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "No test","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void subComboBoxListener() {
		if(subComboBox.getSelectedIndex()==-1) return;
		int subid = ((Subject)subComboBox.getSelectedItem()).getId();
		ArrayList<Template> temList = TemplateDao.getAllTemplate(subid);
		if(temList==null) {
			JOptionPane.showMessageDialog(this, "Error no connection","Error",JOptionPane.ERROR_MESSAGE);
		}else {
			temModel.removeAllElements();
			temModel.addAll(temList);
			titleComboBox.setSelectedIndex(-1);
			textPane.setText("");
		}
	}

	public void titleComboBoxListener() {
		if(titleComboBox.getSelectedIndex()==-1) {
			//do nothing
		}else {
			testControl = new TestControl();
			testControl.setTemplate((Template)titleComboBox.getSelectedItem());
			textPane.setText("");
		}
	}

	public void resetButtonListener() {
		if(!textPane.getText().equals("")) {
			testControl.sort();
			testControl.setInnerQuestionList();
			textPane.setText(testControl.getTestUnit());
			JOptionPane.showMessageDialog(this, "Reset test successfully","Successfully",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "No test","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void saveButtonListener() {
		if(!textPane.getText().equals("")) {
			if(code.getText().contentEquals("")) {
				JOptionPane.showMessageDialog(this, "No code","Error",JOptionPane.ERROR_MESSAGE); return;
			}
			long millis = System.currentTimeMillis();
			Template t = (Template)titleComboBox.getSelectedItem();
	        java.sql.Date date = new java.sql.Date(millis);
			String s = "\n"+t.getTitle()+"\n\n"+"Test Code: "+code.getText()+"\t"
					+"      Date: "+date.toString()+"\n";
			s += textPane.getText();
			WriteTextFile.writeTextFile(s, "Save a Test file");
		}else {
			JOptionPane.showMessageDialog(this, "No test","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void next(int subid,int temid) {
		if(subModel.getSize()==0) {
			JOptionPane.showMessageDialog(this, "No connect to data base","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int subIndex = -1;
		for(int i=0;i<subModel.getSize();i++) {
			if(subModel.getElementAt(i).getId()==subid) {
				subIndex = i;
				break;
			}
		}
		if(subIndex==-1) {
			JOptionPane.showMessageDialog(this, "This option no ative\n"
					+"Plase try again later","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		subComboBox.setSelectedIndex(subIndex);
		if(temModel.getSize()==0) {
			JOptionPane.showMessageDialog(this, "No connect to data base","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int temIndex = -1;
		for(int i=0;i<temModel.getSize();i++) {
			if(temModel.getElementAt(i).getId()==temid) {
				temIndex = i;
				break;
			}
		}
		if(temIndex==-1) {
			JOptionPane.showMessageDialog(this, "This option no ative\n"
					+"Plase try again later","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		titleComboBox.setSelectedIndex(temIndex);
		viewButtonListener();
	}
	
	public void btnCancelListener(){
		int x = JOptionPane.showConfirmDialog(this, "Do you realy want to exit","Notification",JOptionPane.OK_CANCEL_OPTION);
		if(x!=0) return;
		thisFrame.setVisible(false);
		menu.setVisible(true);
	}
}
