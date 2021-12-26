package model;

public class Template {
	
	private int id;
	private String title;
	private int type;
	private int easyfirst;
	
	public Template(int id, String title, int type, int easyfirst) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.easyfirst = easyfirst;
	}
	
	public Template(String title, int type, int easyfirst) {
		super();
		this.title = title;
		this.type = type;
		this.easyfirst = easyfirst;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getEasyfirst() {
		return easyfirst;
	}
	public void setEasyfirst(int easyfirst) {
		this.easyfirst = easyfirst;
	}
	
	
	public String toString() {
		return title;
	}
}
