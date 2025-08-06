package mylab.student.entity;

import mylab.student.exception.InvalidGradeException;

public class Student {
	private int studentId;
	private String name;
	private String major;
	private int grade;
	
	public Student() {
		System.out.println("�⺻������ ȣ���");
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
	
	//�г⺯��
	public void gradechange(int num) throws InvalidGradeException{
		//�ܾ׺���
		if(num < 1 || num > 4) {
			String errMessage = String.format("�г��� 1~4 ���̿��� �մϴ�.");
			throw new InvalidGradeException(errMessage);
		}
		this.grade=num;
	}
		
	
}
