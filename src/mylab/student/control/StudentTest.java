package mylab.student.control;

import mylab.student.entity.Student;
import workshop.account.entity.Account;

public class StudentTest {

	public static void main(String[] args) {
		
		Student student = new Student(1,"��μ�","��ǻ�Ͱ���",3);
	
		System.out.print(student.getName()+"/");
		System.out.print(student.getMajor()+"/");
		System.out.println(student.getGrade()+"�г�");
		
		try {
			System.out.println("5�г����� ����");
			student.gradechange(5);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
