package model;

public class Question {
	int id;
	String content;
	int type;
	int level;
	
	public Question(int id, String content,int type, int level) {
		super();
		this.id = id;
		this.content = content;
		this.type = type;
		this.level = level;
	}
	
	public Question(String content, int type, int level) {
		super();
		this.content = content;
		this.type = type;
		this.level = level;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
