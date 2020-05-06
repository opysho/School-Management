package Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class Subject {

	private String subjectName;
	private int subjectCode;

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	@Override
	public String toString() {
		return "Subject [subjectName=" + subjectName + ", subjectCode=" + subjectCode + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(subjectCode, subjectName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return subjectCode == other.subjectCode && Objects.equals(subjectName, other.subjectName);
	}
	
	public void updateSubject(Scanner sc) throws Exception{
			Class.forName("com.mysql.jdbc.Driver");
			//"com.mysql.jdbc.Driver" this is the path
			Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root", "pvi@2020");
			//here Pvi is database name, root is username and password
			Statement  st = c1.createStatement();
			
			System.out.println("UPDATING SUBJECT INFORMATION");
			System.out.println();
			System.out.println("Enter subject name");
			String subject = sc.nextLine();
			System.out.println("Enter subject code");
			String subjectCode = sc.nextLine();
			
			String sql = "INSERT INTO Subject (subject_name, subject_code) VALUES (" + subject + subjectCode + ")"; 
			
		    int res = st.executeUpdate(sql);
			System.out.println("Number of rows affected: " + res);
				
			c1.close();
		}
	}


