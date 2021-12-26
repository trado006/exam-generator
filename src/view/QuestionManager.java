package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import connection.AnswerDao;
import connection.ChapterDao;
import connection.QuestionDao;
import connection.SubjectDao;
import model.Answer;
import model.Chapter;
import model.Question;
import model.Subject;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class QuestionManager extends JFrame {
	Menu menu;
	int userid;
	
	private JPanel contentPane;
	
	JTextPane contentTextPane;
	
	JComboBox<Subject> subjectBox;
	JComboBox<Chapter> chapterBox;
	
	DefaultComboBoxModel<Subject> subModel;
	DefaultComboBoxModel<Chapter> chapModel;
	
	ArrayList<Question> qList;
	ArrayList<Answer> aList;
	
	int row;
	int column;
	private JTable table;
	DefaultTableModel questionsModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionManager frame = new QuestionManager(null,0);
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
	public QuestionManager(Menu menu,int userid) {
		this.menu = menu;
		this.userid = userid;
		row = -1; column = -1; 
		prepareData();
		setTitle("Change question");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(792,600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\base_book.png"));
		
		subjectBox = new JComboBox<Subject>(subModel);
		subjectBox.setBackground(SystemColor.inactiveCaptionBorder);
		subjectBox.setSelectedIndex(-1);
		subjectBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subjectComboBoxListener();
			}
		});
		
		JLabel lblChapter = new JLabel("Chapter");
		lblChapter.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\chapter.png"));
		
		chapterBox = new JComboBox(chapModel);
		chapterBox.setBackground(SystemColor.inactiveCaptionBorder);
		chapterBox.setSelectedIndex(-1);
		chapterBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				chapterComboBoxListener();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblQuestionlist = new JLabel("QuestionList");
		lblQuestionlist.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\item_24.png"));
		
		JButton btnSave = new JButton("Update");
		btnSave.setBackground(SystemColor.inactiveCaptionBorder);
		btnSave.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\undo_20.png"));
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btSaveListener();
			}
		});
		
		JButton btnOut = new JButton("Out");
		btnOut.setBackground(SystemColor.inactiveCaptionBorder);
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btOutListener();
			}
		});
		btnOut.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\cancel.png"));
		
		JScrollPane questionTable = new JScrollPane();
		
		JButton btnRemove = new JButton("sub question");
		btnRemove.setBackground(SystemColor.inactiveCaptionBorder);
		btnRemove.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\delete.png"));
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btDeleteListener();
			}
		});
		
		JLabel lblQuestionManage = new JLabel("Question manage");
		lblQuestionManage.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\hoicham.png"));
		lblQuestionManage.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionManage.setForeground(Color.DARK_GRAY);
		lblQuestionManage.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnOut))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(questionTable, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblQuestionlist, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
									.addComponent(btnRemove)
									.addGap(4)
									.addComponent(btnSave))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblChapter, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chapterBox, 0, 625, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblSubject, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(subjectBox, 0, 625, Short.MAX_VALUE)))))
					.addGap(20))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(88)
					.addComponent(lblQuestionManage, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
					.addGap(45))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(lblQuestionManage, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(subjectBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubject))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChapter, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(chapterBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuestionlist, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave)
						.addComponent(btnRemove))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(questionTable, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
					.addGap(9)
					.addComponent(btnOut))
		);
		
		contentTextPane = new JTextPane();
		contentTextPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentTextPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentTextPane.setEditable(false);
		scrollPane.setViewportView(contentTextPane);
		
		table = new JTable();
		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		questionTable.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				listSelectionListener();
			}
		});
	}
	
	public void listSelectionListener() {
		int cln = table.getSelectedColumn();
		int rw = table.getSelectedRow();
		table.clearSelection();
		if(cln==-1||rw==-1) return;
		if(qList.get(rw).getType()==0&&cln>2) {
			this.column = -1;
			this.row = -1;
			contentTextPane.setText("");
			contentTextPane.setEditable(false);
			JOptionPane.showMessageDialog(this, "Question is multiple-choice");
			return;
		}
		if(cln==2) {
			this.column = -1;
			this.row = -1;
			contentTextPane.setText("");
			contentTextPane.setEditable(false);
			JOptionPane.showMessageDialog(this, "Don't change the type of queston");
			return;
		}
		this.column = cln;
		this.row = rw;
		String content = (String)questionsModel.getValueAt(row, column);
		contentTextPane.setText(content);
		contentTextPane.setEditable(true);
	}
	
	public void setModel(String[][] questionTableData) {
		questionsModel = new DefaultTableModel(questionTableData,
				new String[] {
					"Content","Level", "Choice", "True answer", "False answer 1", "False answer 2", "False answer 3"
				}
			) {
				Class[] columnTypes = new Class[] {
						String.class,String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
						false,false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			table.setModel(questionsModel);
			table.getColumnModel().getColumn(0).setPreferredWidth(200);
			table.getColumnModel().getColumn(0).setMinWidth(200);
			table.getColumnModel().getColumn(0).setMaxWidth(20000000);
			table.getColumnModel().getColumn(1).setPreferredWidth(44);
			table.getColumnModel().getColumn(1).setMinWidth(22);
			table.getColumnModel().getColumn(1).setMaxWidth(44);
			table.getColumnModel().getColumn(2).setPreferredWidth(44);
			table.getColumnModel().getColumn(2).setMinWidth(22);
			table.getColumnModel().getColumn(2).setMaxWidth(44);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setMinWidth(75);
			table.getColumnModel().getColumn(3).setMaxWidth(150);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setMinWidth(75);
			table.getColumnModel().getColumn(4).setMaxWidth(150);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setMinWidth(75);
			table.getColumnModel().getColumn(5).setMaxWidth(150);
			table.getColumnModel().getColumn(6).setPreferredWidth(100);
			table.getColumnModel().getColumn(6).setMinWidth(75);
			table.getColumnModel().getColumn(6).setMaxWidth(150);
	}

	public void prepareData() {
		subModel = new DefaultComboBoxModel<>();
		chapModel = new DefaultComboBoxModel<>();
		ArrayList<Subject> subList = SubjectDao.getSubjectList(userid);
		if(subList!=null) {
			subModel.addAll(subList);
		}else {
			JOptionPane.showMessageDialog(this, "No connect data","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void chapterComboBoxListener() {
		if(chapterBox.getSelectedIndex()!=-1) {
			int cid = ((Chapter)chapterBox.getSelectedItem()).getId();
			qList = QuestionDao.getQuestionList(cid);
			if(qList!=null) {
				aList = AnswerDao.getAnswerList(qList);
				if(aList!=null) {
					String[][] stringTable = control.QuestionControl.toStringTable(qList, aList);
					setModel(stringTable);
					contentTextPane.setText("");
					contentTextPane.setEditable(false);
					this.row = -1;
					this.column = -1;
					return;
				}
				JOptionPane.showMessageDialog(this, "No connection","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(this, "No connection","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void subjectComboBoxListener() {
		if(subjectBox.getSelectedIndex()!=-1) {
			int subid = ((Subject)subjectBox.getSelectedItem()).getId();
			ArrayList<Chapter> chapList = ChapterDao.getChapterList(subid);
			if(chapList!=null) {
				chapModel.removeAllElements();
				chapModel.addAll(chapList);
				contentTextPane.setText("");
				contentTextPane.setEditable(false);
				setModel(null);
				this.row = -1;
				this.column = -1;
			}else {
				JOptionPane.showMessageDialog(this, "No conection","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	public void btSaveListener() {
		if(row!=-1&&column!=-1) {
			int index = JOptionPane.showConfirmDialog(this, "The data of this question will change"
					+"\n\nDo you want continue?","Warning",JOptionPane.OK_CANCEL_OPTION);
			if(index!=0) return;
			Question q = qList.get(row);
			Answer a = aList.get(row);
			switch(column) {
			case 0:
				if(contentTextPane.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "The content don't null");
					deleteChoose();
					return;
				}else {
					q.setContent(contentTextPane.getText());
					updateQuestion(q,a);
					return;
				}
			case 1:
				int level = control.QuestionControl.getLevelInt(contentTextPane.getText());
				if(level!=-1) {
					q.setLevel(level);
					updateQuestion(q,a);
					return;
				}else {
					JOptionPane.showMessageDialog(this, "Input don't corecly"
							+"\nTry with 'easy','normal','hard'");
					return;
				}
			case 3:
				if(a.getQid()==0) return;
				String trueAnswer = contentTextPane.getText();
				if(!trueAnswer.equals("")) {
					a.setChoose(trueAnswer);
					if(!AnswerDao.updateAnswer(a)) {
						JOptionPane.showMessageDialog(this, "No connection");
						return;
					}
					updateQuestion(q, a);
					return;
				}else {
					JOptionPane.showMessageDialog(this, "The true answer don't null");
					deleteChoose();
					return;
				}
			case 4:
				if(a.getQid()==0) return;
				String falseAnswer1 = contentTextPane.getText();
				if(!falseAnswer1.equals("")) {
					a.setAnswer1(falseAnswer1);
					if(!AnswerDao.updateAnswer(a)) {
						JOptionPane.showMessageDialog(this, "No connection");
						return;
					}
					updateQuestion(q, a);
					return;
				}else {
					JOptionPane.showMessageDialog(this, "The false answer 1 don't null");
					deleteChoose();
					return;
				}
			case 5:
				if(a.getQid()==0) return;
				String falseAnswer2 = contentTextPane.getText();
				a.setAnswer2(falseAnswer2);
				if(!AnswerDao.updateAnswer(a)) {
					JOptionPane.showMessageDialog(this, "No connection");
					return;
				}
				updateQuestion(q, a);
				return;
			case 6:
				if(a.getQid()==0) return;
				String falseAnswer3 = contentTextPane.getText();
				a.setAnswer3(falseAnswer3);
				if(!AnswerDao.updateAnswer(a)) {
					JOptionPane.showMessageDialog(this, "No connection");
					return;
				}
				updateQuestion(q, a);
				return;
			}
		}else {
			JOptionPane.showMessageDialog(this, "No question choiced");
			return;
		}
		
	}
	
	public void updateQuestion(Question q,Answer a) {
		if(!QuestionDao.update(q)) {
			JOptionPane.showMessageDialog(this, "No connection to data base");
			return;
		}else {
			questionsModel.removeRow(row);
			questionsModel.insertRow(row, control.QuestionControl.toStrings(q, a));
			deleteChoose();
			JOptionPane.showMessageDialog(this, "Save ok");
			return;
		}
	}
	
	public void deleteChoose() {
		contentTextPane.setText("");
		contentTextPane.setEditable(false);
		row = -1; column = -1;
	}
	
	public void btDeleteListener() {
		if(column!=-1&&row!=-1) {
			int x = JOptionPane.showConfirmDialog(this, "Do you realy want to delete this question?","Warning",JOptionPane.OK_CANCEL_OPTION);
			if(x!=0) return;
			if(column!=0) {
				JOptionPane.showMessageDialog(this, "To delele question \nLet choose content column of question");
				deleteChoose();
				return;
			}
			Question q = qList.get(row);
			if(QuestionDao.deleteQuestion(q)==-1) {
				JOptionPane.showMessageDialog(this, "No connection\nDon't delete this question");
				return;
			}else {
				qList.remove(row); aList.remove(row);
				questionsModel.removeRow(row);
				deleteChoose();
				JOptionPane.showMessageDialog(this, "Delete this question successfully");
			}
		}else {
			JOptionPane.showMessageDialog(this, "Please choice the question");
		}
	}
	
	public void btOutListener() {
		int x = JOptionPane.showConfirmDialog(this, "Can lost the data","Warning",JOptionPane.OK_CANCEL_OPTION);
		if(x!=0) return;
		this.setVisible(false);
		menu.setVisible(true);
	}
}
