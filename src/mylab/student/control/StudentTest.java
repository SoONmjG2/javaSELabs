package mylab.student.control;

import mylab.student.entity.Student;
import workshop.account.entity.Account;

public class StudentTest {

	public static void main(String[] args) {
		
		Student student = new Student(1,"김민수","컴퓨터공학",3);
	
		System.out.print(student.getName()+"/");
		System.out.print(student.getMajor()+"/");
		System.out.println(student.getGrade()+"학년");
		
		try {
			System.out.println("5학년으로 변경");
			student.gradechange(5);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
