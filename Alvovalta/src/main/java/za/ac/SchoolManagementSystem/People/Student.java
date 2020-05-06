package People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class Student extends Person {

	
	private int studentNO;
	private String grade;
	private String parentInfo;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getStudentNo() {
		return studentNO;
	}

	public void setStudentNo(int studentNO) {
		this.studentNO = studentNO;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getParentInfo() {
		return parentInfo;
	}

	public void setParentInfo(String parentInfo) {
		this.parentInfo = parentInfo;
	}

	@Override
	public String toString() {
		return "Student [grade=" + grade + ", parentInfo=" + parentInfo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(grade, parentInfo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(grade, other.grade) && Objects.equals(parentInfo, other.parentInfo);
	}
	
	
	@Override
	public void register(Scanner sc) throws Exception{
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		//"com.mysql.jdbc.Driver" this is the path
		Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root", "pvi@2020");
		//here Pvi is database name, root is username and password
		Statement  st = c1.createStatement();
		
		System.out.println("REGISTER STUDENT INFORMATION");
		System.out.println();
		System.out.println("What's your student number");
		int studentNo = sc.nextInt();
		sc.nextLine();
		System.out.println("What are your full names?");
		String fullName = sc.nextLine();
		//sc.nextLine();
		System.out.println("What's your identity number?");
		Long id = sc.nextLong();
		System.out.println("How old are you?");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("Are you male or female or both?");
		String sex = sc.nextLine();
		System.out.println("What grade are you now?");
		String grade = sc.nextLine();
		System.out.println("What's your home address?");
		String address = sc.nextLine();
		System.out.println("What's your parent/guardian full Names, address and phone number");
		String parentInfo = sc.nextLine();
		System.out.println("Enter your password");
		String password = sc.nextLine();
		/*int y = 1;
		++y;
		setStudentNo(y);
		System.out.println("Welcome, Your student number is " + getStudentNo());*/
		
		String sql = "INSERT INTO Student (student_no, full_names, id_number, age, sex, grade, address, parent_info,"
				+ " password) VALUES (" + studentNo + ",'" + fullName + "'," + id + "," + age + ",'" + sex + "'," + "'" + grade +
				"'," + "'" + address + "'," + "'" + parentInfo + "'," + "'" + password + "'" + ")"; 
		
	    int res = st.executeUpdate(sql);
		System.out.println("Number of rows affected: " + res);
			
		c1.close();
	}


	@Override
	public boolean logIn(String fullName, String password, Person student, Scanner sc) {
		if ((fullName.trim().equalsIgnoreCase(student.getFullNames()) && password.trim().equals(student.getPassword()))) {
			return true;
		} else {
			return false;
		}
	} // TODO Auto-generated method stub

}
