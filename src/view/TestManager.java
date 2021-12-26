package view;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import connection.InnerQuestionDao;
import connection.SubjectDao;
import connection.TestDao;
import io.WriteTextFile;
import model.*;

import java.awt.SystemColor;

public class TestManager extends JFrame {
	Menu menu;
	private int userid;
	
	TestManager thisFrame;
	private JPanel contentPane;
	
	JComboBox<Subject> subComboBox;
	JList<Test> codeList;
	JTextPane textPane;
	
	private ArrayList<InnerQuestion> iQs;
	private DefaultComboBoxModel<Subject> subModel;
	private DefaultListModel<Test> codeModel;


	/**
	 * Create the frame.
	 */
	public TestManager(Menu menu,int userid) {
		thisFrame = this;
		this.menu = menu;
		this.userid = userid;
		prepareData();
		setTitle("Test manager");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(714, 679);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewAndOutput = new JLabel("Test manager");
		lblViewAndOutput.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\option.png"));
		lblViewAndOutput.setForeground(Color.DARK_GRAY);
		lblViewAndOutput.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblViewAndOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewAndOutput.setBounds(164, 11, 371, 48);
		contentPane.add(lblViewAndOutput);
		
		JLabel lblTestCode = new JLabel("Test code:");
		lblTestCode.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\testcode.png"));
		lblTestCode.setBounds(424, 70, 90, 33);
		contentPane.add(lblTestCode);
		
		JLabel lblTheTest = new JLabel("The test");
		lblTheTest.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\tests_32.png"));
		lblTheTest.setBounds(10, 114, 138, 41);
		contentPane.add(lblTheTest);
		
		JButton btnCancel = new JButton("Out");
		btnCancel.setBackground(SystemColor.inactiveCaptionBorder);
		btnCancel.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\cancel.png"));
		btnCancel.setBounds(587, 596, 101, 30);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnCancelListener();
			}
			
		});
		contentPane.add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(SystemColor.inactiveCaptionBorder);
		btnDelete.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\trash.png"));
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.setBounds(476, 596, 101, 30);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 //TODO Auto-generated method stub
				btnDeleteListener();
			}
			
		});
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 166, 678, 418);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setBackground(SystemColor.inactiveCaptionBorder);
		textPane.setEditable(false);
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textPane.setToolTipText("tap text to change");
		scrollPane.setViewportView(textPane);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\base_book.png"));
		lblSubject.setBounds(9, 70, 84, 33);
		contentPane.add(lblSubject);
		
		subComboBox = new JComboBox<Subject>(subModel);
		subComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		subComboBox.setBounds(97, 75, 306, 22);
		subComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subComboBoxListener();
			}
			
		});
		contentPane.add(subComboBox);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(SystemColor.inactiveCaptionBorder);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveListener();
			}
		});
		btnSave.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\memory_save_20.png"));
		btnSave.setBounds(250, 596, 101, 30);
		contentPane.add(btnSave);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(524, 70, 164, 85);
		contentPane.add(scrollPane_1);
		
		codeList = new JList<Test>(codeModel);
		codeList.setBackground(SystemColor.inactiveCaptionBorder);
		codeList.setToolTipText("Click to view\r\n");
		codeList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				codeListListener();
				
			}
			
		});
		scrollPane_1.setViewportView(codeList);
		codeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnSaveAnswer = new JButton("Answer");
		btnSaveAnswer.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\memory_save_20.png"));
		btnSaveAnswer.setBackground(SystemColor.inactiveCaptionBorder);
		btnSaveAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAnswerActionListener();
			}
		});
		btnSaveAnswer.setBounds(361, 596, 105, 30);
		contentPane.add(btnSaveAnswer);
	}
	
	public void saveAnswerActionListener() {
		if(!textPane.getText().equals("")) {
			if(control.InnerQuestionControl.getMultipleQuestions(iQs)==0) {
				JOptionPane.showMessageDialog(this, "No multiple-choice question","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}
			Test t = (Test)codeList.getSelectedValue();
	        java.sql.Date date = t.getDate();
			String s = "\n"+t.getTitle()+"\n\n"+"Test Code: "+t.getCode()+"\t"
					+"      Date: "+date.toString()+"\n";
			s += control.InnerQuestionControl.getTestAnswer(iQs);
			WriteTextFile.writeTextFile(s, "Save a Test file");
		}else {
			JOptionPane.showMessageDialog(this, "No test","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void prepareData() {
		subModel = new DefaultComboBoxModel<>();
		codeModel = new DefaultListModel<>();
		ArrayList<Subject> subList = SubjectDao.getSubjectList(userid);
		if(subList==null) {
			JOptionPane.showMessageDialog(this, "Error no connection","Error",JOptionPane.ERROR_MESSAGE);
		}else {
			subModel.addAll(subList);
		}
	}
	
	public void btnDeleteListener() {
		if(!textPane.getText().equals("")) {
			int x = JOptionPane.showConfirmDialog(this, "Do you realy want to delete the test","Notification",JOptionPane.OK_CANCEL_OPTION);
			if(x!=0) return;
			if(!TestDao.delete(((Test)codeList.getSelectedValue()).getId())) {
				JOptionPane.showMessageDialog(this, "No connection to data","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			ArrayList<Test> tList = TestDao.getTestList(((Subject)subComboBox.getSelectedItem()).getId());
			codeModel.removeAllElements();
			codeModel.addAll(tList);
			textPane.setText("");
			JOptionPane.showMessageDialog(this, "Delete the test successfully","Successfully",JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "No test choiced","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void subComboBoxListener() {
		if(subComboBox.getSelectedIndex()==-1) return;
		ArrayList<Test> tList = TestDao.getTestList(((Subject)subComboBox.getSelectedItem()).getId());
		if(tList==null) {
			JOptionPane.showMessageDialog(this, "Error no connection","Error",JOptionPane.ERROR_MESSAGE);
		}else {
			codeModel.removeAllElements();
			codeModel.addAll(tList);
			textPane.setText("");
		}
	}
	
	public void btnSaveListener() {
		if(!textPane.getText().equals("")) {
			String content = textPane.getText();
			String title = "Save a Test file";
			WriteTextFile.writeTextFile(content, title);
		}else {
			JOptionPane.showMessageDialog(this, "No test","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void codeListListener() {
		if(codeList.getSelectedIndex()==-1) return;
		Test test = (Test)codeList.getSelectedValue();
		iQs = InnerQuestionDao.getInnerQuestionList(test.getId());
		if(iQs==null) {
			JOptionPane.showMessageDialog(this, "No connection to data base","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String s = "\n"+test.getTitle()+"\n\n"+"Test Code: "+test.getCode()+"\t"
				+"      Date: "+test.getDate().toString()+"\n";
		s += control.InnerQuestionControl.getTestUnit(iQs);
		textPane.setText(s);
	}

	public void btnCancelListener() {
		int x = JOptionPane.showConfirmDialog(this, "Do you realy want to exit","Notification",JOptionPane.OK_CANCEL_OPTION);
		if(x!=0) return;
		thisFrame.setVisible(false);
		menu.setVisible(true);
	}
}
