package control;

import java.util.ArrayList;

import model.Struct;

public class StructControl {
	public static String[][] toStringTable(ArrayList<Struct> sl){
		// "Name","level", "choice", "Count", "Score"
		int n = sl.size();
		String[][] stringTable = new String[n][5];
		for(int i=0;i<n;i++) {
			Struct str = sl.get(i);
			stringTable[i] = toStringRow(str);
		}
		return stringTable;
	}
	public static String[] toStringRow(Struct str) {
		String rowString[] = new String[5];
		rowString[0] = str.getcName();
		rowString[1] = getLevelString(str.getLevel());
		rowString[2] = getChoiceString(str.getType());
		rowString[3] = String.valueOf(str.getCount());
		rowString[4] = String.valueOf(str.getTotalScore());
		return rowString;
	}
	public static int getCount(String count) {
		try {
			int counts = Integer.parseInt(count);
			if(counts>=0) return counts;
			return -1;
		}catch(Exception ex) {
			return -1;
		}
	}
	
	public static float getScore(String score) {
		try {
			Float totalscore = Float.parseFloat(score);
			if(totalscore>=0) return totalscore;
			return -1;
		}catch(Exception ex) {
			return -1;
		}
	}
	public static String getChoiceString(int t) {
		if(t==0) return "no";
		else return "yes";
	}
	
	public static int getChoiceInt(String type) {
		if(type.equals("no")) return 0;
		if(type.equals("yes")) return 1;
		return -1;
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
