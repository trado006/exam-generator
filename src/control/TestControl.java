package control;
import java.util.ArrayList;
import java.util.Random;

import connection.FullQuestionDao;
import connection.StructDao;
import connection.TestDao;
import model.*;

public class TestControl {
	
	Template template;
	ArrayList<Struct> strList;
	ArrayList<FullQuestion> fullQuestionList;
	ArrayList<InnerQuestion> innerQuestionList;
	//1
	public void setTemplate(Template t) {
		this.template = t;
	}
	//2
	public boolean prepareStrList() {
		this.strList = StructDao.getAllStruct(template.getId());
		if(strList!=null) return true;
		else return false;
	}
	//3
	// xóa phần tủ không cân thiết ra khỏi strList
	public boolean removeZero() {
		//System.out.println("first strList size "+strList.size());
		for(int i=0;i<strList.size();i++) {
			if(strList.get(i).getCount()==0) {
				strList.remove(i);
				i--;
			}
		}
		//System.out.println("strList size "+strList.size());
		if(strList.size()==0) return false;
		else return true;
	}
	//4
	public boolean checkStrList() {
		return FullQuestionDao.CheckListAvailable(strList);
	}
	//5
	public boolean getFullQuestions() {
		ArrayList<FullQuestion> fqs = FullQuestionDao.getFullQuestions(strList);
		if(fqs==null) return false;
		fullQuestionList = fqs;
		//System.out.println("fqs is "+fqs.size());
		return true;
	}
	//6
	public void sort() {
		int sorteasyfirst = template.getEasyfirst();
		// đảo danh sách các câu hỏi
		Random rand = new Random();
		ArrayList<FullQuestion> fullQuestionList2 = new ArrayList<>();
		int count = fullQuestionList.size();
		for(int i=0;i<count;i++) {
			int index = rand.nextInt(fullQuestionList.size());
			fullQuestionList2.add(fullQuestionList.get(index));
			fullQuestionList.remove(index);
		}
		//System.out.println("dao ds "+fullQuestionList.size()+" "+fullQuestionList2.size());
		// easy first
		if(sorteasyfirst==0) {
			fullQuestionList.addAll(fullQuestionList2);
			fullQuestionList2.removeAll(fullQuestionList2);
		}else {
			for(int level=0;level<3;level++) {
				for(int i=0;i<fullQuestionList2.size();i++) {
					if(fullQuestionList2.get(i).getLevel()==level) {
						fullQuestionList.add(fullQuestionList2.get(i));
						fullQuestionList2.remove(i);
						i--;
					}
				}
			}
		}
		//System.out.println("dao ds "+fullQuestionList.size()+" "+fullQuestionList2.size());
		// multiple-choice first
		if(template.getType()!=2) {
			// do nothing
		}else {
			fullQuestionList2.addAll(fullQuestionList);
			fullQuestionList.removeAll(fullQuestionList);
			for(int i=0;i<fullQuestionList2.size();i++) {
				if(fullQuestionList2.get(i).getType()==1) {
					fullQuestionList.add(fullQuestionList2.get(i));
					fullQuestionList2.remove(i);
					i--;
				}
			}
			fullQuestionList.addAll(fullQuestionList2);
		}
		//System.out.println("dao ds "+fullQuestionList.size()+" "+fullQuestionList2.size());
		// easy first
	}
	// 7
	public void setInnerQuestionList() {
		innerQuestionList = control.InnerQuestionControl.pareInnerQs(fullQuestionList);
	}
	// 8 ********************
	public String getTestUnit() {
		return control.InnerQuestionControl.getTestUnit(innerQuestionList);
	}
	
	public ArrayList<Struct> getStrList() {
		return strList;
	}
	public void setStrList(ArrayList<Struct> strList) {
		this.strList = strList;
	}
	public ArrayList<FullQuestion> getFullQuestionList() {
		return fullQuestionList;
	}
	public void setFullQuestionList(ArrayList<FullQuestion> fullQuestionList) {
		this.fullQuestionList = fullQuestionList;
	}
	public ArrayList<InnerQuestion> getInnerQuestionList() {
		return innerQuestionList;
	}
	public void setInnerQuestionList(ArrayList<InnerQuestion> innerQuestionList) {
		this.innerQuestionList = innerQuestionList;
	}
	public Template getTemplate() {
		return template;
	}
	
}





