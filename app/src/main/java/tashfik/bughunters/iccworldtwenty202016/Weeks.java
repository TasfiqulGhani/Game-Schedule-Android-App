package tashfik.bughunters.iccworldtwenty202016;

public class Weeks {

	private String name;
	private String semester;
	private String grade;
	private String credit;

	public Weeks(String name, String semester ,String grade   ) {
		this.name = name;
		this.semester = semester;
		this.grade = grade;

	}

	public String getName() {
		return this.name;
	}

	public String getSemester() {
		return this.semester;
	}

	public String getGrade() {
		return this.grade;
	}


	
}
