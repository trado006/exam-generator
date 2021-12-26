package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import connection.ChapterDao;
import connection.SubjectDao;
import model.Chapter;
import model.Subject;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;

public class TableOfContent extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu menu;
	private int userid;
	
	private JPanel contentPane;
	private JList<Chapter> chapterList;
	private JList<Subject> subjectList;
	
	private DefaultListModel<Subject> subModel;
	private DefaultListModel<Chapter> cModel;
	
	public TableOfContent(int userid,Menu menu) {
		this.userid = userid;
		this.menu = menu;
		prepareModel();
		setDisplay();
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTableOfContent = new JLabel("Table of content");
		lblTableOfContent.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\item.png"));
		lblTableOfContent.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTableOfContent.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableOfContent.setBounds(223, 11, 234, 37);
		contentPane.add(lblTableOfContent);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\base_book.png"));
		lblSubject.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubject.setBounds(20, 49, 84, 37);
		contentPane.add(lblSubject);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(UIManager.getColor("Button.shadow"));
		separator.setBounds(320, 49, 2, 300);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 88, 274, 261);
		contentPane.add(scrollPane);
		
		subjectList = new JList<Subject>(subModel);
		subjectList.setBackground(SystemColor.inactiveCaptionBorder);
		subjectList.setForeground(Color.BLACK);
		subjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		subjectList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				subjectListListener();
			}
			
		});
		scrollPane.setViewportView(subjectList);
		
		JButton addSubBtn = new JButton("");
		addSubBtn.setBackground(SystemColor.inactiveCaptionBorder);
		addSubBtn.setToolTipText("Add new subject");
		addSubBtn.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\add_20.png"));
		addSubBtn.setBounds(255, 49, 39, 33);
		addSubBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addSubBtnListener();
			}
			
		});
		contentPane.add(addSubBtn);
		
		JButton subSubBtn = new JButton("");
		subSubBtn.setToolTipText("Remove the subject");
		subSubBtn.setForeground(new Color(0, 0, 0));
		subSubBtn.setBackground(SystemColor.inactiveCaptionBorder);
		subSubBtn.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\remove.png"));
		subSubBtn.setBounds(206, 49, 39, 33);
		subSubBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				subSubBtnListener();
			}
			
		});
		contentPane.add(subSubBtn);
		
		JButton updateSubBtn = new JButton("");
		updateSubBtn.setBackground(SystemColor.inactiveCaptionBorder);
		updateSubBtn.setToolTipText("Rename the subject");
		updateSubBtn.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\undo_20.png"));
		updateSubBtn.setBounds(157, 49, 39, 33);
		updateSubBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateSubBtnListener();
			}
			
		});
		contentPane.add(updateSubBtn);
		
		JLabel lblChapter = new JLabel("Chapter");
		lblChapter.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\chapter.png"));
		lblChapter.setHorizontalAlignment(SwingConstants.LEFT);
		lblChapter.setBounds(347, 49, 84, 37);
		contentPane.add(lblChapter);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(347, 88, 274, 261);
		contentPane.add(scrollPane_1);
		
		chapterList = new JList<Chapter>(cModel);
		chapterList.setBackground(SystemColor.inactiveCaptionBorder);
		chapterList.setVisibleRowCount(-1);
		chapterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(chapterList);
		
		JButton addChapBtn = new JButton("");
		addChapBtn.setBackground(SystemColor.inactiveCaptionBorder);
		addChapBtn.setToolTipText("add new chapter");
		addChapBtn.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\add_20.png"));
		addChapBtn.setBounds(582, 49, 39, 33);
		addChapBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addChapBtnListener();
			}
			
		});
		contentPane.add(addChapBtn);
		
		JButton subChapBtn = new JButton("");
		subChapBtn.setToolTipText("Remove the chapter");
		subChapBtn.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\remove.png"));
		subChapBtn.setForeground(Color.BLACK);
		subChapBtn.setBackground(SystemColor.inactiveCaptionBorder);
		subChapBtn.setBounds(533, 49, 39, 33);
		subChapBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				subChapBtnListener();
			}
			
		});
		contentPane.add(subChapBtn);
		
		JButton updateChapBtn = new JButton("");
		updateChapBtn.setBackground(SystemColor.inactiveCaptionBorder);
		updateChapBtn.setToolTipText("Rename the chapter");
		updateChapBtn.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\undo_20.png"));
		updateChapBtn.setBounds(484, 49, 39, 33);
		updateChapBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateChapBtnListener();
			}
			
		});
		contentPane.add(updateChapBtn);
		
		JButton outBtn = new JButton("Out");
		outBtn.setBackground(SystemColor.inactiveCaptionBorder);
		outBtn.setToolTipText("back to home");
		outBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outBtnListener();
			}
		});
		outBtn.setIcon(new ImageIcon("F:\\project java\\test_manager_programing\\images\\cancel.png"));
		outBtn.setHorizontalAlignment(SwingConstants.LEFT);
		outBtn.setBounds(537, 368, 84, 30);
		contentPane.add(outBtn);
		
		loadDataBase();
	}

	public void setDisplay() {
		setTitle("Table of content");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(659, 451);
		setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	public void prepareModel() {
		subModel = new DefaultListModel<>() ;
		cModel = new DefaultListModel<>();
	}
	
	public void loadDataBase() {
		ArrayList<Subject> subList = SubjectDao.getSubjectList(userid);
		if(subList==null) {
			JOptionPane.showMessageDialog(this,"No connect to database","No connect",JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			subModel.addAll(subList);
		}
	}
	
	public void subjectListListener() {
		if(subjectList.getSelectedIndex()==-1)
			return;
		int subid = ((Subject)subjectList.getSelectedValue()).getId();
		ArrayList<Chapter> chapList = ChapterDao.getChapterList(subid);
		if(chapList!=null) {
			cModel.removeAllElements();
			cModel.addAll(chapList);
			chapterList.setSelectedIndex(-1);
		}else {
			JOptionPane.showMessageDialog(this,"No connect to database","No connect",JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	public void addSubBtnListener() {
		String subname = JOptionPane.showInputDialog(this,"Input the subject name into below:",
				"Input new subject name",JOptionPane.OK_CANCEL_OPTION);
		if(subname==null) return;
		if(subname.equals("")) {
			JOptionPane.showMessageDialog(this, "Non typing","Error",JOptionPane.OK_OPTION);
			return;
		}
		if(SubjectDao.checkSubjectName(subname, userid)==1) {
			JOptionPane.showMessageDialog(this,"The name available","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			switch(SubjectDao.addSubject(subname, userid)) {
			case -1:
				JOptionPane.showMessageDialog(this,"No connect to database","No connect",JOptionPane.ERROR_MESSAGE);
				return;
			case  0:
				JOptionPane.showMessageDialog(this,"The name not active","Error",JOptionPane.ERROR_MESSAGE);
				return;
			case  1:
				ArrayList<Subject> subList = SubjectDao.getSubjectList(userid);
				subModel.removeAllElements();
				subModel.addAll(subList);
				subjectList.ensureIndexIsVisible(subModel.getSize()-1);
				subjectList.setSelectedIndex(subModel.getSize()-1);
				JOptionPane.showMessageDialog(this,"Insert the subject success","Success",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	}
	
	public void subSubBtnListener() {
		if(subjectList.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice the subject","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int x = JOptionPane.showConfirmDialog(this, "Do you really want to delete it?","Notification",JOptionPane.YES_NO_OPTION);
		if(x!=0) return;
		Subject sub = (Subject) subjectList.getSelectedValue();
		int index = subjectList.getSelectedIndex();
		if(SubjectDao.deleteSubject(sub.getId())==-1) {
			JOptionPane.showMessageDialog(this,"No connect to database","No connect",JOptionPane.ERROR_MESSAGE);
			return;
		}
		subModel.removeElementAt(index);
		cModel.removeAllElements();
		JOptionPane.showMessageDialog(this,"Delete the subject successfully","Successfully",JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void updateSubBtnListener() {
		if(subjectList.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice the subject","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String subname = JOptionPane.showInputDialog(this,"Input new name of this subject:","Rename subject",JOptionPane.OK_CANCEL_OPTION);
		if(subname==null) return;
		if(subname.equals("")) {
			JOptionPane.showMessageDialog(this, "non typing","non typing",JOptionPane.OK_OPTION);
			return;
		}
		Subject sub = (Subject) subjectList.getSelectedValue();
		int index = subjectList.getSelectedIndex();
		sub.setName(subname);
		if(SubjectDao.updateSubject(sub)==-1) {
			JOptionPane.showMessageDialog(this,"No connect to database","Error no connect",JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			subModel.removeElementAt(index);
			subModel.insertElementAt(sub, index);
			subjectList.setSelectedIndex(index);
			subjectList.ensureIndexIsVisible(index);
			JOptionPane.showMessageDialog(this,"Rename the subject successfully","Successfully",JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	public void addChapBtnListener() {
		if(subjectList.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice subject","Non choice",JOptionPane.WARNING_MESSAGE);
			return;
		}
		String cname = JOptionPane.showInputDialog(this,"Input the chapter name into below:","Input new chapter name",JOptionPane.OK_CANCEL_OPTION);
		if(cname==null) return;
		if(cname.equals("")) {
			JOptionPane.showMessageDialog(this, "Non typing","Non typing",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int subid = ((Subject)subjectList.getSelectedValue()).getId();
		if(ChapterDao.checkChapterName(cname, subid)==1) {
			JOptionPane.showMessageDialog(this,"The name available","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		switch(ChapterDao.addChapter(cname, subid)) {
		// -1 no connect
	    //  0 name available
	    //  1 did
		case -1:
			JOptionPane.showMessageDialog(this,"No connect to database","No connect",JOptionPane.ERROR_MESSAGE);
			break;
		case  1:
			ArrayList<Chapter> chapList = ChapterDao.getChapterList(subid);
			if(chapList==null) {
				JOptionPane.showMessageDialog(this,"No connect to database","No connect",JOptionPane.ERROR_MESSAGE);
				return;
			}
			cModel.removeAllElements();
			cModel.addAll(chapList);
			chapterList.ensureIndexIsVisible(cModel.getSize()-1);
			chapterList.setSelectedIndex(cModel.getSize()-1);
			JOptionPane.showMessageDialog(this,"Insert the chapter success","Success",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void subChapBtnListener() {
		if(chapterList.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice chapter","Non choice",JOptionPane.WARNING_MESSAGE);
			return;
		}
		int x = JOptionPane.showConfirmDialog(this, "Do you really want to delete it?","Notification",JOptionPane.OK_CANCEL_OPTION);
		if(x!=0) return;
		Chapter c = (Chapter) chapterList.getSelectedValue();
		int index = chapterList.getSelectedIndex();
		if(ChapterDao.deleteChapter(c.getId())==-1) {
			JOptionPane.showMessageDialog(this,"No connect to database","No connect",JOptionPane.ERROR_MESSAGE);
			return;
		}
		cModel.removeElementAt(index);
		JOptionPane.showMessageDialog(this,"Delete the chapter success","Success",JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	// thu nghiem
	public void updateChapBtnListener() {
		if(chapterList.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Please choice chapter","Non choice",JOptionPane.WARNING_MESSAGE);
			return;
		}
		String cname = JOptionPane.showInputDialog(this,"Input new name of this chapter:","Rename chapter",JOptionPane.OK_CANCEL_OPTION);
		if(cname==null) return;
		if(cname.equals("")) {
			JOptionPane.showMessageDialog(this, "Non typing","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Chapter c = (Chapter) chapterList.getSelectedValue();
		int index = chapterList.getSelectedIndex();
		c.setName(cname);
		if(ChapterDao.updateChapter(c)==-1) {
			JOptionPane.showMessageDialog(this,"No connect to database","No connect",JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			cModel.removeElementAt(index);
			cModel.insertElementAt(c, index);
			chapterList.ensureIndexIsVisible(index);
			chapterList.setSelectedIndex(index);
			JOptionPane.showMessageDialog(this,"Rename the chapter success","Success",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void outBtnListener() {
		setVisible(false);
		menu.setVisible(true);
	}
	
	
	
}
