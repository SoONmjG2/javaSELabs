package mylab.student.entity;

import mylab.student.exception.InvalidGradeException;

public class Student {
	private int studentId;
	private String name;
	private String major;
	private int grade;
	
	public Student() {
		System.out.println("기본생성자 호출됨");
	}

	public Student(int studentId, String name, String major, int grade) {
		this.studentId = studentId;
		this.name = name;
		this.major = major;
		this.grade = grade;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	//학년변경
	public void gradechange(int num) throws InvalidGradeException{
		//잔액부족
		if(num < 1 || num > 4) {
			String errMessage = String.format("학년은 1~4 사이여야 합니다.");
			throw new InvalidGradeException(errMessage);
		}
		this.grade=num;
	}
		
	
}
