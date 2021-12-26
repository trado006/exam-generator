package control;

import java.util.ArrayList;
import java.util.Random;

import model.FullQuestion;

public class InnerQuestionControl {
	public static String getTestUnit(ArrayList<model.InnerQuestion> iQs) {
		String s="\n\n";
		for(model.InnerQuestion iQ:iQs) {
			s = s+"\n"+iQ.getFullContent()+"\n";
			if(iQ.getAnswer1()==null||iQ.getAnswer1().equals("")) {
				// do nothing
			}else {
				if(iQ.getAnswer1().length()<20) {
					s = s+"\n"+iQ.getAnswer1()+"\t"+iQ.getAnswer2()+"\t"+iQ.getAnswer3()+"\t"+iQ.getAnswer4()+"\n";
				}else {
					s = s+"\n"+iQ.getAnswer1()+"\n"+iQ.getAnswer2()+"\n"+iQ.getAnswer3()+"\n"+iQ.getAnswer4()+"\n";
				}
			}
		}
		return s;
	}
	public static String getTestAnswer(ArrayList<model.InnerQuestion> iQs) {
		String s="\n\n";
		for(model.InnerQuestion iQ:iQs) {
			if(iQ.getChoose()==null||iQ.getChoose().equals("")) continue;
			s = s+"\n"+iQ.getFullQuestionNumber()+iQ.getChoose()+"\n";
		}
		return s;
	}
	public static ArrayList<model.InnerQuestion> pareInnerQs(ArrayList<FullQuestion> fQs){
		ArrayList<model.InnerQuestion> iQs = new ArrayList<>();
		for(int i=0;i<fQs.size();i++) {
			iQs.add(parseInnerQ(fQs.get(i),i+1));
		}
		return iQs;
	}
	public static model.InnerQuestion parseInnerQ(FullQuestion fullQ,int qnumber){
		model.InnerQuestion innerQ = new model.InnerQuestion(0, qnumber, fullQ.getScore(),fullQ.getContent()
				,"","","","", "");
		if(fullQ.getType()==0) return innerQ;
		int choice = 0;
		ArrayList<String> sArr = fullQ.getAnswers();
		ArrayList<String> sArr2 = new ArrayList<>();
		Random rand = new Random();
		boolean first = true;
		while(!sArr.isEmpty()) {
			int index = rand.nextInt(sArr.size());
			if(index==0&&first) {
				first= false;
				sArr2.add(sArr.get(index));
				choice = sArr2.size()-1;
				sArr.remove(index);
			}else {
				sArr2.add(sArr.get(index));
				sArr.remove(index);
			}
		}
		if(!first) {
			innerQ.setChoose(getAnswer(sArr2.get(choice),choice));
		}
		for(int i=0;i<sArr2.size();i++) {
			switch(i) {
			case 0: innerQ.setAnswer1(getAnswer(sArr2.get(i),i)); break;
			case 1: innerQ.setAnswer2(getAnswer(sArr2.get(i),i)); break;
			case 2: innerQ.setAnswer3(getAnswer(sArr2.get(i),i)); break;
			case 3: innerQ.setAnswer4(getAnswer(sArr2.get(i),i)); break;
			}
		}
		return innerQ;
	}
	// in dap an
	public static String getAnswer(String s,int index) {
		String s1 = "";
		switch(index) {
		case 0: s1 = "A. " + s; break;
		case 1: s1 = "B. " + s; break;
		case 2: s1 = "C. " + s; break;
		case 3: s1 = "D. " + s; break;
		}
		return s1;
	}
	public static int getMultipleQuestions(ArrayList<model.InnerQuestion> iQs) {
		int count = 0;
		for(model.InnerQuestion iQ:iQs) {
			if(iQ.getChoose()==null||iQ.getChoose().equals("")) {
				continue;
			}else {
				count ++;
			}
		}
		return count;
	}
}
