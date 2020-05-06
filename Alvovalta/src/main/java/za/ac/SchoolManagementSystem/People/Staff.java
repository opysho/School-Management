package People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Staff extends Person {

	private String position;
	private int staffNo;

	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

		
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(int staffNo) {
		this.staffNo = staffNo;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		return "Staff [position=" + position + ", staffNo=" + staffNo + "]";
	}


	@Override
	public void register(Scanner sc) throws Exception {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.jdbc.Driver");
		//"com.mysql.jdbc.Driver" this is the path
		Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root", "pvi@2020");
		//here Pvi is database name, root is username and password
		Statement  st = c1.createStatement();

		System.out.println("REGISTER STAFF INFORMATION");
		System.out.println();
		sc.nextLine();
		System.out.println("What are your full names?");
		String fullName = sc.nextLine();
		System.out.println("What's your identity number?");
		 Long id = sc.nextLong();
		System.out.println("How old are you?");
		 int age = sc.nextInt();
		 sc.nextLine();
		System.out.println("Are you male or female or both?");
		String sex = sc.nextLine();
		//sc.nextLine();
		System.out.println("What's your home address?");
		String address = sc.nextLine();
		System.out.println("What's your staff number?");
		String staffNo = sc.nextLine();
		System.out.println("What is your position?");
		String position = sc.nextLine();
		System.out.println("Enter your password?");
		String password = sc.nextLine();
	/*	addNo++;
		String y = "tch";

		String teacherNo = y.concat(String.valueOf(addNo));
		System.out.println("Welcome, Your teacher number is " + teacherNo );*/
		
		String sql = "INSERT INTO Staff (staff_no, full_names, id_number, age, sex, address, password, position) VALUES ("
		+ staffNo + ",'" + fullName + "'," + id + "," + age + ",'" + sex + "'," + "'" + address + "'," + "'" + password + "'" + ",'" + position + "'" + ")"; 
		
		int res1 =  st.executeUpdate(sql);
		System.out.println("Number of rows affected: " + res1);
		c1.close();
	}
	

	@Override
	public boolean logIn(String fullName, String password, Person staff, Scanner sc) {
		// TODO Auto-generated method stub
		if (fullName.trim().equals(staff.getFullNames()) && password.trim().equals(staff.getPassword())) {
			return true;
		} else {
			return false;
		}

	}
}
