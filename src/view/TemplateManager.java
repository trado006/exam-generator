package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import connection.FullQuestionDao;
import connection.StructDao;
import connection.SubjectDao;
import connection.TemplateDao;
import control.StructControl;
import model.Struct;
import model.Subject;
import model.Template;

public class TemplateManager extends JFrame {
	private Menu menu;
	private JPanel contentPane;
	
	JLabel totalScore;
	JComboBox<Subject> subjectComboBox;
	JComboBox<Template> titleComboBox;
	JComboBox<Object> typeComboBox;
	JRadioButton easyFirstRadioButton;
	
	
	int userid;
	private JSpinner qCountSpinner;
	private JTextField scoreTextField;
	
	int row;
	private JTable chapterTable;
	private DefaultTableModel chapTableModel;
	
	private DefaultComboBoxModel<Subject> subModel;
	private DefaultComboBoxModel<Template> tModel;
	private ArrayList<Struct> strList;
	
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemplateManager templateManager = new TemplateManager(null,0);
					templateManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	/**
	 * Create the panel.
	 */
	public TemplateManager(Menu menu,int userid) {
		this.menu = menu;
		this.userid = userid;
		setDisplay();
		prepareModel();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCreateNewTest = new JLabel("Manage test template");
		lblCreateNewTest.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\Edit.png"));
		lblCreateNewTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateNewTest.setForeground(Color.DARK_GRAY);
		lblCreateNewTest.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\base_book.png"));
		lblSubject.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\chapter.png"));
		
		JLabel lblTestType = new JLabel("Test type:");
		
		JLabel lblSortBy = new JLabel("+ Sort by:");
		
		subjectComboBox = new JComboBox<Subject>(subModel);
		subjectComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		subjectComboBox.setSelectedIndex(-1);
		subjectComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subComBoBoxListener();
			}
		});
		
		typeComboBox = new JComboBox<Object>();
		typeComboBox.setModel(
				new DefaultComboBoxModel<Object>(
						new String[] {"Foreword", "Multiple-choice", "All"}));
		typeComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		typeComboBox.setSelectedIndex(-1);
		typeComboBox.setEnabled(false);
		
		easyFirstRadioButton = new JRadioButton("Easy first hard last");
		easyFirstRadioButton.setEnabled(false);
		easyFirstRadioButton.setBackground(Color.LIGHT_GRAY);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Template chapter list", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		
		titleComboBox = new JComboBox<Template>(tModel);
		titleComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		titleComboBox.setSelectedIndex(-1);
		titleComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				temComboBoxListener();
			}
		});
		JButton outButton = new JButton("Out");
		outButton.setBackground(SystemColor.inactiveCaptionBorder);
		outButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\cancel.png"));
		outButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				outButtonListener();
			}
		});
		JButton resetButton = new JButton("Reset");
		resetButton.setBackground(SystemColor.inactiveCaptionBorder);
		resetButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\update_20.png"));
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resetButtonLintener();
			}
		});
		
		JButton nextButton = new JButton("Next");
		nextButton.setBackground(SystemColor.inactiveCaptionBorder);
		nextButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\out_20.png"));
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nextButtonListener();
			}
		});
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(SystemColor.inactiveCaptionBorder);
		deleteButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\delete.png"));
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteButtonListener();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblCreateNewTest, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblSortBy, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTestType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblSubject, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(subjectComboBox, 0, 561, Short.MAX_VALUE)
								.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addComponent(titleComboBox, 0, 561, Short.MAX_VALUE))
							.addContainerGap())
						.addComponent(easyFirstRadioButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 654, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(318, Short.MAX_VALUE)
					.addComponent(nextButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(resetButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(deleteButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(outButton)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblCreateNewTest, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubject, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(subjectComboBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(titleComboBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTestType, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSortBy, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(easyFirstRadioButton))
					.addGap(10)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(outButton)
						.addComponent(deleteButton)
						.addComponent(resetButton)
						.addComponent(nextButton)))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblQuestionCount = new JLabel("Question count:");
		
		qCountSpinner = new JSpinner();
		qCountSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		qCountSpinner.setBackground(SystemColor.inactiveCaptionBorder);
		qCountSpinner.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblTotalScore = new JLabel("Total score:");
		
		scoreTextField = new JTextField();
		scoreTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreTextField.setToolTipText("Floating point number");
		scoreTextField.setBackground(SystemColor.inactiveCaptionBorder);
		scoreTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
		scoreTextField.setColumns(10);
		
		JButton updateButton = new JButton("Update");
		updateButton.setBackground(SystemColor.inactiveCaptionBorder);
		updateButton.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\undo_20.png"));
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateButtonListener();
			}
		});
		
		totalScore = new JLabel("");
		totalScore.setHorizontalAlignment(SwingConstants.CENTER);
		totalScore.setForeground(Color.RED);
		totalScore.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addComponent(totalScore, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
							.addGap(36))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addComponent(lblQuestionCount)
									.addGap(1)
									.addComponent(qCountSpinner, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotalScore, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addGap(1)
									.addComponent(scoreTextField, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
									.addComponent(updateButton)))
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(updateButton)
						.addComponent(lblTotalScore, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(qCountSpinner, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuestionCount, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(scoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(totalScore, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		
		chapterTable = new JTable();
		chapterTable.setBackground(SystemColor.inactiveCaptionBorder);
		chapterTable.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(chapterTable);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
		ListSelectionModel listSelectionModel = chapterTable.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				listSelectionListener();
			}
		});
		
		loadDataBase();
	}
	
	public void setDisplay() {
		setTitle("Change question");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(700,700);
		setLocationRelativeTo(null);
	}
	
	
	public void prepareModel() {
		row = -1;
		subModel = new DefaultComboBoxModel<Subject>();
		tModel = new DefaultComboBoxModel<Template>();
	}

	
	public void loadDataBase() {
		ArrayList<Subject> subList = SubjectDao.getSubjectList(userid);
		if(subList==null) {
			JOptionPane.showMessageDialog(this,"No connect to database","No connect",JOptionPane.ERROR_MESSAGE);
		}else {
			subModel.addAll(subList);
		}
	}
	
	public void subComBoBoxListener() {
		if(subjectComboBox.getSelectedIndex()==-1) return;
		else {
			int subid = ((Subject)subjectComboBox.getSelectedItem()).getId();
			ArrayList<Template> tList = TemplateDao.getAllTemplate(subid);
			if(tList==null) {
				JOptionPane.showMessageDialog(this, "No connect data base","Error",JOptionPane.ERROR_MESSAGE);
				tModel.removeAllElements();
			}else {
				tModel.removeAllElements();
				tModel.addAll(tList);
			}
			typeComboBox.setSelectedIndex(-1);
			easyFirstRadioButton.setSelected(false);
			qCountSpinner.setValue(0);
			scoreTextField.setText("");
			totalScore.setText("");
			setModel(null);
			this.row = -1;
		}
	}
	
	public void temComboBoxListener() {
		if(titleComboBox.getSelectedIndex()!=-1) {
			Template t = (Template)titleComboBox.getSelectedItem();
			typeComboBox.setSelectedIndex(t.getType());
			easyFirstRadioButton.setSelected((t.getEasyfirst()==0)?false:true);
			strList = StructDao.getAllStruct(t.getId());
			if(strList==null) {
				JOptionPane.showMessageDialog(this, "No connection to data base","Error",JOptionPane.ERROR_MESSAGE);
				setModel(null);
				return;
			}else {
				String[][] stringTable = StructControl.toStringTable(strList);
				setModel(stringTable);
				qCountSpinner.setValue(0);
				scoreTextField.setText("");
				setTotalScore();
				this.row = -1;
			}
		}
	}
	// note kiem tra lai
	public void listSelectionListener() {
		int rw = chapterTable.getSelectedRow();
		//chapterTable.clearSelection();
		if(rw==-1) return;
		this.row = rw;
		// "Name","level", "choice", "Count", "Score"
		qCountSpinner.setValue(Integer.parseInt((String)chapTableModel.getValueAt(row, 3)));
		scoreTextField.setText((String)chapTableModel.getValueAt(row, 4));
	}
	
	
	public void updateButtonListener() {
		if(row!=-1) {
			int index = JOptionPane.showConfirmDialog(this, "The data of item will change"
					+"\n\nDo you want continue?","Warning",JOptionPane.OK_CANCEL_OPTION);
			if(index==2) return;
			int count = (int)qCountSpinner.getValue();
			float score = 0;
			try {
			score = Float.parseFloat(scoreTextField.getText());
			if(score>=0) {
				if(score>0&&count==0) {
					JOptionPane.showMessageDialog(this, "Error with score>0 but count = 0","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Struct str = strList.get(row);
				str.setCount(count);
				str.setTotalScore(score);
				if(StructDao.update(str)) {
					String[] rowString = StructControl.toStringRow(str);
					chapTableModel.removeRow(row);
					chapTableModel.insertRow(row, rowString);
					qCountSpinner.setValue(0);
					scoreTextField.setText("");
					setTotalScore();
					row = -1;
					JOptionPane.showMessageDialog(this, "Update this item successfully","Successfully",JOptionPane.INFORMATION_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(this, "You input the score <0","Error",JOptionPane.ERROR_MESSAGE);
			}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, "You input the score not correct\nLet try input '1.2'","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "Please choose the item to update");
		}
	}
	
	public void nextButtonListener() {
		if(strList==null) {
			JOptionPane.showMessageDialog(this, "Have nothing");
			return;
		}
		float totalCount = 0;
		for(int i=0;i<strList.size();i++) {
			Struct str = strList.get(i);
			totalCount += str.getCount();
		}
		if(totalCount==0) {
			JOptionPane.showMessageDialog(this, "The test has no question"+
					"\nPlease change and try again","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(FullQuestionDao.CheckListAvailable(strList)) {
			
		}else {
			JOptionPane.showMessageDialog(this, "The data base has no enough question"+
					"\nPlease change and try again","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int subid = ((Subject)subjectComboBox.getSelectedItem()).getId();
		int temid = ((Template)titleComboBox.getSelectedItem()).getId();
		NewTest newTest = new NewTest(menu, userid);
		setVisible(false);
		newTest.setVisible(true);
		newTest.next(subid, temid);
	}
	
	public void resetButtonLintener() {
		if(row!=-1) {
			int x = JOptionPane.showConfirmDialog(this, "Do you want to reset item","Notification",JOptionPane.OK_CANCEL_OPTION);
			if(x!=0) return;
			Struct str = strList.get(row);
			str.setCount(0);
			str.setTotalScore(0);
			if(StructDao.update(str)) {
				qCountSpinner.setValue(0);
				scoreTextField.setText("");
				chapTableModel.removeRow(row);
				chapTableModel.insertRow(row, StructControl.toStringRow(str));
				setTotalScore();
				JOptionPane.showMessageDialog(this, "Reset item successfully");
				row = -1;
			}else {
				JOptionPane.showMessageDialog(this, "No connect to data base","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "No item choiced");
		}
	}
	
	public void deleteButtonListener() {
		if(titleComboBox.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice the test to delete");
			return;
		}
		int x = JOptionPane.showConfirmDialog(this, "Do you want to delete the test","Notification",JOptionPane.OK_CANCEL_OPTION);
		if(x!=0) return;
		if(TemplateDao.deleteTemplate(((Template)titleComboBox.getSelectedItem()).getId())==1) {
			int index = titleComboBox.getSelectedIndex();
			tModel.removeElementAt(index);
			titleComboBox.setSelectedIndex(-1);
			typeComboBox.setSelectedIndex(-1);
			easyFirstRadioButton.setSelected(false);
			qCountSpinner.setValue(0);
			scoreTextField.setText("");
			totalScore.setText("");
			setModel(null);
			this.row = -1;
		}else {
			JOptionPane.showMessageDialog(this, "No connect data base");
		}
	}
	
	public void outButtonListener() {
		int x = JOptionPane.showConfirmDialog(this, "Do you really want to out","Notification",JOptionPane.OK_CANCEL_OPTION);
		if(x!=0) return;
		setVisible(false);
		menu.setVisible(true);
	}
	
	public void setModel(String[][] chapTableData) {
		chapTableModel = new DefaultTableModel(chapTableData,
				new String[] {
					"Name","Level", "Choice", "Count", "Score"
				}
			) {
				Class[] columnTypes = new Class[] {
						String.class,String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
						false,false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			chapterTable.setModel(chapTableModel);
			chapterTable.getColumnModel().getColumn(0).setPreferredWidth(200);
			chapterTable.getColumnModel().getColumn(0).setMinWidth(200);
			chapterTable.getColumnModel().getColumn(0).setMaxWidth(20000000);
			chapterTable.getColumnModel().getColumn(1).setPreferredWidth(44);
			chapterTable.getColumnModel().getColumn(1).setMinWidth(22);
			chapterTable.getColumnModel().getColumn(1).setMaxWidth(44);
			chapterTable.getColumnModel().getColumn(2).setPreferredWidth(44);
			chapterTable.getColumnModel().getColumn(2).setMinWidth(22);
			chapterTable.getColumnModel().getColumn(2).setMaxWidth(44);
			chapterTable.getColumnModel().getColumn(3).setPreferredWidth(44);
			chapterTable.getColumnModel().getColumn(3).setMinWidth(22);
			chapterTable.getColumnModel().getColumn(3).setMaxWidth(44);
			chapterTable.getColumnModel().getColumn(4).setPreferredWidth(44);
			chapterTable.getColumnModel().getColumn(4).setMinWidth(22);
			chapterTable.getColumnModel().getColumn(4).setMaxWidth(44);
			
	}
	
	public  void setNext(int subid,int temid) {
		if(subModel.getSize()==0) {
			JOptionPane.showMessageDialog(this, "No connect to data base","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int subIndex = -1;
		int temIndex = -1;
		for(int i=0;i<subModel.getSize();i++) {
			if(subModel.getElementAt(i).getId()==subid) {
				subIndex = i;
				break;
			}
		}
		if(subIndex==-1) {
			JOptionPane.showMessageDialog(this, "This option is discrete\nPlease try again later");
			return;
		}
		subjectComboBox.setSelectedIndex(subIndex);
		if(tModel.getSize()==0) {
			JOptionPane.showMessageDialog(this, "No connect to data base","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		for(int i=0;i<tModel.getSize();i++) {
			if(tModel.getElementAt(i).getId()==temid) {
				temIndex = i;
				break;
			}
		}
		if(subIndex==-1) {
			JOptionPane.showMessageDialog(this, "This option is discrete\nPlease try again later");
			return;
		}
		titleComboBox.setSelectedIndex(temIndex);
		JOptionPane.showMessageDialog(this, "Done");
	}
	
	public void setTotalScore() {
		float totalScore = 0;
		for(int i=0;i<strList.size();i++) {
			Struct str = strList.get(i);
			if(str.getCount()>0) {
				totalScore += str.getTotalScore();
			}
		}
		this.totalScore.setText("Total scores of all: "+totalScore);
	}
}
