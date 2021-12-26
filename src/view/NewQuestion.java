package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connection.ChapterDao;
import connection.QuestionDao;
import connection.SubjectDao;
import model.Answer;
import model.Chapter;
import model.Question;
import model.Subject;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import java.awt.SystemColor;

public class NewQuestion extends JFrame {
	private Menu menu;
	private int userid;
	private JPanel contentPane;
	
	private JTextPane questionContentTextPane;
	private JTextField trueAnswerTextField;
	private JTextField falseAnswerTextField1;
	private JTextField falseAnswerTextField2;
	private JTextField falseAnswerTextField3;

	private DefaultComboBoxModel<Subject> subModel;
	private DefaultComboBoxModel<Chapter> cModel;

	JComboBox<Chapter> chapterNameComboBox;
	JComboBox<Subject> subjectNameComboBox;
	JComboBox<String> questionTypeComboBox;
	JComboBox<String> questionLevelComboBox;
	
	/**
	 * Create the frame.
	 */
	public NewQuestion(Menu menu,int userid) {
		this.menu = menu;
		this.userid = userid;
		setModel();
		setDisplay();
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewQuestion = new JLabel("Add new question");
		lblAddNewQuestion.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAddNewQuestion.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\hoicham.png"));
		lblAddNewQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewQuestion.setBounds(0, 0, 637, 56);
		lblAddNewQuestion.setForeground(Color.DARK_GRAY);
		contentPane.add(lblAddNewQuestion);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\base_book.png"));
		lblSubject.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSubject.setBounds(10, 57, 90, 38);
		contentPane.add(lblSubject);
		
		subjectNameComboBox = new JComboBox<Subject>(subModel);
		subjectNameComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		subjectNameComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		subjectNameComboBox.setBounds(110, 66, 253, 22);
		subjectNameComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				subjectNameComboBoxListener();
			}
			
		});
		contentPane.add(subjectNameComboBox);
		
		JLabel lblChapter = new JLabel("Chapter");
		lblChapter.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\chapter.png"));
		lblChapter.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblChapter.setBounds(10, 100, 90, 38);
		contentPane.add(lblChapter);
		
		chapterNameComboBox = new JComboBox<Chapter>(cModel);
		chapterNameComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		chapterNameComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		chapterNameComboBox.setBounds(110, 109, 253, 22);
		contentPane.add(chapterNameComboBox);
		
		JLabel lblQuestionType = new JLabel("Question type");
		lblQuestionType.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblQuestionType.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\option.png"));
		lblQuestionType.setBounds(388, 57, 122, 38);
		contentPane.add(lblQuestionType);
		
		questionTypeComboBox = new JComboBox<String>();
		questionTypeComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		questionTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"foreword", "multiple-choice"}));
		questionTypeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		questionTypeComboBox.setMaximumRowCount(2);
		questionTypeComboBox.setBounds(518, 67, 109, 22);
		questionTypeComboBox.setSelectedIndex(-1);
		questionTypeComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				typeComboBoxChanged();
			}
			
		});
		contentPane.add(questionTypeComboBox);
		
		JLabel lblQustionLevel = new JLabel("Question level");
		lblQustionLevel.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\star_level.png"));
		lblQustionLevel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblQustionLevel.setBounds(388, 100, 122, 38);
		contentPane.add(lblQustionLevel);
		
		questionLevelComboBox = new JComboBox<String>();
		questionLevelComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		questionLevelComboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		questionLevelComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"easy", "normal", "hard"}));
		questionLevelComboBox.setMaximumRowCount(3);
		questionLevelComboBox.setBounds(518, 109, 109, 22);
		questionLevelComboBox.setSelectedIndex(-1);
		contentPane.add(questionLevelComboBox);
		
		JLabel lblQuestionContent = new JLabel("Question content");
		lblQuestionContent.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\pen_page.png"));
		lblQuestionContent.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblQuestionContent.setBounds(10, 135, 139, 38);
		contentPane.add(lblQuestionContent);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 617, 178);
		contentPane.add(scrollPane);
		
		questionContentTextPane = new JTextPane();
		questionContentTextPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		questionContentTextPane.setBackground(SystemColor.inactiveCaptionBorder);
		scrollPane.setViewportView(questionContentTextPane);
		
		trueAnswerTextField = new JTextField();
		trueAnswerTextField.setBackground(SystemColor.inactiveCaptionBorder);
		trueAnswerTextField.setToolTipText("true answer");
		trueAnswerTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		trueAnswerTextField.setBounds(105, 371, 200, 22);
		trueAnswerTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(trueAnswerTextField.getText().length()==0) {
					trueAnswerTextField.setBackground(Color.RED);
				}else {
					trueAnswerTextField.setBackground(Color.WHITE);
				}
			}
		});
		
		contentPane.add(trueAnswerTextField);
		trueAnswerTextField.setColumns(10);
		
		JLabel lblTrueAnswer = new JLabel("True answer");
		lblTrueAnswer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTrueAnswer.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\true_answer.png"));
		lblTrueAnswer.setBounds(10, 365, 90, 32);
		contentPane.add(lblTrueAnswer);
		
		JLabel lblFalseAnswer = new JLabel("False answer");
		lblFalseAnswer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFalseAnswer.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\false.png"));
		lblFalseAnswer.setBounds(320, 365, 92, 32);
		contentPane.add(lblFalseAnswer);
		
		falseAnswerTextField1 = new JTextField();
		falseAnswerTextField1.setBackground(SystemColor.inactiveCaptionBorder);
		falseAnswerTextField1.setToolTipText("false answer 1");
		falseAnswerTextField1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		falseAnswerTextField1.setBounds(426, 370, 200, 22);
		falseAnswerTextField1.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stu
				if(falseAnswerTextField1.getText().length()==0) {
					falseAnswerTextField1.setBackground(Color.RED);
				}else {
					falseAnswerTextField1.setBackground(Color.WHITE);
				}
			}
			
		});
		contentPane.add(falseAnswerTextField1);
		falseAnswerTextField1.setColumns(10);
		
		JLabel lblFalseAnswer_1 = new JLabel("False answer");
		lblFalseAnswer_1.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\false.png"));
		lblFalseAnswer_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFalseAnswer_1.setBounds(320, 404, 96, 32);
		contentPane.add(lblFalseAnswer_1);
		
		JLabel lblFalseAnswer_2 = new JLabel("False answer");
		lblFalseAnswer_2.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\false.png"));
		lblFalseAnswer_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFalseAnswer_2.setBounds(10, 404, 92, 32);
		contentPane.add(lblFalseAnswer_2);
		
		falseAnswerTextField2 = new JTextField();
		falseAnswerTextField2.setBackground(SystemColor.inactiveCaptionBorder);
		falseAnswerTextField2.setToolTipText("false answer 2");
		falseAnswerTextField2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		falseAnswerTextField2.setColumns(10);
		falseAnswerTextField2.setBounds(105, 410, 200, 22);
		falseAnswerTextField2.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stu
				if(falseAnswerTextField2.getText().length()==0) {
					falseAnswerTextField2.setBackground(Color.YELLOW);
				}else {
					falseAnswerTextField2.setBackground(Color.WHITE);
				}
			}
			
		});
		contentPane.add(falseAnswerTextField2);
		
		falseAnswerTextField3 = new JTextField();
		falseAnswerTextField3.setBackground(SystemColor.inactiveCaptionBorder);
		falseAnswerTextField3.setToolTipText("false answer 3");
		falseAnswerTextField3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		falseAnswerTextField3.setColumns(10);
		falseAnswerTextField3.setBounds(427, 410, 200, 22);
		falseAnswerTextField3.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stu
				if(falseAnswerTextField3.getText().length()==0) {
					falseAnswerTextField3.setBackground(Color.YELLOW);
				}else {
					falseAnswerTextField3.setBackground(Color.WHITE);
				}
			}
			
		});
		contentPane.add(falseAnswerTextField3);
		
		JButton doneButton = new JButton("Add");
		doneButton.setBackground(SystemColor.inactiveCaptionBorder);
		doneButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\addtemplate_20.png"));
		doneButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		doneButton.setBounds(426, 448, 95, 32);
		doneButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doneButtonListener();
			}
		});
		contentPane.add(doneButton);
		
		JLabel note = new JLabel("Note: input at least two choices by mutiple-choice");
		note.setHorizontalAlignment(SwingConstants.CENTER);
		note.setForeground(Color.RED);
		note.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		note.setBounds(10, 453, 400, 22);
		contentPane.add(note);
		
		JButton cancelButton = new JButton("Out");
		cancelButton.setBackground(SystemColor.inactiveCaptionBorder);
		cancelButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\cancel.png"));
		cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cancelButton.setBounds(532, 448, 95, 32);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelButtonListener();
			}
			
		});
		contentPane.add(cancelButton);
		
		prepareDataBase();
		
	}
	
	public void setDisplay() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\project java\\JavaJDBC_Postgresql\\images\\question_24.png"));
		setFont(new Font("Times New Roman", Font.PLAIN, 13));
		setTitle("Add new question");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(653, 535);
		setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	public void setModel() {
		subModel = new DefaultComboBoxModel<Subject>();
		cModel = new DefaultComboBoxModel<Chapter>();
	}
	
	public void prepareDataBase() {
		ArrayList<Subject> subList = SubjectDao.getSubjectList(userid);
		if(subList==null) {
			JOptionPane.showMessageDialog(this,"No connect to database","Error connect",JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			subModel.addAll(subList);
		}
	}
	
	public void subjectNameComboBoxListener() {
		int subid = ((Subject) subjectNameComboBox.getSelectedItem()).getId();
		ArrayList<Chapter> chapList = ChapterDao.getChapterList(subid);
		if(chapList==null) {
			JOptionPane.showMessageDialog(null, "No connect to database", "Error connect", JOptionPane.ERROR_MESSAGE);
			cModel.removeAllElements();
			return;
		}else {
			cModel.removeAllElements();
			cModel.addAll(chapList);
		}
	}
	
	public boolean checkStruct() {
		if(subjectNameComboBox.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "You don't choose the subject","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(chapterNameComboBox.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "You don't choose the chapter","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(questionLevelComboBox.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "You don't choose the level","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean checkContent() {
		int type = questionTypeComboBox.getSelectedIndex();
		String content = questionContentTextPane.getText();
		if(type==-1) {
			JOptionPane.showMessageDialog(this, "You don't choose the type","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(type==0) {
			if(content.equals("")) {
				JOptionPane.showMessageDialog(this, "You don't input the question content","Error",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}else {
			if(content.equals("")) {
				JOptionPane.showMessageDialog(this, "You don't input the content","Error",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(trueAnswerTextField.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "You don't input the true answer","Error",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(falseAnswerTextField1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "You don't input the fisrt false answer","Error",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	public void doneButtonListener() {
		if(!checkStruct()) return;
		if(!checkContent()) return;
		Question newq = new Question(questionContentTextPane.getText(),questionTypeComboBox.getSelectedIndex(),questionLevelComboBox.getSelectedIndex());
		if(questionTypeComboBox.getSelectedIndex()==0) {
			// -1 no connect
			//  1 did
			if(QuestionDao.addForewordQuestion(newq,((Chapter)chapterNameComboBox.getSelectedItem()).getId())==-1 ){
				JOptionPane.showMessageDialog(this, "No connect to database","Error connect",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				questionContentTextPane.setText("");
				JOptionPane.showMessageDialog(this, "Insert question successfully","Notification",JOptionPane.INFORMATION_MESSAGE);
			}
		}else {
			// -1: no connect
			//  1: insert question
			//  2: insert answer and question
			Answer newa = new Answer(trueAnswerTextField.getText(), falseAnswerTextField1.getText(),falseAnswerTextField2.getText(),falseAnswerTextField3.getText());
			switch(QuestionDao.addMultiple_ChooseQuestion(newq, ((Chapter)chapterNameComboBox.getSelectedItem()).getId(), newa)) {
			case -1: 
				JOptionPane.showMessageDialog(this, "No connect to database","Error connect database",JOptionPane.ERROR_MESSAGE);
				break;
			case  1:
				JOptionPane.showMessageDialog(this, "Only insert multiple-choose question no answers","Notification",JOptionPane.INFORMATION_MESSAGE);
				break;
			case  2:
				JOptionPane.showMessageDialog(this, "Insert multiple-choose question success","Notification",JOptionPane.INFORMATION_MESSAGE);
				questionContentTextPane.setText("");
				trueAnswerTextField.setText("");
				falseAnswerTextField1.setText("");
				falseAnswerTextField2.setText("");
				falseAnswerTextField3.setText("");
				typeComboBoxChanged();
				break;
			}
		}
	}
	
	public void cancelButtonListener() {
		int x = JOptionPane.showConfirmDialog(this, "Do you really want to exit?\n"
				+"Can be loss the infomation", "Notification", JOptionPane.OK_CANCEL_OPTION);
		if(x!=0) return;
		setVisible(false);
		menu.setVisible(true);
	}
	
	public void typeComboBoxChanged() {
		if(questionTypeComboBox.getSelectedIndex()==-1) return;
		int x = questionTypeComboBox.getSelectedIndex();
		if(x==0) {
			trueAnswerTextField.setEditable(false);
			trueAnswerTextField.setText("");
			trueAnswerTextField.setBackground(Color.DARK_GRAY);
			falseAnswerTextField1.setEditable(false);
			falseAnswerTextField1.setText("");
			falseAnswerTextField1.setBackground(Color.DARK_GRAY);
			falseAnswerTextField2.setEditable(false);
			falseAnswerTextField2.setText("");
			falseAnswerTextField2.setBackground(Color.DARK_GRAY);
			falseAnswerTextField3.setEditable(false);
			falseAnswerTextField3.setText("");
			falseAnswerTextField3.setBackground(Color.DARK_GRAY);
		}else {
			trueAnswerTextField.setEditable(true);
			trueAnswerTextField.setBackground(Color.RED);
			falseAnswerTextField1.setEditable(true);
			falseAnswerTextField1.setBackground(Color.RED);
			falseAnswerTextField2.setEditable(true);
			falseAnswerTextField2.setBackground(Color.YELLOW);
			falseAnswerTextField3.setEditable(true);
			falseAnswerTextField3.setBackground(Color.YELLOW);
		}
	}
}
