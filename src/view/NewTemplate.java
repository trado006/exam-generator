package view;


import javax.swing.JPanel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import connection.ChapterDao;
import connection.StructDao;
import connection.SubjectDao;
import connection.TemplateDao;
import model.Chapter;
import model.Struct;
import model.Subject;
import model.Template;

import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTemplate extends JFrame {
	private Menu menu;
	int userid;
	private JPanel contentPane;
	
	private JTextField titleTextField;
	private JComboBox<Subject> subjectComboBox;
	private JComboBox<Chapter> chapterComboBox;
	private JComboBox<Object> typeComboBox;
	private JRadioButton sortEasyFistRadioButton;
	
	private ArrayList<Subject> subList;
	private DefaultComboBoxModel<Subject> subModel;
	private ArrayList<Chapter> chapList;
	private DefaultComboBoxModel<Chapter> chapModel;
	
	private JList<Chapter> chapterList;
	private DefaultComboBoxModel<Chapter> chapListModel;
	
	
	/**
	 * Create the panel.
	 */
	public NewTemplate(Menu menu,int userid) {
		this.menu = menu;
		this.userid = userid;
		setDisplay();
		prepareModel();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCreateNewTest = new JLabel("Create new test template");
		lblCreateNewTest.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\Edit.png"));
		lblCreateNewTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateNewTest.setForeground(Color.DARK_GRAY);
		
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
				subjectComboBoxListener();
			}
		});
		
		titleTextField = new JTextField();
		titleTextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleTextField.setColumns(10);
		
		typeComboBox = new JComboBox<Object>();
		typeComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		typeComboBox.setModel(
				new DefaultComboBoxModel<Object>(
						new String[] {"Foreword", "Multiple-choice", "All"}));
		typeComboBox.setSelectedIndex(-1);
		
		sortEasyFistRadioButton = new JRadioButton("Easy first hard last");
		sortEasyFistRadioButton.setBackground(Color.LIGHT_GRAY);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Template chapter list", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		
		JButton btnOut = new JButton("Out");
		btnOut.setBackground(SystemColor.inactiveCaptionBorder);
		btnOut.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\cancel.png"));
		btnOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				outButtonListener();
			}
		});
		
		JButton btnNext = new JButton("Next");
		btnNext.setBackground(SystemColor.inactiveCaptionBorder);
		btnNext.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\out_20.png"));
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nextButtonListener();
			}
		});
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBackground(SystemColor.inactiveCaptionBorder);
		btnCreate.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\addtemplate_20.png"));
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int id = createButtonListener();
				if(id==-1) return;
				toBegin();
				JOptionPane.showMessageDialog(null, "Create template successfully");
				
			}
		});
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(SystemColor.inactiveCaptionBorder);
		btnReset.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\undo_20.png"));
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resetButtonListener();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblCreateNewTest, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblSortBy, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTestType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblSubject, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(titleTextField, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addComponent(subjectComboBox, 0, 454, Short.MAX_VALUE)
						.addComponent(sortEasyFistRadioButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(211, Short.MAX_VALUE)
					.addComponent(btnNext)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCreate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnReset)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOut)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblCreateNewTest, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubject, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(subjectComboBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTestType, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSortBy, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(sortEasyFistRadioButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOut)
						.addComponent(btnCreate)
						.addComponent(btnReset)
						.addComponent(btnNext))
					.addGap(5))
		);
		
		JLabel lblChapter = new JLabel("Chapter:");
		lblChapter.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\chapter.png"));
		
		chapterComboBox = new JComboBox<Chapter>(chapModel);
		chapterComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		
		JButton btnSub = new JButton("Sub");
		btnSub.setBackground(SystemColor.inactiveCaptionBorder);
		btnSub.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\remove.png"));
		btnSub.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subButtonListener();
			}
		});
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(SystemColor.inactiveCaptionBorder);
		btnAdd.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\add_20.png"));
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addButtonListener();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblChapter)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chapterComboBox, 0, 261, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSub))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChapter)
						.addComponent(chapterComboBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSub)
						.addComponent(btnAdd))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		chapterList = new JList<Chapter>(chapListModel);
		scrollPane.setViewportView(chapterList);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
		loadDataBase();
	}
	
	public void setDisplay() {
		setTitle("Change question");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(700,700);
		setLocationRelativeTo(null);
	}
	
	
	public void prepareModel() {
		subModel = new DefaultComboBoxModel<Subject>();
		chapModel = new DefaultComboBoxModel<Chapter>();
		chapListModel = new DefaultComboBoxModel<Chapter>();
	}
	
	
	public void loadDataBase() {
		subList = SubjectDao.getSubjectList(userid);
		if(subList==null) {
			JOptionPane.showMessageDialog(this,"No connect to database","No connect",JOptionPane.ERROR_MESSAGE);
		}else {
			subModel.addAll(subList);
		}
	}
	
	
	public void subjectComboBoxListener() {
		if(subjectComboBox.getSelectedIndex()!=-1) {
			int subid = ((Subject)subjectComboBox.getSelectedItem()).getId();
			chapList = ChapterDao.getChapterList(subid);
			if(chapList!=null) {
				chapModel.addAll(chapList);
				chapterComboBox.setSelectedIndex(-1);
			}else {
				JOptionPane.showMessageDialog(this, "No connection to data base");
			}
		}
	}
	
	
	public void addButtonListener() {
		if(subjectComboBox.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice the subject");
			return;
		}
		if(titleTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Please input the title");
			return;
		}
		if(typeComboBox.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice the type combobox");
			return;
		}
		if(chapterComboBox.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice the chapter combobox");
			return;
		}
		setEnable(false);
		if(addChapter((Chapter)chapterComboBox.getSelectedItem())) {
			JOptionPane.showMessageDialog(this, "Add the chapter successfully");
			chapterComboBox.setSelectedIndex(-1);
		}else {
			JOptionPane.showMessageDialog(this, "The chapter available");
		}
	}
	
	public boolean addChapter(Chapter c) {
		for(int i=0;i<chapListModel.getSize();i++) {
			if(chapListModel.getElementAt(i).getId()==c.getId()) {
				return false;
			}
		}
		chapListModel.addElement(c);
		return true;
	}
	
	public void setEnable(boolean b) {
		subjectComboBox.setEnabled(b);
		titleTextField.setEditable(b);
		sortEasyFistRadioButton.setEnabled(b);
		typeComboBox.setEnabled(b);
	}

	public void subButtonListener() {
		if(chapterList.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Let choose the item to delete");
		}else {
			int x = JOptionPane.showConfirmDialog(this, "Do you realy want to delete it?\n\n"
					,"notification",JOptionPane.OK_CANCEL_OPTION);
			if(x==2) return;
			int index = chapterList.getSelectedIndex();
			chapListModel.removeElementAt(index);
			chapterList.setSelectedIndex(-1);
			JOptionPane.showMessageDialog(this, "Remove the chapter successfully");
		}
	}

	public int createButtonListener() {
		if(subjectComboBox.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice the subject", "Error",JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		if(titleTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Please input the name of template", "Error",JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		if(typeComboBox.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice the type", "Error",JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		if(chapListModel.getSize()==0) {
			JOptionPane.showMessageDialog(this, "No chapter");
			return -1;
		}
		Template t = new Template(titleTextField.getText(), typeComboBox.getSelectedIndex(), sortEasyFistRadioButton.isSelected()==true? 1:0);
		int subid = ((Subject) subjectComboBox.getSelectedItem()).getId();
		ArrayList<Struct> strList = new ArrayList<Struct>();
		for(int i=0;i<chapListModel.getSize();i++) {
			Chapter c = (Chapter)chapListModel.getElementAt(i);
			int cid = (c).getId();
			int type = typeComboBox.getSelectedIndex();
			int level;
			if(type!=2) {
				for(level = 0;level<3;level++) {
					strList.add(new Struct(cid, c.getName(), type, 0, level, 0));
				}
			}else {
				for(int lType=0;lType<2;lType++) {
					for(level = 0;level<3;level++) {
						strList.add(new Struct(cid, c.getName(), lType, 0, level, 0));
					}
				}
			}
		}
		int id = TemplateDao.insertTandId(t,subid);
		if(id==-1) {
			JOptionPane.showMessageDialog(this, "No connect to database");
			return -1;
		}
		if(StructDao.insertStructList(strList, id))
			return id;
		else {
			JOptionPane.showMessageDialog(this, "Only insert the template without struct");
			return -1;
		}
	}

	public void toBegin() {
		titleTextField.setText("");
		sortEasyFistRadioButton.setSelected(false);
		typeComboBox.setSelectedIndex(-1);
		chapterComboBox.setSelectedIndex(-1);
		chapListModel.removeAllElements();
		setEnable(true);
	}
	
	public void nextButtonListener() {
		int temid = createButtonListener();
		if(temid==-1) return;
		int subid = ((Subject)subjectComboBox.getSelectedItem()).getId();
		TemplateManager templateManager = new TemplateManager(menu, userid);
		setVisible(false);
		templateManager.setVisible(true);
		templateManager.setNext(subid, temid);
	}
	
	public void resetButtonListener() {
		if(chapListModel.getSize()==0) {
			JOptionPane.showMessageDialog(this, "No question");
			return;
		}
		int x =JOptionPane.showConfirmDialog(this, "Can lost the data","notification", JOptionPane.OK_CANCEL_OPTION);
		if(x!=0) return;
		chapListModel.removeAllElements();
		chapterComboBox.setSelectedIndex(-1);
		setEnable(true);
	}

	public void outButtonListener() {
		int x = JOptionPane.showConfirmDialog(this, "Do you realy want to exit it?\n\n"
				+"The data will be lossed","notification",JOptionPane.OK_CANCEL_OPTION);
		if(x!=0) return;
		setVisible(false);
		menu.setVisible(true);
	}
	
	
	
}
