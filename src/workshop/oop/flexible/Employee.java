package workshop.oop.flexible;

public abstract class Employee {

	private String name;
	protected double salary;

	public Employee() {
	}

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}
	
	//Concrete Method 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	//abstract Method
	public abstract void manageSalary(double rate);

}