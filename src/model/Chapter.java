package model;

public class Chapter {
	int id;
	String name;

	public Chapter() {
		this.id=0;
		this.name=null;
	}

	public Chapter(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return name;
	}
}
