package control;

import java.util.ArrayList;

public class QuestionControl {
	public static String[][] toStringTable(ArrayList<model.Question> ql,ArrayList<model.Answer> al){
		int  n = ql.size();
		String[][] st = new String[n][7];
		for(int i=0;i<n;i++) {
			st[i] = toStrings(ql.get(i),al.get(i));
		}
		return st;
	}
	// content, choice, true answer, falsse
	public static String[] toStrings(model.Question q,model.Answer a) {
		String[] sl = new String[7];
		sl[0] = q.getContent();
		sl[1] = getLevelString(q.getLevel());
		sl[2] = q.getType()==1?"Yes":"No";
		if(q.getType()==1) {
			sl[3] = a.getChoose();
			sl[4] = a.getAnswer1();
			sl[5] = a.getAnswer2();
			sl[6] = a.getAnswer3();
		}
		return sl;
	}
	public static String getLevelString(int l) {
		switch(l) {
		case 0: return "easy";
		case 1: return "normal";
		case 2: return "hard";
		}
		return "";
	}
	public static int getLevelInt(String l) {
		switch(l){
			case "easy": return 0;
			case "normal": return 1;
			case "hard": return 2;
		}
		return -1;
	}
}
